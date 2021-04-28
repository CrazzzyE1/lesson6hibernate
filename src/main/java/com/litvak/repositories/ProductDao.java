package com.litvak.repositories;

import com.litvak.entities.Customer;
import com.litvak.entities.Product;
import com.litvak.services.FactoryBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    private FactoryBuilder factory;
    private Session session = null;

    @Autowired
    public void setFactory(FactoryBuilder factory) {
        this.factory = factory;
    }

    public Product getProductById(Long id) {
        Product product;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return product;
    }

    public List<Customer> getProductCustomers(Long id) {
        List<Customer> tmpList = null;
        Product product;
        try {
            session = factory.getFactory().getCurrentSession();
            session.beginTransaction();
            product = session.get(Product.class, id);
            tmpList = product.getCustomers();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tmpList;
    }
}
