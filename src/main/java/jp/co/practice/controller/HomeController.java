package jp.co.practice.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.practice.entity.Branch;
import jp.co.practice.entity.Comment;
import jp.co.practice.entity.Department;
import jp.co.practice.entity.Message;
import jp.co.practice.form.HomeForm;
import jp.co.practice.service.BranchService;
import jp.co.practice.service.CommentService;
import jp.co.practice.service.DepartmentService;
import jp.co.practice.service.MessageService;

@Controller
public class HomeController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private BranchService branchService;
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute HomeForm form, Model model) {
		model.addAttribute("homeForm", form);
		model.addAttribute("title", "ホーム画面");

		// プルダウン用カテゴリー一覧
		List<Message> categories = messageService.getCategories();

		// 支店、部署のプルダウン用
		List<Branch> branches = branchService.getBranches();
		List<Department> departments = departmentService.getDepartments();

		// 投稿、コメント一覧
		List<Message> messages = messageService.getAllMessage();
		List<Comment> comments = commentService.getAllComment();

		// DBのminDate,maxDateを取得
		List<Message> date = messageService.getDate();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String minDate = df.format(date.get(0).getMinDate());
		String maxDate = df.format(date.get(0).getMaxDate());

		if((form.getFromDate() == "") && (form.getToDate() == "") && (form.getCategoryName().equals("すべて"))) {
			return "redirect:./";
		}

		String fromDate;
		if(form.getFromDate() == "") {
			fromDate = minDate;
		} else {
			fromDate = (form.getFromDate());
		}
		String toDate;
		if(form.getToDate() == "") {
			toDate = maxDate;
		} else {
			toDate = (form.getToDate());
		}

		String categoryName;
		if((form.getCategoryName()) != null){
			categoryName = form.getCategoryName();
			//TODO すべての部分をなんとかする、
			if(categoryName.equals("すべて")) {
				categoryName = null;
			}
			messages = messageService.getDefinedMessages(categoryName, fromDate, toDate);
		}

		model.addAttribute("categories", categories);
		model.addAttribute("branches", branches);
		model.addAttribute("departments", departments);
		model.addAttribute("comments", comments);
		model.addAttribute("messages", messages);
		return "/home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String Home(@ModelAttribute HomeForm form, Model model) {

		//投稿削除機能
		if ((form.getMessageId()) != null){ // 削除対象のmessageID
			messageService.deleteMessage(form.getMessageId());
			commentService.deleteCommentsOfMessage(form.getMessageId()); // 対応するコメントも削除
		}
		if ((form.getCommentId()) != null){ // 削除対象のcommentID
			commentService.deleteComment(form.getCommentId());
		}
		return "redirect:./";

	}

}

