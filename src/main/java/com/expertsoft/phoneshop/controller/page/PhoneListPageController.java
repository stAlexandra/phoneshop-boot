package com.expertsoft.phoneshop.controller.page;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

import javax.annotation.Resource;

import com.expertsoft.phoneshop.facade.PhoneFacade;
import com.expertsoft.phoneshop.service.util.PaginationProperties;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController extends AbstractPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";
    private static final String MAX_PAGES = "maxPages";

    @Resource
    private PhoneFacade phoneFacade;

    @Resource
    private PaginationProperties paginationProperties;

    @ModelAttribute
    public void addMaxPagesAttribute(Model model) {
        model.addAttribute(MAX_PAGES, paginationProperties.getPlpMaxPages());
    }

    @GetMapping
    public String getPhoneListByQueryAndPriceRange(@RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "") String fromPrice,
            @RequestParam(defaultValue = "") String toPrice, 
            Model model, Pageable pageable) {
        model.addAttribute(PHONES, phoneFacade.getPhonesPage(query, fromPrice, toPrice, pageable));
        return PHONE_LIST_PAGE;
    }
}
