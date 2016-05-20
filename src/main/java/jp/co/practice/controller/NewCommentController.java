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
import jp.co.practice.entity.Comment;
import jp.co.practice.entity.Department;
import jp.co.practice.entity.Message;
import jp.co.practice.form.NewCommentForm;
import jp.co.practice.service.BranchService;
import jp.co.practice.service.CommentService;
import jp.co.practice.service.DepartmentService;
import jp.co.practice.service.MessageService;

@Controller
public class NewCommentController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/newComment", method = RequestMethod.POST)
	public String newMessage(@ModelAttribute NewCommentForm form, Model model) {

		model.addAttribute("title", "ホーム画面");

		// プルダウン用カテゴリー一覧
		List<Message> categories = messageService.getCategories();

		// 支店、部署のプルダウン用
		List<Branch> branches = branchService.getBranches();
		List<Department> departments = departmentService.getDepartments();

		// 投稿、コメント一覧
		List<Message> messages = messageService.getAllMessage();
		List<Comment> comments = commentService.getAllComment();

		List<String> errorMessages = new ArrayList<String>();

		Comment comment = new Comment();
		comment.setUserId(form.getUserId());
		comment.setMessageId(form.getMessageId());
		comment.setText(form.getText());

		if(isValid(form, errorMessages) == true) {
			commentService.postComment(comment);
			return "redirect:./";
		} else {
			model.addAttribute("categories", categories);
			model.addAttribute("branches", branches);
			model.addAttribute("departments", departments);
			model.addAttribute("comments", comments);
			model.addAttribute("messages", messages);
			model.addAttribute("comment", comment);
			model.addAttribute("errorMessages", errorMessages);
			return "/home";
		}
	}

	//バリデーションエラー
	private boolean isValid(NewCommentForm form, List<String> errorMessages) {

		String commentText = form.getText();

		if(StringUtils.isEmpty(commentText) == true) {
			errorMessages.add("コメントを入力してください。");
		} else {
			int digit = commentText.length();
			if(digit > 500) {
				errorMessages.add("コメントは500文字以下で入力してください。");
			}
		}

		if(errorMessages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
