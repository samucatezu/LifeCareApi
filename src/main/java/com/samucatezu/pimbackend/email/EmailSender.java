package com.samucatezu.pimbackend.email;

public interface EmailSender {
    void send(String to, String email);
}
