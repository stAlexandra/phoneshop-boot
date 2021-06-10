package com.expertsoft.phoneshop.facade;

import java.math.BigDecimal;
import java.util.Optional;

import javax.annotation.Resource;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.service.PhoneService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class PhoneFacade {
    @Resource
    private PhoneService phoneService;

    public Phone getPhoneById(final Long phoneId) {
        return phoneService.getPhoneById(phoneId);
    }

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneService.getPhonesPage(pageable);
    }

    public Page<Phone> getPhonesPage(String query, String fromPrice, String toPrice, Pageable pageable) {
        Optional<BigDecimal> optFromPrice = parseToBigDecimal(fromPrice);
        Optional<BigDecimal> optToPrice = parseToBigDecimal(toPrice);
        
        if (optToPrice.isPresent()) {
            return phoneService.getPhonesPage(query, optFromPrice.orElse(BigDecimal.ZERO), optToPrice.get(), pageable);
        } else if (optFromPrice.isPresent()) {
            return phoneService.getPhonesPage(query, optFromPrice.get(), pageable);
        } else {
            return phoneService.getPhonesPage(query, pageable);
        }
    }

    private Optional<BigDecimal> parseToBigDecimal(String value) {
        if (!StringUtils.hasText(value)) {
            return Optional.empty();
        }
        try {
            return Optional.of(new BigDecimal(value));
        } catch (final NumberFormatException e) {
            return Optional.empty();
        }
    }
}
