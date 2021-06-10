package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.dto.UserDto;
import com.expertsoft.phoneshop.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.ADMIN_PATH;

@Controller
@RequestMapping(ADMIN_PATH)
public class AdminPageController extends AbstractPageController {

    private static final String ADMIN_PAGE = "admin/adminPanelPage";
    private static final String USERS = "users";
    
    @Resource
    private AdminService adminService;

    @GetMapping
    public String getAdminPanel(Model model, Pageable pageable) {
        Page<UserDto> users = adminService.getAllRegisteredUsers(pageable);
        model.addAttribute(USERS, users);
        return ADMIN_PAGE;
    }
}
