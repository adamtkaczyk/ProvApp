package com.ita.provapp.common.exceptions;

import com.ita.provapp.common.json.ErrorMessage;

public class ServerException extends Throwable {
    private ErrorMessage error;

    public ServerException(ErrorMessage error) {
        this.error = error;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return "Cannot execute request: " + error.getMessage();
    }
}
