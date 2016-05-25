package jp.co.practice.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

private Integer id;
private Integer userId;
private Integer messageId;
private String text;
private Date insertDate;
private Date updateDate;
private String name;
private Integer branchId;
private Integer departmentId;

}
