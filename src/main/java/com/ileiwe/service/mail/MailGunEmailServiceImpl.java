package com.ileiwe.service.mail;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.springframework.stereotype.Service;

@Service("mailgun")
public class MailGunEmailServiceImpl implements EmailService{
    @Override
    public MailResponse send(Message message) {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                .basicAuth("api", API_KEY)
                .field("from", "Excited User <USER@YOURDOMAIN.COM>")
                .field("to", "artemis@example.com")
                .field("subject", "hello")
                .field("text", "testing")
                .asJson();

//        return request.getBody();
        return null;
    }
}
