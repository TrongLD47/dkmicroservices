package dev.ngdangkiet.service;

import dev.ngdangkiet.DTO.EmailDTO;
import dev.ngdangkiet.domain.JsonMessage;

public interface EmailService {

    void sendMail(EmailDTO email, boolean isHTML);
    void sendMail(String sendTo, String Subject, String body);
    void sendEmailWithTemplate(EmailDTO email, String templateName);
    void receiveAlterEmail(JsonMessage message);

}
