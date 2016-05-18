package jp.co.practice.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.practice.entity.Department;

@Service
public interface DepartmentMapper {

	// 部署一覧を取得
	List<Department> getDepartments();
}
