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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
public class Product  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String name;
	
	Double price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	
	Integer quantity;
	
	Boolean available;
	
	String description;
	
	Integer discount;
	
	String image;
	
	Integer gender;
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "Brandid")
	Brand brand;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Comment> comments;	

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<SizeDetail> sizeDetails;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ImageColorDetail> imageColorDetails;	
}
