package edu.poly.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.entity.Comment;
@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
