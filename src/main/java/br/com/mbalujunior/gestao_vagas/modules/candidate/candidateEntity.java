package br.com.mbalujunior.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity(name = "candidate")
@AllArgsConstructor
@NoArgsConstructor
public class candidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo [name] é obrigatório.")
    private String name;

    @NotBlank(message = "O campo [username] é obrigatório.")
    @Pattern(regexp = "^[^\\s]+$", message = "O campo [username] não pode conter espaços.")
    private String username;

    @NotBlank(message = "O campo [email] é obrigatório.")
    @Email(message = "O campo [email] deve conter um email válido.")
    private String email;

    @NotBlank(message = "O campo [password] é obrigatório.")
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres.")
    private String password;

    @Lob
    private String description;

    @Lob
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
