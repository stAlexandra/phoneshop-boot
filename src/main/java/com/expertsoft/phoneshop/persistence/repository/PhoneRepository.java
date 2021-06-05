package com.expertsoft.phoneshop.persistence.repository;

import java.math.BigDecimal;

import com.expertsoft.phoneshop.persistence.model.Phone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Page<Phone> findByModelContainingIgnoreCaseOrBrandContainingIgnoreCase(String model, String brand, Pageable pageable);

    Page<Phone> findByPriceGreaterThanEqual(BigDecimal fromPrice, Pageable pageable);
    
    Page<Phone> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);

    @Query("select p from #{#entityName} p where ( lower(p.brand) like lower(concat('%',:query,'%')) " +
            "OR lower(p.model) like lower(concat('%',:query,'%')) )" +
            "AND p.price >= :fromPrice")
    Page<Phone> findByQueryAndPriceFrom(String query, BigDecimal fromPrice, Pageable pageable);

    @Query("select p from #{#entityName} p where ( lower(p.brand) like lower(concat('%',:query,'%')) " +
            "OR lower(p.model) like lower(concat('%',:query,'%')) )" +
            "AND p.price >= :fromPrice AND p.price <= :toPrice")
    Page<Phone> findByQueryAndInPriceRange(String query, BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);
}
