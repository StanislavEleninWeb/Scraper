package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crawled_info")
public class CrawledInfo {

	@Id
	@Column(name = "id")
	private int id;

	private double price;

	@Column(name = "price_per_square")
	private double pricePerSquare;

	private double size;

	private String region;

	@OneToOne
	@MapsId
	private Crawled crawled;

}
