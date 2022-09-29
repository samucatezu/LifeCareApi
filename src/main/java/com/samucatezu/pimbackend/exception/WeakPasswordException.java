package com.samucatezu.pimbackend.exception;

public class WeakPasswordException extends RuntimeException{
        public WeakPasswordException(String message) {
                super(message);
        }
}
