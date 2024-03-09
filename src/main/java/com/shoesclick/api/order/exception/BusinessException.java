package com.shoesclick.api.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class BusinessException extends ResourceException {

    /**
     * Serial id
     */
    private static final long serialVersionUID = 320544027441912540L;

    /**
     * Construtor com parametros
     *
     * @param message Mensagem
     *
     * @param cause Objeto de exceção
     *
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Construtor com parametros
     *
     * @param message Mensagem
     */
    public BusinessException(String message) {
        super(message);
    }

}