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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Productdetails")
public class ProductDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	@PastOrPresent
	@NotEmpty
	Date createDate = new Date();

	@NotEmpty
	Long quantity;

	@Length(max = 50)
	String image;

	@NotNull
	Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "Colorid")
	Color color;
	
	@ManyToOne
	@JoinColumn(name = "Sizeid")
	Size size;
	
	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
	
	@JsonIgnore
	@OneToMany(mappedBy = "productDetail")
	List<OrderDetail> orderDetails;	
}
