package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.poly.entity.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
