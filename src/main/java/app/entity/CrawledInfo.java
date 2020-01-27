package app.entity;

import java.time.LocalDate;

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
	@Column(name = "id")
	private int id;

	@NotBlank
	@Size(min = 2)
	private String title;

	private String description;

	private String keywords;

	// Mestopolojenie
	private String region;

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

	private int floor;

	// Vid na imota [tristaen]
	@Column(name = "premise_type")
	private String premiseType;

	// Vid stroitelstvo [tuhla]
	@Column(name = "premise_build_type")
	private String premiseBuildType;

	// Godina na stroej
	@Column(name = "premise_build_at")
	private LocalDate premiseBuildAt;

	@OneToOne(mappedBy = "crawledInfo", fetch = FetchType.LAZY)
	private Crawled crawled;

	public CrawledInfo() {
		// TODO Auto-generated constructor stub
	}

	public CrawledInfo(int id, @NotBlank @Size(min = 2) String title, String description, String keywords,
			String region, @NotBlank String currency, @NotBlank @Min(0) double price,
			@NotBlank @Min(0) double pricePerSquare, @NotBlank @Min(0) double size, int floor, String premiseType,
			String premiseBuildType, LocalDate premiseBuildAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.region = region;
		this.currency = currency;
		this.price = price;
		this.pricePerSquare = pricePerSquare;
		this.size = size;
		this.floor = floor;
		this.premiseType = premiseType;
		this.premiseBuildType = premiseBuildType;
		this.premiseBuildAt = premiseBuildAt;
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

	public String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}

	public String getPremiseBuildType() {
		return premiseBuildType;
	}

	public void setPremiseBuildType(String premiseBuildType) {
		this.premiseBuildType = premiseBuildType;
	}

	public LocalDate getPremiseBuildAt() {
		return premiseBuildAt;
	}

	public void setPremiseBuildAt(LocalDate premiseBuildAt) {
		this.premiseBuildAt = premiseBuildAt;
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
				+ ", region=" + region + ", currency=" + currency + ", price=" + price + ", pricePerSquare="
				+ pricePerSquare + ", size=" + size + ", floor=" + floor + ", premiseType=" + premiseType
				+ ", premiseBuildType=" + premiseBuildType + ", premiseBuildAt=" + premiseBuildAt + "]";
	}

}
