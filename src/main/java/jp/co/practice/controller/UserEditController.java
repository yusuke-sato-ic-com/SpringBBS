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
import jp.co.practice.form.UserEditForm;
import jp.co.practice.service.BranchService;
import jp.co.practice.service.DepartmentService;
import jp.co.practice.service.UserService;

@Controller
public class UserEditController {

	@Autowired
	private UserService userService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/userEdit", method = RequestMethod.GET)
	public String userEdit(@ModelAttribute UserEditForm form, Model model){

		User user = userService.getEditedUser(form.getUserId());

		List<Branch> branches = branchService.getBranches();
		List<Department> departments = departmentService.getDepartments();

		model.addAttribute("title", "ユーザー編集画面");
		model.addAttribute("user", user);
		model.addAttribute("userEditForm", form);
		model.addAttribute("branches", branches);
		model.addAttribute("departments", departments);

		return "/user/userEdit";
	}

	@RequestMapping(value = "/userEdit", method = RequestMethod.POST)
	public String getUserEditForm(@ModelAttribute UserEditForm form, Model model){

		List<Branch> branches = branchService.getBranches();
		List<Department> departments = departmentService.getDepartments();

		model.addAttribute("title", "ユーザー編集画面");

		Integer userId = form.getUserId();
		User user = new User();
		user.setId(userId);
		user.setLoginId(form.getLoginId());
		user.setPassword(form.getPassword());
		user.setName(form.getName());
		user.setBranchId(form.getBranchId());
		user.setDepartmentId(form.getDepartmentId());

		List<String> errorMessages = new ArrayList<String>();

		if(isValid(user, form, errorMessages) == true) {
			System.out.println("user.getPassword() : " + user.getPassword());
			userService.editor(user);
			return "redirect:/userManagement";
		} else {
			model.addAttribute("title", "ユーザー編集画面");
			model.addAttribute("errorMessages", errorMessages);
			model.addAttribute("user", user);
			model.addAttribute("userEditForm", form);
			model.addAttribute("branches", branches);
			model.addAttribute("departments", departments);
			return "/user/userEdit";
		}

	}

	private boolean isValid(User user, UserEditForm form, List<String> messages) {

		User edtedUser = userService.getEditedUser(form.getUserId());

		if(StringUtils.isEmpty(form.getName()) == true) {
			messages.add("名前を入力してください。");
		}

		if(StringUtils.isEmpty(form.getLoginId()) == true) {
			messages.add("ログインIDを入力してください。");
		} else if (!form.getLoginId().matches("^\\w{6,20}$")) {
			messages.add("ログインIDは半角英数字6～20文字で入力してください。");
		}
		if(!userService.checkOverlapLoginId(edtedUser, form.getLoginId())) {
			messages.add("ログインIDがすでに使用されています。");
		}

		if(StringUtils.isEmpty(form.getPassword()) == true && StringUtils.isEmpty(form.getConfirm()) == true) {
			//TODO 現在のパスワードを入力
			user.setPassword(null);
		} else if(StringUtils.isEmpty(form.getPassword()) == true) {
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
