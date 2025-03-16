package org.dirimo.consumerservice.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSListener {

    @JmsListener(destination = "sample-queue")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
