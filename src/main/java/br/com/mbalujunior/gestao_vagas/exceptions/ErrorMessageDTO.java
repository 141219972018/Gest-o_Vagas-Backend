package br.com.mbalujunior.gestao_vagas.exceptions;

import lombok.Data;

@Data
public class ErrorMessageDTO {
    
    private String message;
    private String field;

    public ErrorMessageDTO(String s,String x){
            this.message=s;
            this.field=x;
    }
}

