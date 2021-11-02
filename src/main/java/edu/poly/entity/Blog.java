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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Blogs")
public class Blog implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	Integer id;
	
	@NotBlank
	@Length(max = 50)
	String name;
	
	@NotBlank
	String content;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	@PastOrPresent
	@NotEmpty
	Date createDate = new Date();
	
	@NotBlank
	@Length(max = 50)
	String images;
	
	@ManyToOne

	@JoinColumn(name = "Username")
	Account account;	
	
	
	@ManyToOne
	@JoinColumn(name = "blogcategoryid")
	BlogCategory blogCategory;
	

}
