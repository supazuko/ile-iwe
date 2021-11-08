package com.ileiwe.service.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String to;
    private String body;
    private String from;
    private String subject;
}
