package ru.dest.samsungapp.api;

import java.io.IOException;

public class ApiConnectException extends IOException {

    public ApiConnectException(String message) {
        super(message);
    }

    public ApiConnectException(){
        super( "Connection Error: Invalid response code");
    }
}
