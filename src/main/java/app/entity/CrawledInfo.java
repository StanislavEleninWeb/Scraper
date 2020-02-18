package app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import app.enumerated.CurrencyEnum;

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

	@Enumerated(EnumType.STRING)
	private CurrencyEnum currency;

	@Min(value = 0)
	private BigDecimal price;

	@Min(value = 0)
	@Column(name = "price_per_square")
	private BigDecimal pricePerSquare;

	// Kvadratura
	@Min(value = 0)
	private short size;

	@Min(value = 0)
	private byte floor;

	// Godina na stroej
	@Column(name = "build_at")
	private String buildAt;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Crawled crawled;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	private ResidenceType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "build_type")
	private BuildType buildType;

	public CrawledInfo() {
		// TODO Auto-generated constructor stub
	}

	public CrawledInfo(@NotBlank @Size(min = 2) String title, String description, String keywords, String region,
			CurrencyEnum currency, @Min(0) BigDecimal price, @Min(0) BigDecimal pricePerSquare, @Min(0) short size,
			@Min(0) byte floor, String buildAt, ResidenceType type, BuildType buildType) {
		super();
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.region = region;
		this.currency = currency;
		this.price = price;
		this.pricePerSquare = pricePerSquare;
		this.size = size;
		this.floor = floor;
		this.buildAt = buildAt;
		this.type = type;
		this.buildType = buildType;
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

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPricePerSquare() {
		return pricePerSquare;
	}

	public void setPricePerSquare(BigDecimal pricePerSquare) {
		this.pricePerSquare = pricePerSquare;
	}

	public short getSize() {
		return size;
	}

	public void setSize(short size) {
		this.size = size;
	}

	public byte getFloor() {
		return floor;
	}

	public void setFloor(byte floor) {
		this.floor = floor;
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

	public ResidenceType getType() {
		return type;
	}

	public void setType(ResidenceType type) {
		this.type = type;
	}

	public BuildType getBuildType() {
		return buildType;
	}

	public void setBuildType(BuildType buildType) {
		this.buildType = buildType;
	}

	@Override
	public String toString() {
		return "CrawledInfo [id=" + id + ", title=" + title + ", description=" + description + ", keywords=" + keywords
				+ ", region=" + region + ", currency=" + currency + ", price=" + price + ", pricePerSquare="
				+ pricePerSquare + ", size=" + size + ", floor=" + floor + ", buildAt=" + buildAt + "]";
	}

}
