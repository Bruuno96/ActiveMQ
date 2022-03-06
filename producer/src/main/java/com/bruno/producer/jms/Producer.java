package com.bruno.producer.jms;

import com.bruno.producer.entities.Person;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {


    private final JmsTemplate jmsTemplate;

    @Value("${activemq.name}")
    private String destinationQueue;

    public Producer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    // Converter para JSON e enviar a mensagem informando a fila de destino e objeto json
    public void send(Person person){
        Gson gson = new Gson();
        String jsonPerson = gson.toJson(person);
        jmsTemplate.convertAndSend(destinationQueue, jsonPerson);
    }

}