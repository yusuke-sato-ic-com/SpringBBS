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

import jp.co.practice.entity.Message;
import jp.co.practice.form.NewMessageForm;
import jp.co.practice.service.MessageService;

@Controller
public class NewMessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/newMessage", method = RequestMethod.GET)
	public String newMessage(Model model) {
		NewMessageForm form = new NewMessageForm();
		// プルダウン用カテゴリー一覧
		List<Message> categories = messageService.getCategories();
		model.addAttribute("NewMessageForm", form);
		model.addAttribute("categories", categories);
		model.addAttribute("title", "新規投稿画面");
		return "/newMessage";
	}

	@RequestMapping(value = "/newMessage", method = RequestMethod.POST)
	public String newMessage(@ModelAttribute NewMessageForm form, Model model) {
		model.addAttribute("NewMessageForm", form);
		// プルダウン用カテゴリー一覧
		List<Message> categories = messageService.getCategories();

		Message message = new Message();
		message.setTitle(form.getTitle());
		message.setCategory(form.getCategory());
		message.setText(form.getText());
		message.setUserId(form.getUserId());

		List<String> errorMessages = new ArrayList<String>();

		if(isValid(form, errorMessages) == true) {
			messageService.newMessage(message);
			return "redirect:./";
		} else {
			model.addAttribute("NewMessageForm", form);
			model.addAttribute("categories", categories);
			model.addAttribute("message", message);
			model.addAttribute("errorMessages", errorMessages);
			model.addAttribute("title", "新規投稿画面");
			return "/newMessage";
		}
	}

	//バリデーションエラー
	private boolean isValid(NewMessageForm form, List<String> messages) {

		String title = form.getTitle();
		String category = form.getCategory();
		String text = form.getText();

		if(StringUtils.isEmpty(title) == true) {
			messages.add("件名を入力してください。");
		} else {
			int digit = title.length();
			if(digit > 50) {
				messages.add("件名は50文字以下で入力してください。");
			}
		}

		if(StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力してください。");
		} else {
			int digit = category.length();
			if(digit > 10) {
				messages.add("カテゴリーは10文字以下で入力してください。");
			}
		}

		if(StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください。");
		} else {
			int digit = text.length();
			if(digit > 1000) {
				messages.add("本文は1000文字以下で入力してください。");
			}
		}

		if(messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
