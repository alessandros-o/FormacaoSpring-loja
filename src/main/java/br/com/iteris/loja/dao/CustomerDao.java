package br.com.iteris.loja.dao;

import br.com.iteris.loja.modelo.Customer;
import br.com.iteris.loja.modelo.Product;

import javax.persistence.EntityManager;

public class CustomerDao {

    private EntityManager em;

    public CustomerDao(EntityManager em) {
        this.em = em;
    }

    public void create(Customer customer) {
        this.em.persist(customer);
    }

    public Customer searchById(Long id) {
        return em.find(Customer.class, id);
    }

}
