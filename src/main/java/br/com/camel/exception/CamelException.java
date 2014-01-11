package br.com.camel.exception;

public class CamelException extends Exception {

    public CamelException() {
	super();
    }

    public CamelException(String msg, Throwable cause) {
	super(msg, cause);
    }

    public CamelException(String msg) {
	super(msg);
    }

    public CamelException(Throwable cause) {
	super(cause);
    }

}
