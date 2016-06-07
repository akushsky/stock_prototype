package com.akushsky.Entity

import javax.persistence.*
/**
 * Reference of products in stock
 */
@Entity
@Table(name = "Product")
class Product extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @Column(name = "stock_count")
    private Integer stockCount;

    @Column(name = "stock_count_reserved")
    private Integer stockCountReserved;

    @Column(name = "bulk")
    private Integer bulk;

    ProductType getProductType() {
        return productType
    }

    void setProductType(ProductType productType) {
        this.productType = productType
    }

    Integer getStockCount() {
        return stockCount
    }

    void setStockCount(Integer stockCount) {
        this.stockCount = stockCount
    }

    Integer getStockCountReserved() {
        return stockCountReserved
    }

    void setStockCountReserved(Integer stockCountReserved) {
        this.stockCountReserved = stockCountReserved
    }

    Integer getBulk() {
        return bulk
    }

    void setBulk(Integer bulk) {
        this.bulk = bulk
    }
}
