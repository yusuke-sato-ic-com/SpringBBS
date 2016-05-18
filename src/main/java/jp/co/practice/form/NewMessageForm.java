package jp.co.practice.form;

import lombok.Data;

@Data
public class NewMessageForm {

	private Integer userId;
	private String title;
	private String category;
	private String text;

}
