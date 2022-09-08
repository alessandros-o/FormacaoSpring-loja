package br.com.iteris.loja.testes;

import br.com.iteris.loja.dao.CategoryDao;
import br.com.iteris.loja.dao.ProductDao;
import br.com.iteris.loja.modelo.Category;
import br.com.iteris.loja.modelo.Product;
import br.com.iteris.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductRegistration {
    public static void main(String[] args) {
        create();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);

        Product product = productDao.searchById(1L);
        System.out.println(product.getPrice());

        List<Product> allProducts = productDao.searchAll();
        allProducts.forEach(p -> System.out.println(p.getName()));

        List<Product> allProductsName = productDao.searchByName("Xiaomi Redmi");
        allProductsName.forEach(p -> System.out.println(p.getName()));

        List<Product> allProductsPerCategory = productDao.searchByCategoryName("CELULARES");
        allProductsPerCategory.forEach(p -> System.out.println(p.getName()));

        BigDecimal productPrice = productDao.searchPriceByProductName("Xiaomi Redmi");
        System.out.println(productPrice);
    }

    private static void create() {
        Category celulares = new Category("CELULARES");
        Product celular = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();

        categoryDao.create(celulares);
        productDao.registration(celular);

        em.getTransaction().commit();
        em.close();
    }
}
