package jp.co.practice.form;

import lombok.Data;

@Data
public class HomeForm {

	private String categoryName;

	private String fromDate;
	private String toDate;

	private Integer messageId;
	private Integer commentId;

}
