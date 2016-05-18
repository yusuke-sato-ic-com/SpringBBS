package jp.co.practice.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Message {
	// テーブルのカラムを格納するクラス。Beanのイメージ。

	private String name;
	private Integer id;
	private Integer userId;
	private Integer branchId;
	private Integer departmentId;

	private String title;
	private String category;
	private String text;

	private Date insertDate;
	private Date updateDate;

	private Date minDate;
	private Date maxDate;

}
