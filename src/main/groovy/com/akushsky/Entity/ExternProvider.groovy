package com.akushsky.Entity

import org.hibernate.annotations.ManyToAny

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Reference of extern data providers
 */
@Entity
@Table(name = "ExternProvider")
class ExternProvider extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "is_proceed")
    private Boolean isProceed;

    @ManyToMany(mappedBy = "externProviders")
    private Set<ProductType> productTypes;

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Boolean getIsProceed() {
        return isProceed
    }

    void setIsProceed(Boolean isProceed) {
        this.isProceed = isProceed
    }

    String getUrl() {
        return url
    }

    void setUrl(String url) {
        this.url = url
    }

    Set<Product> getProducts() {
        return products
    }

    void setProducts(Set<Product> products) {
        this.products = products
    }

    Set<ProductType> getProductTypes() {
        return productTypes
    }

    void setProductTypes(Set<ProductType> productTypes) {
        this.productTypes = productTypes
    }
}
