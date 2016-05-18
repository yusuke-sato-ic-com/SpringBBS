package jp.co.practice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.practice.entity.Branch;
import jp.co.practice.entity.Department;
import jp.co.practice.entity.User;
import jp.co.practice.form.SignupForm;
import jp.co.practice.service.BranchService;
import jp.co.practice.service.DepartmentService;
import jp.co.practice.service.UserService;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		SignupForm form = new SignupForm();
		model.addAttribute("signupForm", form);
		model.addAttribute("title", "ユーザー登録画面");

		// 支店、部署のプルダウン用
		List<Branch> branches = branchService.getBranches();
		List<Department> departments = departmentService.getDepartments();

		model.addAttribute("branches", branches);
		model.addAttribute("departments", departments);
		return "/user/signup";

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String getSignupForm(@ModelAttribute SignupForm form, Model model){

		// 入力されたユーザー情報を格納
		User user = new User();
		user.setName(form.getName());
		user.setLoginId(form.getLoginId());
		user.setPassword(form.getPassword());
		user.setBranchId(form.getBranchId());
		user.setDepartmentId(form.getDepartmentId());

		List<String> errorMessages = new ArrayList<String>();

		// 支店、部署のプルダウン用
		List<Branch> branches = branchService.getBranches();
		List<Department> departments = departmentService.getDepartments();

		if(isValid(form, errorMessages)) {
			userService.register(user);
			return "redirect:/userManagement";
		} else {
			model.addAttribute("title", "ユーザー登録画面");
			model.addAttribute("user", user);
			model.addAttribute("errorMessages", errorMessages);
			model.addAttribute("branches", branches);
			model.addAttribute("departments", departments);
			return "/user/signup";
		}

	}

	// バリデーションチェック
	private boolean isValid(SignupForm form, List<String> messages) {

		if(StringUtils.isEmpty(form.getName()) == true) {
			messages.add("名前を入力してください。");
		}
		if(StringUtils.isEmpty(form.getLoginId()) == true) {
			messages.add("ログインIDを入力してください。");
		} else if (!form.getLoginId().matches("^\\w{6,20}$")) {
			messages.add("ログインIDは半角英数字6～20文字で入力してください。");
		}

		//TODO ログインIDの重複チェック
		if(!userService.checkOverlapLoginId(form.getLoginId())) {
			messages.add("ログインIDがすでに使用されています。");
		}

		if(StringUtils.isEmpty(form.getPassword()) == true) {
			messages.add("パスワードを入力してください。");
		} else if(StringUtils.isEmpty(form.getConfirm()) == true) {
			messages.add("パスワード(確認用)も入力してください。");
		} else if((!form.getConfirm().equals(form.getPassword()) == true)) {
			messages.add("パスワードは同じものを入力してください。");
		} else if (!form.getPassword().matches("^[a-zA-Z0-9-/:-@\\[-\\`\\{-\\~]{6,20}$")) {
			messages.add("パスワードは半角英数字6～20文字で入力してください。");
		}

		if((form.getBranchId()) == 0) {
			messages.add("所属支店を選択してください。");
		}
		if((form.getDepartmentId()) == 0) {
			messages.add("所属部署を選択してください。");
		}

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

}
