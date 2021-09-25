package edu.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Blogs")
public class Blog implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String name;
	
	String content;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	
	String images;
	
	@ManyToOne
<<<<<<< HEAD
	@JoinColumn(name = "Username")
	Account account;
	
	
	@ManyToOne
	@JoinColumn(name = "blogcategoriesid")
=======
	@JoinColumn(name = "Blogcategoryid")
>>>>>>> 070a6aa17a28a3cec6b2f353079deef5d3bfd83d
	BlogCategory blogCategory;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
}
