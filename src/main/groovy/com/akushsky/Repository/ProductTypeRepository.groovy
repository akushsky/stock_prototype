package com.akushsky.Repository


import com.akushsky.Entity.ProductType
import com.akushsky.Entity.ProductTypeEnum
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import javax.transaction.Transactional

/**
 * Created by akushsky on 6/5/16.
 */
@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

    /**
     * Deletes all entities where present extern provider
     */
    @Modifying
    @Transactional
    @Query(value="DELETE FROM producttype pt WHERE pt.id IN (SELECT producttypes_id FROM producttype_externprovider) AND pt.id NOT IN (SELECT product_type_id FROM product)", nativeQuery = true)
    void deleteAllExtern()
}