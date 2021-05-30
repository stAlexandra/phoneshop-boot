package com.expertsoft.phoneshop.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import com.expertsoft.phoneshop.persistence.model.Phone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
public class PhoneRepositoryIntegrationTests {
    private static final BigDecimal PHONE_PRICE = BigDecimal.valueOf(1000);
    private static Pageable pageable;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeAll
    private static void setUp() {
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void whenFindAll_thenReturnPhonePage() {
        Page<Phone> phonePage = phoneRepository.findAll(pageable);
        
        assertTrue(phonePage.hasContent());
    }

    @Test
    public void whenFindById_thenReturnPhone() {
        Long phoneId = entityManager.persistAndGetId(getTestPhone(), Long.class);

        Optional<Phone> found = phoneRepository.findById(phoneId);

        assertTrue(found.isPresent());
        assertEquals(phoneId, found.get().getId());
    }

    @Test
    public void givenIdNotValid_whenFindById_thenThrowException() {
        Long phoneId = null;

        assertThrows(InvalidDataAccessApiUsageException.class, () -> phoneRepository.findById(phoneId));
    }

    @Test
    public void givenPhoneNotExist_whenFindById_thenPhoneNotPresent() {
        Long phoneId = 1234L;
        
        Optional<Phone> found = phoneRepository.findById(phoneId);

        assertFalse(found.isPresent());
    }

    private Phone getTestPhone() {
        Phone testPhone = new Phone();
        testPhone.setBrand("Samsung");
        testPhone.setModel("3000x");
        testPhone.setDescription("This is a test phone.");
        testPhone.setPrice(PHONE_PRICE);
        return testPhone;
    }
}
