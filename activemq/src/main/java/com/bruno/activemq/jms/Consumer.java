package com.bruno.activemq.jms;

import com.bruno.activemq.entities.Person;
import com.bruno.activemq.repositories.PersonRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Consumer {

    private List<Person> persons = new ArrayList<>();
    private final PersonRepository personRepository;


    @JmsListener( destination = "${activemq.name}" ) // Escutando a fila.pessoa criada no endpoint http://localhost:8161/admin/queues.jsp
    public void listen(String mensagem) {
        System.out.println(mensagem);
        Person person = new Person();
        try {
            Gson gson = new Gson();
            person = gson.fromJson(mensagem, Person.class);
            personRepository.save(person);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}