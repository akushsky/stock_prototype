package com.akushsky.Repository

import com.akushsky.Entity.ExternProvider
import com.akushsky.Entity.ProductType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Created by akushsky on 6/5/16.
 */
@Repository
public interface ExternProviderRepository extends CrudRepository<ExternProvider, Long> {
}