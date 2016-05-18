package jp.co.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.practice.entity.User;
import jp.co.practice.mapper.UserMapper;

@Service
public class LoginService {

	@Autowired
	private UserMapper userMapper;

	// ログインユーザー情報を取得
	public User getLoginUser(String loginId, String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User loginUserEntity = userMapper.getLoginUser(loginId);
		if(passwordEncoder.matches(password, loginUserEntity.getPassword()) == false) {
			loginUserEntity = null;
		}
		return loginUserEntity;

	}
}
