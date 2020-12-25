package com.expertsoft.phoneshop.converter;

import com.expertsoft.phoneshop.dto.PhoneDto;
import com.expertsoft.phoneshop.persistence.model.Phone;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import static com.expertsoft.phoneshop.PhoneShopConstants.IMAGE_SOURCE_PATH;

public class PhoneConverter implements Function<Phone, PhoneDto> {

    private static final String IMAGE_PLACEHOLDER = "/img/no-image.jpg";
    private static final String ZERO_PRICE = "0.00";

    @Override
    public PhoneDto apply(Phone phone) {
        PhoneDto dto = new PhoneDto();
        dto.setId(phone.getId());
        dto.setBrand(phone.getBrand());
        dto.setModel(phone.getModel());
        dto.setImageUrl(getImageUrl(phone));
        dto.setPrice(getPrice(phone));
        return dto;
    }

    private String getImageUrl(Phone phone) {
        return Optional.ofNullable(phone.getImage())
                .map(IMAGE_SOURCE_PATH::concat)
                .orElse(IMAGE_PLACEHOLDER);
    }

    private BigDecimal getPrice(Phone phone) {
        return Optional.ofNullable(phone.getPrice())
                .orElseGet(() -> new BigDecimal(ZERO_PRICE));
    }
}
