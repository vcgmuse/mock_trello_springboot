package com.mock_trello.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mock_trello.models.Comment;

public interface CommentRepository extends CrudRepository <Comment, byte[]>{

	List<Comment> findRepliesByParentCommentId(byte[] parentCommentId);

}
