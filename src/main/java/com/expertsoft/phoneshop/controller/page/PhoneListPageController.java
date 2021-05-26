package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.service.PhoneService;
import com.expertsoft.phoneshop.service.util.PaginationProperties;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";
    private static final String MAX_PAGES = "maxPages";

    @Resource
    private PhoneService phoneService;

    @Resource
    private PaginationProperties paginationProperties;

    @GetMapping
    public String getPhoneList(Model model, Pageable pageable) {
        model.addAttribute(PHONES, phoneService.getPhonesPage(pageable));
        model.addAttribute(MAX_PAGES, paginationProperties.getPlpMaxPages());
        return PHONE_LIST_PAGE;
    }
}
