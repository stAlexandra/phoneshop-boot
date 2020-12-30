package com.expertsoft.phoneshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONE_DETAILS_PATH;

@Controller
@RequestMapping(PHONE_DETAILS_PATH)
public class PhoneDetailsPageController {

    private static final String PHONE_DETAILS_PAGE = "phoneDetailsPage";

    @GetMapping( "/{phoneId}")
    public String getPhoneDetails(@PathVariable("phoneId") Long phoneId) {
        return PHONE_DETAILS_PAGE;
    }
}
