package jp.co.practice.form;

import lombok.Data;

@Data
public class NewCommentForm {

	private String text;
	private Integer messageId;
	private Integer userId;

}
