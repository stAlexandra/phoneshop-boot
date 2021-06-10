package com.expertsoft.phoneshop.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
import org.springframework.util.StringUtils;

@ActiveProfiles("test")
@DataJpaTest
public class PhoneRepositoryIntegrationTests {
    private static final BigDecimal TEST_PHONE_PRICE = BigDecimal.valueOf(100);
    private static final String TEST_PHONE_BRAND = "Samsung";
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

    @Test
    public void givenPriceInsideRange_whenFindByPriceRange_thenReturnPhonesPage() {
        BigDecimal fromPrice = BigDecimal.valueOf(99);
        BigDecimal toPrice = BigDecimal.valueOf(101);
        assumeTrue(TEST_PHONE_PRICE.compareTo(fromPrice) > 0 && TEST_PHONE_PRICE.compareTo(toPrice) < 0);
        Phone phone = entityManager.persist(getTestPhone());

        Page<Phone> phonePage = phoneRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(fromPrice, toPrice, pageable);
        
        assertTrue(phonePage.hasContent());
        assertTrue(phonePage.getContent().contains(phone));
    }

    @Test
    public void givenPriceAtLowerBound_whenFindByPriceRange_thenReturnPhonesPage() {
        BigDecimal fromPrice = BigDecimal.valueOf(100);
        assumeTrue(TEST_PHONE_PRICE.compareTo(fromPrice) == 0);
        Phone phone = entityManager.persist(getTestPhone());

        Page<Phone> phonePage = phoneRepository.findByPriceGreaterThanEqual(fromPrice, pageable);
        
        assertTrue(phonePage.hasContent());
        assertTrue(phonePage.getContent().contains(phone));
    }

    @Test
    public void givenPriceAtUpperBound_whenFindByPriceRange_thenReturnPhonesPage() {
        BigDecimal fromPrice = BigDecimal.valueOf(99);
        BigDecimal toPrice = BigDecimal.valueOf(100);
        assumeTrue(TEST_PHONE_PRICE.compareTo(fromPrice) > 0 && TEST_PHONE_PRICE.compareTo(toPrice) == 0);
        Phone phone = entityManager.persist(getTestPhone());

        Page<Phone> phonePage = phoneRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(fromPrice, toPrice, pageable);
        
        assertTrue(phonePage.hasContent());
        assertTrue(phonePage.getContent().contains(phone));
    }

    @Test
    public void whenFindByBrand_thenReturnPhonePage() {
        final Phone phone = getTestPhone();
        String brand = phone.getBrand().toLowerCase();
        entityManager.persist(phone);

        Page<Phone> phonePage = phoneRepository.findByModelContainingIgnoreCaseOrBrandContainingIgnoreCase(brand, brand, pageable);

        assertTrue(phonePage.hasContent());
        assertTrue(phonePage.getContent().contains(phone));
    }

    @Test
    public void whenFindByModel_thenReturnPhonePage() {
        String model = "IphoN";

        Page<Phone> phonePage = phoneRepository.findByModelContainingIgnoreCaseOrBrandContainingIgnoreCase(model, model, pageable);

        assertTrue(phonePage.hasContent());
        assertTrue(phonePage.getContent().stream()
            .filter(phone -> StringUtils.hasLength(phone.getModel()))
            .anyMatch(phone -> phone.getModel().toLowerCase().contains(model.toLowerCase())));
    }

    @Test
    public void whenFindByQueryAndPriceRange_thenReturnPhonePage() {
        BigDecimal fromPrice = BigDecimal.valueOf(90);
        BigDecimal toPrice = BigDecimal.valueOf(150);
        assumeTrue(TEST_PHONE_PRICE.compareTo(fromPrice) >= 0 && TEST_PHONE_PRICE.compareTo(toPrice) <= 0);
        Phone phone = entityManager.persist(getTestPhone());
        
        Page<Phone> phonePage = phoneRepository.findByQueryAndInPriceRange(TEST_PHONE_BRAND, fromPrice, toPrice, pageable);

        assertTrue(phonePage.hasContent());
        assertTrue(phonePage.getContent().contains(phone)); 
    }

    private Phone getTestPhone() {
        Phone testPhone = new Phone();
        testPhone.setBrand(TEST_PHONE_BRAND);
        testPhone.setModel("3000x");
        testPhone.setDescription("This is a test phone.");
        testPhone.setPrice(TEST_PHONE_PRICE);
        return testPhone;
    }
}
