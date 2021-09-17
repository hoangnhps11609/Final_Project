package edu.poly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Vouchers")
public class Voucher implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Double value;
	
	String name;
	
	Boolean Status;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Closingdate")
	Date closingDate = new Date();
	
	
	 @OneToOne(mappedBy = "voucher")
	 OrderDetail orderDetail;
}
