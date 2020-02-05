package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "crawled_info")
public class CrawledInfo {

	@Id
	@Column(name = "crawled_id")
	private int id;

	@NotBlank
	@Size(min = 2)
	private String title;

	private String description;

	private String keywords;

	// Mestopolojenie
	private String region;

	private String type;

	@NotBlank
	private String currency;

	@NotBlank
	@Min(value = 0)
	private double price;

	@NotBlank
	@Min(value = 0)
	@Column(name = "price_per_square")
	private double pricePerSquare;

	// Kvadratura
	@NotBlank
	@Min(value = 0)
	private double size;

	@Min(value = 0)
	private int floor;

	// Vid stroitelstvo [tuhla]
	@Column(name = "build_type")
	private String buildType;

	// Godina na stroej
	@Column(name = "build_at")
	private String buildAt;

	@OneToOne(mappedBy = "crawledInfo", fetch = FetchType.LAZY)
	private Crawled crawled;

	public CrawledInfo() {
		// TODO Auto-generated constructor stub
	}

	public CrawledInfo(@NotBlank @Size(min = 2) String title, String description, String keywords, String region,
			String type, @NotBlank String currency, @NotBlank @Min(0) double price,
			@NotBlank @Min(0) double pricePerSquare, @NotBlank @Min(0) double size, @Min(0) int floor, String buildType,
			String buildAt) {
		super();
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.region = region;
		this.type = type;
		this.currency = currency;
		this.price = price;
		this.pricePerSquare = pricePerSquare;
		this.size = size;
		this.floor = floor;
		this.buildType = buildType;
		this.buildAt = buildAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPricePerSquare() {
		return pricePerSquare;
	}

	public void setPricePerSquare(double pricePerSquare) {
		this.pricePerSquare = pricePerSquare;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public String getBuildAt() {
		return buildAt;
	}

	public void setBuildAt(String buildAt) {
		this.buildAt = buildAt;
	}

	public Crawled getCrawled() {
		return crawled;
	}

	public void setCrawled(Crawled crawled) {
		this.crawled = crawled;
	}

	@Override
	public String toString() {
		return "CrawledInfo [id=" + id + ", title=" + title + ", description=" + description + ", keywords=" + keywords
				+ ", region=" + region + ", type=" + type + ", currency=" + currency + ", price=" + price
				+ ", pricePerSquare=" + pricePerSquare + ", size=" + size + ", floor=" + floor + ", buildType="
				+ buildType + ", buildAt=" + buildAt + "]";
	}

}
