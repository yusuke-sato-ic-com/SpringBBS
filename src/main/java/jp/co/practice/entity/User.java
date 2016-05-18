package jp.co.practice.entity;

import lombok.Data;

@Data
public class User {

	// テーブルのカラムを格納するクラス。Beanのイメージ。
	private Integer id;
	private String name;
	private String loginId;
	private String password;

	private String confirm;

	private Integer branchId;
	private Integer departmentId;

	private String branchName;
	private String departmentName;

	private Integer using;
}
