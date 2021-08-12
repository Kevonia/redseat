package com.kovecmedia.redseat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "testimonials")
public class Testimonial {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String  Details;
	    
	    private String ClientName;

	    private String ClientImg;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDetails() {
			return Details;
		}

		public void setDetails(String details) {
			Details = details;
		}

		public String getClientName() {
			return ClientName;
		}

		public void setClientName(String clientName) {
			ClientName = clientName;
		}

		public String getClientImg() {
			return ClientImg;
		}

		public void setClientImg(String clientImg) {
			ClientImg = clientImg;
		}
	    
	    
	
}
