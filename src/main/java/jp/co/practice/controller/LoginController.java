package jp.co.practice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.practice.entity.User;
import jp.co.practice.form.LoginForm;
import jp.co.practice.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		LoginForm form = new LoginForm();
		model.addAttribute("loginForm", form);
		model.addAttribute("title", "掲示板システム");
		return "login";
	}

	/*
	 * form:formのmodelAttributeに指定する値と、
	 * 実際にControllerでバインドさせるFormオブジェクトは名前を一致させる
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLogin(@ModelAttribute LoginForm form, Model model, HttpServletRequest request) {
		model.addAttribute("LoginForm", form);
		String loginId = form.getLoginId();
		String password = form.getPassword();

		HttpSession session = request.getSession();
		List<String> errorMessages = new ArrayList<String>();

		if(isValid(request, errorMessages, loginId, password) == true) {
			User user = loginService.getLoginUser(loginId, password);
			if(user == null || user.getUsing() == 0) {
				session.setAttribute("errorMessages", errorMessages);
				errorMessages.add("ログインに失敗しました");
				return "redirect:/login";
			} else {
				session.setAttribute("loginUser", user);
				return "redirect:./";
			}
		} else {
			session.setAttribute("errorMessages", errorMessages);
			return "redirect:/login";
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> errorMessages, String loginId, String password) {

		if(StringUtils.isEmpty(loginId) == true) {
			errorMessages.add("ログインIDを入力してください。");
		}

		if(StringUtils.isEmpty(password) == true) {
			errorMessages.add("パスワードを入力してください。");
		}

		if(errorMessages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
