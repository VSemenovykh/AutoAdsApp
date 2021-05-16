package ru.ncedu.services;

import ru.ncedu.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendMail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
}
