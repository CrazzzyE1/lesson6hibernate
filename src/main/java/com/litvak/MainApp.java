package com.litvak;

import com.litvak.configurations.AppConfig;
import com.litvak.services.AppService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        // TODO разобраться, почему комментим
        // ", products=" + products +
        // Потому что, по умолчанию в примере из урока идет Lazy загрузка данных из БД
        // Пока нет вызова для List<Product> products, он не инициализирован и toString бросает Exception

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppService appService = context.getBean(AppService.class);
        System.out.println(appService.getProductById(1L));
        System.out.println(appService.getCustomerById(1L));
        System.out.println(appService.getProductById(2L));
        System.out.println(appService.getCustomerById(2L));
        System.out.println(appService.getProductById(3L));
        System.out.println(appService.getCustomerById(3L));
        System.out.println(appService.getProductById(4L));
        System.out.println("_______________________________________");
        System.out.println(appService.getCustomerProducts(2L));
        System.out.println(appService.getProductCustomers(2L));
    }
}
