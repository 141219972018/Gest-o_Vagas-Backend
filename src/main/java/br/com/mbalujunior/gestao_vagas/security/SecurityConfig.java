package br.com.mbalujunior.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity

public class SecurityConfig {

    @Autowired
    private SecurityFilter securityCompanyFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    private static final String[] SWAGGER_LIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**"
    };

    /*  é usado para dizer que o metodo a baixo é um objecto gerenciado 
    pelo spring
    metodo para gerenciar as requisicoes */
    @Bean 
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        /*desabilitar o spring security csrf, ele é uma vunerabilidade
       usado para ataques e vou configurar da minha ouytra forma */
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> { 
                //Aqui estou a permitir o acesso total nesses dois caminho(Rota)
                auth.requestMatchers("/candidate/").permitAll()
                    .requestMatchers("/company/").permitAll()
                    .requestMatchers("/company/auth").permitAll()
                    .requestMatchers("/candidate/auth").permitAll()
                    .requestMatchers(SWAGGER_LIST).permitAll();




                auth.anyRequest().authenticated();//o resto das Rotas tem que se autenticar 
                //antes de acessae
            })
                .addFilterBefore(securityCandidateFilter,BasicAuthenticationFilter.class)
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class);
                
            ;
        return http.build();
    }

    @Bean //encriptando a minha palavra pass
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
