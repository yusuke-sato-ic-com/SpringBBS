package jp.co.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.practice.entity.Branch;
import jp.co.practice.mapper.BranchMapper;

@Service
public class BranchService {

	@Autowired
	private BranchMapper branchMapper;

	// ユーザー管理画面の一覧表示用
	public List<Branch> getBranches() {
		List<Branch> branches = branchMapper.getBranches();
		return branches;
	}

}
