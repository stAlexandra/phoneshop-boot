package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.exception.PhoneNotFoundException;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

import javax.annotation.Resource;

@Service
public class PhoneService {
    @Resource
    private PhoneRepository phoneRepository;

    public Phone getPhoneById(final Long phoneId) {
        return phoneRepository.findById(phoneId).orElseThrow(() -> new PhoneNotFoundException(Long.toString(phoneId)));
    }

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    public Page<Phone> getPhonesPage(String query, Pageable pageable) {
        if (StringUtils.hasText(query)) {
            return phoneRepository.findByModelContainingIgnoreCaseOrBrandContainingIgnoreCase(query, query, pageable);
        }
        return phoneRepository.findAll(pageable);
    }
    
    public Page<Phone> getPhonesPage(String query, BigDecimal fromPrice, Pageable pageable) {
        if (StringUtils.hasText(query)) {
            return phoneRepository.findByQueryAndPriceFrom(query, fromPrice, pageable);
        }
        return phoneRepository.findByPriceGreaterThanEqual(fromPrice, pageable);
    }

    public Page<Phone> getPhonesPage(String query, BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable) {
        if (StringUtils.hasText(query)) {
            return phoneRepository.findByQueryAndInPriceRange(query, fromPrice, toPrice, pageable);
        }
        return phoneRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(fromPrice, toPrice, pageable);
    }
}
