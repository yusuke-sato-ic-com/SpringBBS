package jp.co.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.practice.entity.Department;
import jp.co.practice.mapper.DepartmentMapper;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	// ユーザー管理画面の一覧表示用
	public List<Department> getDepartments() {
		List<Department> departments = departmentMapper.getDepartments();
		return departments;

	}
}
