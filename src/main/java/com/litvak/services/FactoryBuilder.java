package com.litvak.services;

import com.litvak.entities.Customer;
import com.litvak.entities.Product;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Getter
public class FactoryBuilder {

    private SessionFactory factory;

    @PostConstruct
    public void init(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
    }

    @PreDestroy
    public void close() {
        factory.close();
    }
}
