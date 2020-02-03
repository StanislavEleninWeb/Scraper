package app.scraper.analyze;

import java.time.LocalDate;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class AnalyzeContent {

	protected HtmlPage page;
	protected String title;
	protected String description;
	protected String keywords;
	protected String region;
	protected String type;
	protected String currency;
	protected double price;
	protected double pricePerSquare;
	protected double size;
	protected int floor;
	protected String buildType;
	protected LocalDate buildAt;
	protected List<String> images;

	public abstract void analyze();

	public HtmlPage getPage() {
		return page;
	}

	public void setPage(HtmlPage page) {
		this.page = page;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPricePerSquare(double pricePerSquare) {
		this.pricePerSquare = pricePerSquare;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public void setBuildAt(LocalDate buildAt) {
		this.buildAt = buildAt;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getRegion() {
		return region;
	}

	public String getType() {
		return type;
	}

	public String getCurrency() {
		return currency;
	}

	public double getPrice() {
		return price;
	}

	public double getPricePerSquare() {
		return pricePerSquare;
	}

	public double getSize() {
		return size;
	}

	public int getFloor() {
		return floor;
	}

	public String getBuildType() {
		return buildType;
	}

	public LocalDate getBuildAt() {
		return buildAt;
	}

	public List<String> getImages() {
		return images;
	}
}
