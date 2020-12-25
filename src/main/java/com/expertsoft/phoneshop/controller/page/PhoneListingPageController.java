package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONE_LISTING_PATH;

@Controller
@RequestMapping(PHONE_LISTING_PATH)
public class PhoneListingPageController {

    private static final String PHONE_LISTING_PAGE = "phoneListingPage";
    private static final String PHONES = "phones";

    private PhoneService phoneService;

    @Autowired
    public PhoneListingPageController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public String getPhoneListing(Model model) {
        model.addAttribute(PHONES, phoneService.getPhonesPage(Pageable.unpaged()));

        return PHONE_LISTING_PAGE;
    }
}
