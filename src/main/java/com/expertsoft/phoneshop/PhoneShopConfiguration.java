package com.expertsoft.phoneshop;

import com.expertsoft.phoneshop.converter.PhoneConverter;
import com.expertsoft.phoneshop.dto.PhoneDto;
import com.expertsoft.phoneshop.persistence.model.Phone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.function.Function;

import static com.expertsoft.phoneshop.PhoneShopConstants.*;

@Configuration
public class PhoneShopConfiguration implements WebMvcConfigurer {

    @Bean
    public Function<Phone, PhoneDto> phoneConverter() {
        return new PhoneConverter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(ROOT_PATH, PHONE_LISTING_PATH);
    }
}
