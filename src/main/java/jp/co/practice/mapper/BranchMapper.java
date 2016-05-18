package jp.co.practice.mapper;

import java.util.List;

import jp.co.practice.entity.Branch;

public interface BranchMapper {

	// 支店一覧を取得
	List<Branch> getBranches();
}
