package app.scraper.analyze;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlStrong;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

public class AnalyzeContentOlxBg extends AnalyzeContent {

	@Override
	public void analyze() throws Exception {

		HtmlDivision container = (HtmlDivision) html.getByXPath("//div[contains(@class, 'offerbody')]").get(0);

		// Get Title
		setTitle(container);

		// Get Description
		setDescription(container);

		// Get Region
		setRegion(container);

		// Get Price
		setPrice(container);

		// Get Currency
		setCurrency(container);

		// Get Offer Details block
		List<HtmlTableCell> tableContainer = container.getByXPath(
				".//div[contains(@class, 'descriptioncontent')]//table[contains(@class, 'details')]//tbody//tr//td");

		// Get Keywords
		setKeywords(tableContainer.get(27));

		// Get Type
		setType(tableContainer.get(5));

		// Get PricePerSquare
		setPricePerSquare(tableContainer.get(3));

		// Get Size
		setSize(tableContainer.get(7));

		// Get Floor
		setFloor(tableContainer.get(13));

		// Get Build Type
		setBuildType(tableContainer.get(11));

		// Get Build At
		setBuildAt(tableContainer.get(9));

		// Get Images
		setImages(container);

	}

	protected void setTitle(HtmlElement container) throws Exception {
		HtmlHeading1 heading = (HtmlHeading1) container.getByXPath(".//div[@class=\"offer-titlebox\"]//h1").get(0);
		title = heading.getTextContent().trim();
	}

	protected void setDescription(HtmlElement container) throws Exception {
		HtmlDivision division = (HtmlDivision) container.getByXPath(".//div[@id=\"textContent\"]").get(0);
		description = division.getTextContent().trim();
	}

	protected void setKeywords(HtmlElement container) throws Exception {
		List<HtmlAnchor> response = container.getByXPath(".//a");

		keywords = "";
		for (HtmlAnchor itr : response) {
			keywords += itr.getTextContent().trim() + ",";
		}
		keywords = keywords.substring(0, keywords.length() - 1);
	}

	protected void setRegion(HtmlElement container) throws Exception {
		HtmlStrong response = (HtmlStrong) container
				.getByXPath(".//div[@class=\"offer-titlebox__details\"]//a[@class=\"show-map-link\"]//strong").get(0);
		region = response.getTextContent().trim();
	}

	protected void setPrice(HtmlElement container) throws Exception {
		HtmlStrong response = (HtmlStrong) container
				.getByXPath(".//div[@id=\"offeractions\"]//div[@class=\"price-label\"]//strong").get(0);

		String cleanString = response.getTextContent().replaceAll("&" + "nbsp;", " ");
		cleanString = cleanString.replaceAll(String.valueOf((char) 160), " ");

		Pattern pattern = Pattern.compile("\\d+(?:[\\.\\s]\\d+)?",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(cleanString);

		matcher.find();
		price = Double.parseDouble(matcher.group());
	}

	protected void setPricePerSquare(HtmlElement container) throws Exception {

		String cleanString = container.getTextContent().replaceAll("&" + "nbsp;", " ");
		cleanString = cleanString.replaceAll(String.valueOf((char) 160), " ");

		Pattern pattern = Pattern.compile("\\d+(?:[\\.\\s]\\d+)?",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(cleanString);

		matcher.find();
		pricePerSquare = Double.parseDouble(matcher.group());

	}

	protected void setCurrency(HtmlElement container) throws Exception {
		HtmlStrong response = (HtmlStrong) container
				.getByXPath(".//div[@id=\"offeractions\"]//div[@class=\"price-label\"]//strong").get(0);

		currency = response.getTextContent().replaceAll("[0-9]", "").replace("\u20AC", "EUR").trim();
	}

	protected void setType(HtmlElement container) throws Exception {
		type = container.getTextContent().trim();
	}

	protected void setFloor(HtmlElement container) throws Exception {
		floor = Integer.parseInt(container.getTextContent().replaceAll("[^0-9]", ""));
	}

	protected void setSize(HtmlElement container) throws Exception {

		String cleanString = container.getTextContent().replaceAll("&" + "nbsp;", " ");
		cleanString = cleanString.replaceAll(String.valueOf((char) 160), " ");

		Pattern pattern = Pattern.compile("\\d+(?:[\\.\\s]\\d+)?",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(cleanString);

		matcher.find();
		size = Double.parseDouble(matcher.group());

	}

	protected void setBuildType(HtmlElement container) throws Exception {
		buildType = container.getTextContent().trim();
	}

	protected void setBuildAt(HtmlElement container) throws Exception {
		buildAt = container.getTextContent().trim();
	}

	protected void setImages(HtmlElement container) {
		List<DomAttr> imagesList = container
				.getByXPath(".//div[contains(@class, 'img-item')]//div[@class=\"photo-glow\"]//img/@src");

		images = new ArrayList<String>();
		if (!imagesList.isEmpty()) {
			for (DomAttr image : imagesList) {
				images.add(image.getValue());
			}
		}
	}

}
