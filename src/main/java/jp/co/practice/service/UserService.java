package jp.co.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.practice.entity.User;
import jp.co.practice.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// ユーザー管理画面の一覧表示用
	public List<User> getAllUsers() {
		List<User> allUserEntity = userMapper.getAllUser();
		return allUserEntity;
	}

	// ログインIDの重複チェック signup
	public boolean checkOverlapLoginId(String loginId) {

		Integer count = userMapper.checkOverlapLoginId(loginId);
		if(count == 0) {
			return true;
		} else if (2 <= count) {
			throw new IllegalStateException("2 <= count");
		} else {
			return false;
		}
	}

	// ログインIDの重複チェック edit
	public boolean checkOverlapLoginId(User user, String loginId) {

		Integer count = userMapper.checkOverlapLoginId(loginId);
		if(count == 0 || user.getLoginId().equals(loginId)) {
			return true;
		} else if (2 <= count) {
			throw new IllegalStateException("2 <= count");
		} else {
			return false;
		}
	}

	// ユーザー新規登録
	public void register(User user) {
		// パスワードを暗号化してDBへ
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String HashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(HashedPassword);
		userMapper.register(user);
	}

	// ユーザーの利用状況編集
	public void editor(Integer userId, Integer using) {
		userMapper.editUsing(userId, using);
	}

	// ユーザーの削除
	public void editor(Integer userId) {
		userMapper.deleteUser(userId);
	}

	// 編集対象のユーザー情報取得
	public User getEditedUser(Integer id) {
		User editedUserEntity = userMapper.getEditedUser(id);
		return editedUserEntity;

	}

	// ユーザーの編集
	public void editor(User user) {
		if(user.getPassword() == null) {
			userMapper.exceptPassEditor(user);
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String HashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(HashedPassword);
			userMapper.editor(user);
		}
	}

}
