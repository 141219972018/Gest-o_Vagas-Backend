package br.com.mbalujunior.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class CreateJobDTO {
    
    @Schema(example=" Vaga para desenvolvedor júnior", requiredMode= RequiredMode.REQUIRED)
    private String description;

    @Schema(example=" Plano de saúde", requiredMode= RequiredMode.REQUIRED)
    private String  benefits;

    @Schema(example=" JUNIOR", requiredMode= RequiredMode.REQUIRED)
    private String level;
}
