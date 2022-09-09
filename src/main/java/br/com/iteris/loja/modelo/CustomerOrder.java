package br.com.iteris.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total_amount")
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public CustomerOrder() {
    }

    public CustomerOrder(Customer customer) {
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        item.setCustomerOrder(this);
        this.items.add(item);
        this.totalAmount = this.totalAmount.add(item.getValue());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
