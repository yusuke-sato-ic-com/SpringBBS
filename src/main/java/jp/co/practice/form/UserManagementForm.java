package jp.co.practice.form;

import lombok.Data;

@Data
public class UserManagementForm {

	// Web上の画面の入力、出力情報を保有するイメージ

	private String name;
	private String loginId;
	private String password;

	private Integer branchId;
	private Integer departmentId;

	private String branchName;
	private String departmentName;

	private Integer userId;
	private String using;

	private String delete;
	private String edit;
}
