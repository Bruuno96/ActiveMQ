package com.bruno.producer.resource;

import com.bruno.producer.entities.Person;
import com.bruno.producer.jms.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.JMSException;
import javax.naming.NamingException;

@Controller
public class PersonResource {

    private final Producer producer;

    public PersonResource(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/cadastra-pessoas")
    private String cadastraPessoas(Model model){
        return "cadastra-pessoas.html";
    }

    @PostMapping(value="salvar")
    public String save(@RequestParam("name") String name, @RequestParam("age") int age, Model model) throws JMSException, NamingException {
        Person person = new Person(name,age);
        producer.send(person);
        return "/cadastra-pessoas";
    }

}