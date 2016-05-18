package jp.co.practice.form;

import lombok.Data;

@Data
public class SignupForm {
	private String name;
	private String loginId;
	private String password;
	private String confirm;

	private Integer branchId;
	private Integer departmentId;

	private String branchName;
	private String departmentName;

}
