package br.com.iteris.loja.vo;

import java.time.LocalDate;

public class SalesReportVo {

    private String nameProduct;
    private Long totalValueSales;
    private LocalDate dateLastSale;

    public SalesReportVo(String nameProduct, Long totalValueSales, LocalDate dateLastSale) {
        this.nameProduct = nameProduct;
        this.totalValueSales = totalValueSales;
        this.dateLastSale = dateLastSale;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public Long getTotalValueSales() {
        return totalValueSales;
    }

    public LocalDate getDateLastSale() {
        return dateLastSale;
    }

    @Override
    public String toString() {
        return "SalesReportVo{" +
                "nameProduct='" + nameProduct + '\'' +
                ", totalValueSales=" + totalValueSales +
                ", dateLastSale=" + dateLastSale +
                '}';
    }
}
