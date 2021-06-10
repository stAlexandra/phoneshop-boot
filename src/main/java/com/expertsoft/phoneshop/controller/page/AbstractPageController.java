package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

@Controller
public class AbstractPageController {

	private static final String USER_NAME = "userName";

	@Resource
	private UserService userService;

	@ModelAttribute(USER_NAME)
	public String getCurrentUserName(Model model) {
		return userService.getCurrentUserDisplayName();
	}
}
