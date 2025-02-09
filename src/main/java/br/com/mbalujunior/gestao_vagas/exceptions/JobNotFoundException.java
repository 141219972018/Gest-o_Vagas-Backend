package br.com.mbalujunior.gestao_vagas.exceptions;

public class JobNotFoundException extends  RuntimeException{
    public JobNotFoundException(){
        super("Job Trabalho não existe");
   }  
}
