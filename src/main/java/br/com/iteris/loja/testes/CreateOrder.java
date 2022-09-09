package br.com.iteris.loja.testes;

import br.com.iteris.loja.dao.CategoryDao;
import br.com.iteris.loja.dao.CustomerDao;
import br.com.iteris.loja.dao.OrderDao;
import br.com.iteris.loja.dao.ProductDao;
import br.com.iteris.loja.modelo.*;
import br.com.iteris.loja.util.JPAUtil;
import br.com.iteris.loja.vo.SalesReportVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CreateOrder {
    public static void main(String[] args) {
        create();

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);
        CustomerDao customerDao = new CustomerDao(em);

        Product product = productDao.searchById(1L);
        Product product2 = productDao.searchById(2L);
        Product product3 = productDao.searchById(3L);

        Customer customer = customerDao.searchById(1L);

        em.getTransaction().begin();


        CustomerOrder customerOrder = new CustomerOrder(customer);
        customerOrder.addItem(new OrderItem(10, customerOrder, product));
        customerOrder.addItem(new OrderItem(40, customerOrder, product2));

        CustomerOrder customerOrder2 = new CustomerOrder(customer);
        customerOrder2.addItem(new OrderItem(2, customerOrder2, product3));

        OrderDao orderDao = new OrderDao(em);
        orderDao.create(customerOrder);
        orderDao.create(customerOrder2);

        em.getTransaction().commit();

        BigDecimal totalAmount = orderDao.totalValueSold();
        System.out.println("O total vendido é: " + totalAmount);

        List<SalesReportVo> report = orderDao.salesReport();
        report.forEach(System.out::println);
        em.close();
    }

    private static void create() {
        Category celulares = new Category("CELULARES");
        Category videogames = new Category("VIDEOGAMES");
        Category informatica = new Category("INFORMATICA");

        Product celular = new Product("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Product videogame = new Product("PS5", "Playstation 5", new BigDecimal("1200"), videogames);
        Product macbook = new Product("Macbook", "Macbook pro reti", new BigDecimal("3200"), informatica);

        Customer customer = new Customer("Ana", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);
        CustomerDao customerDao = new CustomerDao(em);

        em.getTransaction().begin();

        categoryDao.create(celulares);
        categoryDao.create(videogames);
        categoryDao.create(informatica);

        productDao.registration(celular);
        productDao.registration(videogame);
        productDao.registration(macbook);

        customerDao.create(customer);

        em.getTransaction().commit();
        em.close();
    }
}
