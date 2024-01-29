package br.com.irvabank.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1915326089970482456L;

    private int httpStatus;

    private String error;

    public ApiException(int httpStatus, String error) {
        super(error);
        this.httpStatus = httpStatus;
        this.error = error;
    }


    public String getError() {
        return error;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

}
