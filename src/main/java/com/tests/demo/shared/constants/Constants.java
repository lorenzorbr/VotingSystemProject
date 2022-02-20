package com.tests.demo.shared.constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {

    @Retention(RetentionPolicy.SOURCE)
    public @interface VOTE{
        String ERROR_ALREADY_VOTED = "Voce Ja Votou Nessa Pauta.";
        String ERROR_TIMEOUT = "A Sessao Expirou.";
        String SESSION_STILL_OPENED = "A Sessao Encontra-se Aberta, Seu Resultado Ainda Nao Esta Disponivel";
        String VOTE_NOT_FOUND = "O Voto Nao Foi Encontrado";
        String VOTE_RETURNED = "Voto retornado com sucesso";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface  SCHEDULE{
        String ERROR_NOT_FOUND = "Pauta Nao Encontrada na Base de Dados.";
        String SESSION_NEEDS_DESCRIPTION = "A Sessao Precisa de Uma Descricao";
        String SESSION_CLOSED = "Sessao Nao Esta Aberta.";
        String SESSION_CREATED = "Pauta criada com sucesso";
        String SESSION_RETURNED_SUCCESFUL = " Pauta retornada com sucesso ";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface USER {
        String ERROR_NOT_FOUND = "Usuario Nao Encontrado na Base de Dados.";
        String USER_CANNOT_BE_CREATED = "Usuario Nao Pode Ser Criado, nome nulo ou CPF invalido";
        String USER_CREATED = "Usuario criado com sucesso";
        String USER_RETURNED = "Usuario retornado com sucesso";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ERROR{
        String ENTRY_ERROR = "Nao Foi Possivel Criar Uma Sessao";
    }


}
