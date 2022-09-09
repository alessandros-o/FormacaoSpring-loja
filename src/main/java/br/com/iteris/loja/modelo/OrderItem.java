package br.com.iteris.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "price_unitary")
    private BigDecimal priceUnitary;
    private int quantity;

    @ManyToOne
    private CustomerOrder customerOrder;

    @ManyToOne
    private Product product;

    public OrderItem() {
    }

    public OrderItem(int quantity, CustomerOrder customerOrder, Product product) {
        this.quantity = quantity;
        this.customerOrder = customerOrder;
        this.priceUnitary = product.getPrice();
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPriceUnitary() {
        return priceUnitary;
    }

    public void setPriceUnitary(BigDecimal priceUnitary) {
        this.priceUnitary = priceUnitary;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getValue() {
        return priceUnitary.multiply(new BigDecimal(quantity));
    }
}
