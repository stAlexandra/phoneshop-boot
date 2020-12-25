package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.dto.PhoneDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {
    Page<PhoneDto> getPhonesPage(Pageable pageable);
}
