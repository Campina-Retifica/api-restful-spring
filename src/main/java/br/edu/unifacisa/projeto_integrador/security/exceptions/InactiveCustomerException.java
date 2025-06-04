package br.edu.unifacisa.projeto_integrador.security.exceptions;

public class InactiveCustomerException extends RuntimeException {
    public InactiveCustomerException(String message) {
        super(message);
    }
}