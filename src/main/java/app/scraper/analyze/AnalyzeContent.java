package app.scraper.analyze;

import java.math.BigDecimal;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import app.enumerated.CurrencyEnum;
import app.enumerated.RequestTypeEnum;

public abstract class AnalyzeContent {

	protected HtmlPage html;
	protected String title;
	protected String description;
	protected String keywords;
	protected String region;
	protected String type;
	protected CurrencyEnum currency;
	protected BigDecimal price;
	protected BigDecimal pricePerSquare;
	protected short size;
	protected byte floor;
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

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getPricePerSquare() {
		return pricePerSquare;
	}

	public Short getSize() {
		return size;
	}

	public Byte getFloor() {
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

	public RequestTypeEnum getRequestTypeEnum(String string) throws Exception {
		return RequestTypeEnum.valueOf(string.toUpperCase());
	}

	public CurrencyEnum getCurrencyEnum(String string) throws Exception {
		return CurrencyEnum.valueOf(string.toUpperCase());
	}

	@Override
	public String toString() {
		return "AnalyzeContent [html=" + html + ", title=" + title + ", description=" + description + ", keywords="
				+ keywords + ", region=" + region + ", type=" + type + ", currency=" + currency + ", price=" + price
				+ ", pricePerSquare=" + pricePerSquare + ", size=" + size + ", floor=" + floor + ", buildType="
				+ buildType + ", buildAt=" + buildAt + ", images=" + images + "]";
	}

}
