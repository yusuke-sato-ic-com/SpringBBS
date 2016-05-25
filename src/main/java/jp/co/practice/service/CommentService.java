package jp.co.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.practice.entity.Comment;
import jp.co.practice.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;

	// 投稿メッセージの一覧表示用
	public List<Comment> getAllComment() {
		List<Comment> AllCommentEntity = commentMapper.getAllComment();
		return AllCommentEntity;
	}

	// 削除する投稿に該当するコメントを削除
	public void deleteCommentsOfMessage(Integer commentId) {
		commentMapper.deleteCommentsOfMessage(commentId);
	}

	// コメントを削除
	public void deleteComment(Integer commentId) {
		commentMapper.deleteComment(commentId);
	}

	// コメントを投稿
	public void postComment(Comment comment) {
		commentMapper.postComment(comment);
	}

	// Ajax ユーザIDに該当する最新コメントのIDを取得
	public Integer getLatestCommentId(Integer userId) {
		Integer latestCommentId = commentMapper.getLatestCommentId(userId);
		return latestCommentId;
	}

	// Ajax 最新コメント
	public Comment getLatestComment(Integer latestCommentId) {
		Comment latestComment = commentMapper.getLatestComment(latestCommentId);
		return latestComment;
	}

}
