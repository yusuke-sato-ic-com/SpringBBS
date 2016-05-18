package jp.co.practice.form;

import lombok.Data;

@Data
public class UserEditForm {

	// Web上の画面の入力、出力情報を保有するイメージ

	private Integer userId;
	private String name;
	private String loginId;
	private String password;
	private String confirm;

	private Integer branchId;
	private Integer departmentId;

	private String branchName;
	private String departmentName;

	private String edit;

}
