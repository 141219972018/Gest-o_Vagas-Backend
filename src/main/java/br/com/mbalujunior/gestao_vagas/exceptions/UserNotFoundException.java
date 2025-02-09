package br.com.mbalujunior.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Usuario não existe");
   } 
}
