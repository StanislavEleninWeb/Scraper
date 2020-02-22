package app.scraper.analyze;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import app.entity.BuildType;
import app.entity.ResidenceType;
import app.enumerated.CurrencyEnum;
import app.enumerated.RequestTypeEnum;
import app.service.BuildTypeService;
import app.service.ResidenceTypeService;

public abstract class AnalyzeContent {

	@Autowired
	private BuildTypeService buildTypeService;

	@Autowired
	private ResidenceTypeService residenceTypeService;

	protected HtmlPage html;
	protected String title;
	protected String description;
	protected String keywords;
	protected String region;
	protected ResidenceType type;
	protected CurrencyEnum currency;
	protected BigDecimal price;
	protected BigDecimal pricePerSquare;
	protected short size;
	protected byte floor;
	protected BuildType buildType;
	protected String buildAt;
	protected Set<String> images;

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

	public ResidenceType getType() {
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

	public BuildType getBuildType() {
		return buildType;
	}

	public String getBuildAt() {
		return buildAt;
	}

	public Set<String> getImages() {
		return images;
	}

	/**
	 * Convert String to request type or throw exception if not found
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public RequestTypeEnum getRequestTypeEnum(String string) throws Exception {
		return RequestTypeEnum.valueOf(string.toUpperCase());
	}

	/**
	 * Convert String to currency or throw exception if not found
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public CurrencyEnum getCurrencyEnum(String string) throws Exception {
		return CurrencyEnum.valueOf(string.toUpperCase());
	}

	/**
	 * Convert String to RecidenceType or return null
	 * 
	 * @param string
	 * @return ResidenceType
	 */
	public ResidenceType convertStringToResidenceType(String string) {
		return residenceTypeService.findResidenceTypeByKeywords(string);
	}

	/**
	 * Convert String to BuildType or return null
	 * 
	 * @param string
	 * @return BuildType
	 */
	public BuildType convertStringToBuildType(String string) {
		return buildTypeService.findBuildTypeByKeywords(string);
	}

	@Override
	public String toString() {
		return "AnalyzeContent [html=" + html + ", title=" + title + ", description=" + description + ", keywords="
				+ keywords + ", region=" + region + ", type=" + type + ", currency=" + currency + ", price=" + price
				+ ", pricePerSquare=" + pricePerSquare + ", size=" + size + ", floor=" + floor + ", buildType="
				+ buildType + ", buildAt=" + buildAt + ", images=" + images + "]";
	}

}
