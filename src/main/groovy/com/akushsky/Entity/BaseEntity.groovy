package com.akushsky.Entity

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * Standard fields for all entities
 */
@MappedSuperclass
class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id
    @Column(name = "created")
    private Date created = new Date()
    @Column(name = "modified")
    private Date modified = new Date()
    @Column(name = "deleted")
    private Boolean deleted = false
    @Column(name = "relevant")
    private Boolean relevant = true

    BigInteger getId() {
        return id
    }

    void setId(BigInteger id) {
        this.id = id
    }

    Date getCreated() {
        return created
    }

    void setCreated(Date created) {
        this.created = created
    }

    Date getModified() {
        return modified
    }

    void setModified(Date modified) {
        this.modified = modified
    }

    Boolean getDeleted() {
        return deleted
    }

    void setDeleted(Boolean deleted) {
        this.deleted = deleted
    }

    Boolean getRelevant() {
        return relevant
    }

    void setRelevant(Boolean relevant) {
        this.relevant = relevant
    }
}
