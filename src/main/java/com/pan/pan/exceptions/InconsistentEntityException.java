package com.pan.pan.exceptions;

public class InconsistentEntityException extends SystemException {

    public InconsistentEntityException(String message) {
        super(message);
    }

    public InconsistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
