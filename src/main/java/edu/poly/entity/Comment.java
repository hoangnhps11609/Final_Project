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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Comments")
public class Comment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	Integer id;
	
	@NotEmpty
	@Max(value = 5)
	Integer rate;
	
	@Length(max = 500)
	String content;
	
	@NotNull
	boolean status;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	@UpdateTimestamp
	Date createDate = new Date();
	
	@Length(max = 100)
	String photo;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	
	
	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
}
