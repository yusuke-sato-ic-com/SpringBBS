package jp.co.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.practice.entity.Message;
import jp.co.practice.mapper.MessageMapper;

@Service
public class MessageService {

	@Autowired
	private MessageMapper messageMapper;

	// 投稿メッセージの一覧表示
	public List<Message> getAllMessage() {
		List<Message> allMessageEntity = messageMapper.getAllMessage();
		for(int i = 0; i < allMessageEntity.size(); i++) {
			String text = allMessageEntity.get(i).getText().replaceAll("\\r\\n", "<br />");
			allMessageEntity.get(i).setText(text);
		}
		return allMessageEntity;
	}

	// 新規投稿のカテゴリー一覧取得
	public List<Message> getCategories() {
		List<Message> Categories = messageMapper.getCategories();
		return Categories;
	}

	// 新規投稿
	public void newMessage(Message message) {
		messageMapper.postNewMessage(message);
	}

	// 投稿日付を取得
	public List<Message> getDate() {
		 List<Message> date = messageMapper.getDate();
		return date;
	}

	// 絞り込み一覧取得
	public List<Message> getDefinedMessages(String categoryName, String fromDate, String toDate) {
		List<Message> definedMessagesEntity;
		if(categoryName == null) {
			definedMessagesEntity = messageMapper.getNoCategoryDefinedMessages(fromDate, toDate);
		} else {
			definedMessagesEntity = messageMapper.getDefinedMessages(categoryName, fromDate, toDate);
		}

		for(int i = 0; i < definedMessagesEntity.size(); i++) {
			String text = definedMessagesEntity.get(i).getText().replaceAll("\\r\\n", "<br />");
			definedMessagesEntity.get(i).setText(text);
		}
		return definedMessagesEntity;
	}

	// 投稿削除
	public void deleteMessage(Integer messageId) {
		messageMapper.deleteMessage(messageId);
	}

}
