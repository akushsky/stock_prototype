package com.akushsky.Entity

import javax.persistence.*
/**
 * Reference of possible product types
 */
@Entity
@Table(name = "ProductType")
class ProductType extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum type;

    @OneToMany(mappedBy = "productType")
    private Set<Product> products;

    @ManyToMany
    @JoinColumn(name = "extern_provider_id")
    private Set<ExternProvider> externProviders;

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    ProductTypeEnum getType() {
        return type
    }

    void setType(ProductTypeEnum type) {
        this.type = type
    }

    Set<Product> getProducts() {
        return products
    }

    void setProducts(Set<Product> products) {
        this.products = products
    }

    Set<ExternProvider> getExternProviders() {
        return externProviders
    }

    void setExternProviders(Set<ExternProvider> externProviders) {
        this.externProviders = externProviders
    }
}
