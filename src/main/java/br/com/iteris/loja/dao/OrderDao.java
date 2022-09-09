package br.com.iteris.loja.dao;

import br.com.iteris.loja.modelo.CustomerOrder;
import br.com.iteris.loja.modelo.Product;
import br.com.iteris.loja.vo.SalesReportVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class OrderDao {

    private EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void create(CustomerOrder customerOrder) {
        this.em.persist(customerOrder);
    }

    public BigDecimal totalValueSold() {
        String jpql = "SELECT SUM(c.totalAmount) FROM CustomerOrder c";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<SalesReportVo> salesReport() {
        String jpql = "SELECT new br.com.iteris.loja.vo.SalesReportVo( " +
                "p.name, " +
                "SUM(i.quantity), " +
                "MAX(o.date)) " +
                "FROM CustomerOrder o " +
                "JOIN o.items i " +
                "JOIN i.product p " +
                "GROUP BY p.name " +
                "ORDER BY i.quantity DESC";
        return em.createQuery(jpql, SalesReportVo.class).getResultList();
    }

}
