package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "rating")
public class CrawledRating {

	@Id
	@Column(name = "crawled_id")
	private int id;

	@NotBlank
	@Min(0)
	@Max(10)
	private float avg;

	@NotBlank
	@Min(0)
	@Max(10)
	private float price;

	@NotBlank
	@Min(0)
	@Max(10)
	@Column(name = "price_per_square")
	private float pricePerSquare;

	@NotBlank
	@Min(0)
	@Max(10)
	private float size;

	@OneToOne(mappedBy = "crawledRating", fetch = FetchType.LAZY)
	private Crawled crawled;

	public CrawledRating() {
		// TODO Auto-generated constructor stub
	}

	public CrawledRating(@NotBlank @Min(0) @Max(10) float avg, @NotBlank @Min(0) @Max(10) float price,
			@NotBlank @Min(0) @Max(10) float pricePerSquare, @NotBlank @Min(0) @Max(10) float size) {
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

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPricePerSquare() {
		return pricePerSquare;
	}

	public void setPricePerSquare(float pricePerSquare) {
		this.pricePerSquare = pricePerSquare;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
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
