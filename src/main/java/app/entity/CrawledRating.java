package app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "crawled_rating")
public class CrawledRating {

	@Id
	@Column(name = "crawled_id")
	private int id;

	@Min(0)
	@Max(10)
	@Column(name = "average")
	private Double avg;

	@Min(0)
	@Max(10)
	private Double price;

	@Min(0)
	@Max(10)
	@Column(name = "price_per_square")
	private Double pricePerSquare;

	@Min(0)
	@Max(10)
	private Double size;

	@OneToOne(mappedBy = "crawledRating", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Crawled crawled;

	public CrawledRating() {
		// TODO Auto-generated constructor stub
	}

	public CrawledRating(@Min(0) @Max(10) Double avg, @Min(0) @Max(10) Double price,
			@Min(0) @Max(10) Double pricePerSquare, @Min(0) @Max(10) Double size) {
		super();
		this.avg = avg;
		this.price = price;
		this.pricePerSquare = pricePerSquare;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPricePerSquare() {
		return pricePerSquare;
	}

	public void setPricePerSquare(Double pricePerSquare) {
		this.pricePerSquare = pricePerSquare;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Crawled getCrawled() {
		return crawled;
	}

	public void setCrawled(Crawled crawled) {
		this.crawled = crawled;
	}

	@Override
	public String toString() {
		return "CrawledRating [id=" + id + ", avg=" + avg + ", price=" + price + ", pricePerSquare=" + pricePerSquare
				+ ", size=" + size + "]";
	}

}
