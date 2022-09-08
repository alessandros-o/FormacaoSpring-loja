package br.com.iteris.loja.dao;

import br.com.iteris.loja.modelo.Category;

import javax.persistence.EntityManager;

public class CategoryDao {
    private EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public void create(Category category) {
        this.em.persist(category);
    }

    public void remove(Category category) {
        category = em.merge(category);
        this.em.remove(category);
    }
}
