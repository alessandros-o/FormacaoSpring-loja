package br.com.iteris.loja.dao;

import br.com.iteris.loja.modelo.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductDao {

    private EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public void registration(Product product) {
        this.em.persist(product);
    }

    public Product searchById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> searchAll() {
        String jpql = "SELECT p FROM Product p";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> searchByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> searchByCategoryName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.category.nameCategory = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal searchPriceByProductName(String name) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
