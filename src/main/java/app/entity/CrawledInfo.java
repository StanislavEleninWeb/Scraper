package app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
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

	private String type;

	@NotBlank
	@Enumerated(EnumType.STRING)
	private CurrencyEnum currency;

	@NotBlank
	@Min(value = 0)
	private BigDecimal price;

	@NotBlank
	@Min(value = 0)
	@Column(name = "price_per_square")
	private BigDecimal pricePerSquare;

	// Kvadratura
	@NotBlank
	@Min(value = 0)
	private short size;

	@Min(value = 0)
	private byte floor;

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
			String type, @NotBlank CurrencyEnum currency, @NotBlank @Min(0) BigDecimal price,
			@NotBlank @Min(0) BigDecimal pricePerSquare, @NotBlank @Min(0) short size, @Min(0) byte floor,
			String buildType, String buildAt) {
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
