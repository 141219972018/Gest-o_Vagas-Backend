package br.com.mbalujunior.gestao_vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Gestão de Vagas",description = "API responsavél pela gestão de vagas",version = "1"))
//Passando autenticação no swegger para as rotas
//@SecurityScheme(name="jwt_auth", scheme = "bearer", bearerFormat = "JWT", type= SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class GestaoVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVagasApplication.class, args);
	}

}
