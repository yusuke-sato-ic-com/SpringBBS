package jp.co.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jp.co.practice.entity.User;

public interface UserMapper {

	// 一覧表示用ユーザー全取得
	List<User> getAllUser(); // 戻り値のデータ型に注意

	// ログインユーザー取得
	User getLoginUser(String loginId);

	// ログインIDの重複チェック
	Integer checkOverlapLoginId(String loginId);

	// ユーザーの新規登録
	void register(User user);

	// ユーザーの利用状況編集
	void editUsing(@Param("userId") Integer userId, @Param("using") Integer using);

	// ユーザーの削除
	void deleteUser(Integer userId);

	// 編集対象のユーザー情報取得
	User getEditedUser(Integer id);

	// ユーザーの編集
	void editor(User user);

	// パスワード以外のユーザーの編集
	void exceptPassEditor(User user);

}
