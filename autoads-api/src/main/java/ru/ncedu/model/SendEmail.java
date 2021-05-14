package ru.ncedu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmail {

    private String message;
    private String toEmail;
    private String subject;
    private String fromEmail;
}
