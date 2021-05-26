package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.exception.PhoneNotFoundException;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    public Phone getPhoneById(final Long phoneId) {
        return phoneRepository.findById(phoneId).orElseThrow(() -> new PhoneNotFoundException(Long.toString(phoneId)));
    }
}
