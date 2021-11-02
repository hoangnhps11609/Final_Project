package edu.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Orderdetails")
public class OrderDetail  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotEmpty
	Double price;
	@NotEmpty
	Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "productdetailid")
	ProductDetail productDetail;
	
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Order order;
 
}
