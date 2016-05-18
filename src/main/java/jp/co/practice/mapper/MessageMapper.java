package jp.co.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jp.co.practice.entity.Message;

public interface MessageMapper {

	// 投稿一覧取得
	List<Message> getAllMessage(); // 戻り値のデータ型に注意

	// 新規投稿のカテゴリー取得
	List<Message> getCategories();

	// 新規投稿
	void postNewMessage(Message message);

	// 投稿日時 max - min 取得
	List<Message> getDate();

	// 絞り込み後一覧
	List<Message> getDefinedMessages(@Param("categoryName") String categoryName,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	List<Message> getNoCategoryDefinedMessages(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	// 投稿削除
	void deleteMessage(Integer messageId);
}
