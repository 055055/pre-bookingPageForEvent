package com.smile.prebookingforgames.exception;

import com.smile.prebookingforgames.error.ServiceError;

public class PreBookingException extends RuntimeException {
    private final ServiceError serviceError;

    public PreBookingException(ServiceError serviceError) {
        this.serviceError = serviceError;
    }

    public PreBookingException(String message, ServiceError serviceError) {
        super(message);
        this.serviceError = serviceError;
    }

    public PreBookingException(String message, Throwable cause, ServiceError serviceError) {
        super(message, cause);
        this.serviceError = serviceError;
    }

    public PreBookingException(Throwable cause, ServiceError serviceError) {
        super(cause);
        this.serviceError = serviceError;
    }

    public PreBookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ServiceError serviceError) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.serviceError = serviceError;
    }

    public ServiceError getServiceError() {
        return serviceError;
    }
}
