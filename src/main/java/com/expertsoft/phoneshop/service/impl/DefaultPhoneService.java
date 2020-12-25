package com.expertsoft.phoneshop.service.impl;

import com.expertsoft.phoneshop.dto.PhoneDto;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DefaultPhoneService implements PhoneService {

    private PhoneRepository phoneRepository;
    private Function<Phone, PhoneDto> phoneConverter;

    @Autowired
    public DefaultPhoneService(PhoneRepository phoneRepository,
                               Function<Phone, PhoneDto> phoneConverter) {
        this.phoneRepository = phoneRepository;
        this.phoneConverter = phoneConverter;
    }

    @Override
    public Page<PhoneDto> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable).map(phoneConverter);
    }
}
