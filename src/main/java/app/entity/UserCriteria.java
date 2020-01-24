package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_criteria")
public class UserCriteria {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "price_min")
	private double priceMin;

	@Column(name = "price_max")
	private double priceMax;

	@Column(name = "price_per_square_min")
	private double pricePerSquareMin;

	@Column(name = "price_per_square_max")
	private double pricePerSquareMax;

	@Column(name = "size_min")
	private double sizeMin;

	@Column(name = "size_max")
	private double sizeMax;

	@Column(name = "keywords")
	private String keywords;
	
	@OneToOne
	@MapsId
	private User user;

}
