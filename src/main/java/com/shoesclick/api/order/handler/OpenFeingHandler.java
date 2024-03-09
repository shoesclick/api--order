package com.shoesclick.api.order.handler;

import com.shoesclick.api.order.exception.ElementNotFoundException;
import com.shoesclick.api.order.exception.ListNotFoundException;
import com.shoesclick.api.order.exception.ResourceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OpenFeingHandler implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        Response.Body responseBody = response.body();
        switch (responseStatus) {
            case NO_CONTENT -> {
                return new ListNotFoundException(getMessage(requestUrl, responseBody));
            }
            case NOT_FOUND -> {
                return new ElementNotFoundException(getMessage(requestUrl, responseBody));
            }
            default -> {
                return new ResourceException(getMessage(requestUrl, responseBody));
            }
        }
    }

    private static String getMessage(String requestUrl, Response.Body responseBody) {
        try {
            return new StringBuilder("ERRO AO EXECUTAR O SERVIÇO: ").append(requestUrl).append(" - ").append(IOUtils.toString(responseBody.asInputStream())).toString();
        } catch (IOException e) {
            throw new ResourceException("Erro ao recuperar o body:", e);
        }
    }
}
