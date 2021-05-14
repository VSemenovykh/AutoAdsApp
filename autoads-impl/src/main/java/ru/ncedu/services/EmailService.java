package ru.ncedu.services;

public interface EmailService {

    void  sendMail(String toEmail, String subject, String message);
}
