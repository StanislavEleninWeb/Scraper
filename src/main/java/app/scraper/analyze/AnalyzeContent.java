package app.scraper.analyze;

import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public abstract class AnalyzeContent {

	protected HtmlPage html;
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
	protected String buildAt;
	protected List<String> images;

	public abstract void analyze() throws Exception;

	public HtmlPage getHtml() {
		return html;
	}

	public void setHtml(HtmlPage html) {
		this.html = html;
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

	public String getBuildAt() {
		return buildAt;
	}

	public List<String> getImages() {
		return images;
	}

	@Override
	public String toString() {
		return "AnalyzeContent [html=" + html + ", title=" + title + ", description=" + description + ", keywords="
				+ keywords + ", region=" + region + ", type=" + type + ", currency=" + currency + ", price=" + price
				+ ", pricePerSquare=" + pricePerSquare + ", size=" + size + ", floor=" + floor + ", buildType="
				+ buildType + ", buildAt=" + buildAt + ", images=" + images + "]";
	}

}
