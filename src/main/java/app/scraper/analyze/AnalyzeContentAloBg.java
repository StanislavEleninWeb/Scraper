package app.scraper.analyze;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

public class AnalyzeContentAloBg extends AnalyzeContent {

	@Override
	public void analyze() throws Exception {

		HtmlElement container = (HtmlElement) html.getByXPath("//div[@class=\"container\"]//div").get(0);

		// Get Title
		setTitle(container);

		// Get Description
		setDescription(container);

		// Get content info div
		List<HtmlDivision> responseDivs = html.getByXPath(
				"//div[contains(@class, 'ads-params ads-params-table highlightable')]//div[contains(@class, 'ads-params-row')]");

		// Get Keywords
		setKeywords(responseDivs.get(0));

		// Get Region
		setRegion(responseDivs.get(0));
//
//		// Get Type
//		HtmlSpan responseType = (HtmlSpan) responseDivs.get(4).getByXPath(".//span").get(0);
//		type = responseType.getTextContent().trim();
//
//		// Get Price and PricePerSquare in List
//		HtmlDivision responsePrice = (HtmlDivision) responseDivs.get(2).getByXPath(".//div").get(1);
//
//		String cleanString = responsePrice.getTextContent().replaceAll("&" + "nbsp;", " ");
//		cleanString = cleanString.replaceAll(String.valueOf((char) 160), " ");
//
//		Pattern pattern = Pattern.compile("\\d+(?:[\\.\\s]\\d+)?",
//				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
//		Matcher matcher = pattern.matcher(cleanString);
//
//		// Get Price
//		matcher.find();
//		price = Double.parseDouble(matcher.group().replaceAll("[^0-9]", ""));
//
//		// Get PricePerSquare
//		matcher.find();
//		pricePerSquare = Double.parseDouble(matcher.group());
//
//		// Get Currency
//		Pattern patternCurency = Pattern.compile("[a-z]{3}",
//				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
//		Matcher matcherCurency = patternCurency.matcher(cleanString);
//		matcherCurency.find();
//		currency = matcherCurency.group();
//
//		// Get Size
//		HtmlSpan responseSize = (HtmlSpan) responseDivs.get(5).getByXPath(".//span").get(0);
//		size = Integer.parseInt(responseSize.getTextContent().replaceAll("[^0-9]", ""));
//
//		// Get Floor
//		HtmlSpan responseFloor = (HtmlSpan) responseDivs.get(8).getByXPath(".//span").get(0);
//		floor = Integer.parseInt(responseFloor.getTextContent().replaceAll("[^0-9]", ""));
//
//		// Get Build Type
//		HtmlSpan responseBuildType = (HtmlSpan) responseDivs.get(6).getByXPath(".//span").get(0);
//		buildType = responseBuildType.getTextContent().trim();
//
//		// Get Build At
//		HtmlSpan responseBuildAt = (HtmlSpan) responseDivs.get(7).getByXPath(".//span").get(0);
//		buildAt = responseBuildAt.getTextContent().trim();
//
//		// Get Images as List<String>
//		setImages();
	}

	@Override
	protected void setTitle(HtmlElement container) throws Exception {
		HtmlHeading1 responseTitle = (HtmlHeading1) container
				.getByXPath(".//div[contains(@class, 'big-info theme-color1')]//h1").get(0);
		title = responseTitle.getTextContent().trim();
	}

	@Override
	protected void setDescription(HtmlElement container) throws Exception {
		HtmlParagraph responseDescription = (HtmlParagraph) html.getByXPath(".//div[@class=\"more-info\"]//p").get(0);
		description = responseDescription.getTextContent().trim();
	}

	@Override
	protected void setKeywords(HtmlElement container) throws Exception {
		List<HtmlSpan> responseKeywords = container.getByXPath(".//span");
		for (HtmlSpan itr : responseKeywords) {
			keywords += itr.getTextContent().trim() + ",";
		}
		keywords = keywords.substring(0, keywords.length() - 1);
	}

	@Override
	protected void setRegion(HtmlElement container) throws Exception {
		List<HtmlSpan> responseRegion = container.getByXPath(".//span[@title]");
		for (HtmlSpan itr : responseRegion) {
			region += itr.getTextContent().trim() + ",";
		}
		region = region.substring(0, region.length() - 1);
	}

	@Override
	protected void setType(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setCurrency(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setPrice(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setPricePerSquare(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setSize(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setFloor(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setBuildType(HtmlElement container) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setBuildAt(HtmlElement container) throws Exception {
		List<DomAttr> responseImages = html.getByXPath(".//div[@class=\"images\"]//img/@src");

		images = new ArrayList<String>();
		if (!responseImages.isEmpty()) {
			for (DomAttr responseImage : responseImages) {
				images.add(responseImage.getValue());
			}
		}
	}

}
