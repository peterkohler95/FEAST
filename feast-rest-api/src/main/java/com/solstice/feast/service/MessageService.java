package com.solstice.feast.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public MessageService(@Value("${twilio.account-sid}") String accountSid,
                          @Value("${twilio.auth-token}") String authToken) {
        Twilio.init(accountSid, authToken);
    }

    /**
     * Send message for demo purposes
     *
     * @param message
     * @return
     * @throws TwilioException
     */
    public Message sendSMS(com.solstice.feast.domain.Message message) throws TwilioException {

        return Message.creator(new PhoneNumber(message.getTo()),
                new PhoneNumber(message.getFrom()),
                message.getMessage()).create();
    }

}
