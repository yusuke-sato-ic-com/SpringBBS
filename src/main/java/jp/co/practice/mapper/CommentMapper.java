package jp.co.practice.mapper;

import java.util.List;

import jp.co.practice.entity.Comment;

public interface CommentMapper {
	List<Comment> getAllComment(); // 戻り値のデータ型に注意

	void deleteCommentsOfMessage(Integer commentId);

	void deleteComment(Integer commentId);

	void postComment(Comment comment);
}
