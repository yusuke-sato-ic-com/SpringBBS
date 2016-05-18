package jp.co.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.practice.entity.User;
import jp.co.practice.form.UserEditForm;
import jp.co.practice.form.UserManagementForm;
import jp.co.practice.service.UserService;

@Controller
public class UserManagementController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
	public String userManagement(Model model) {
		UserManagementForm form = new UserManagementForm();
		List<User> users = userService.getAllUsers();
		model.addAttribute("userManagementForm", form);
		model.addAttribute("title", "ユーザー登録画面");
		model.addAttribute("users", users);

		return "/user/userManagement";
	}

	@RequestMapping(value = "/userManagement", method = RequestMethod.POST)
	public String userManagementForm(@ModelAttribute UserManagementForm form, UserEditForm editForm, Model model) {

		List<User> users = userService.getAllUsers();

		// ユーザー停止・復活機能の実装
		Integer userId = form.getUserId();
		Integer using;

		// ユーザーの削除
		if(form.getDelete() != null){
			userService.editor(userId);
		} else if(form.getUsing() != null){
			if(form.getUsing().equals("ON")) {
				using = 0;
				userService.editor(userId, using);
			} else {
				using = 1;
				userService.editor(userId, using);
			}
		}
		model.addAttribute("userManagementForm", form);
		model.addAttribute("users", users);
		return "redirect:/userManagement";
	}
}
