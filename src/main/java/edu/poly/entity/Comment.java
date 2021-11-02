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

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Comments")
public class Comment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Integer rate;
	
	String content;
	
	boolean status ;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	@UpdateTimestamp
	Date createDate = new Date();
	
	
	String photo;
	
	@ManyToOne
	@JoinColumn(name = "Username")
	Account account;
	
	
	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
}
