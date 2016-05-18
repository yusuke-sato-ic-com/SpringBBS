package jp.co.practice.dto;

import lombok.Data;

@Data
public class UserDto {

	// 必要な情報だけ、様々な情報からかき集めたオブジェクト
	// ユーザー情報、セッション情報、その他の情報

	private Integer id;
	private String name;
	private String loginId;
	private String password;

	private Integer branchId;
	private Integer departmentId;

	private String branchName;
	private String departmentName;
}
