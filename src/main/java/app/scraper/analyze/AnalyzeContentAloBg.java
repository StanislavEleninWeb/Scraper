package app.scraper.analyze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.print.attribute.HashAttributeSet;

import org.hibernate.internal.util.collections.ArrayHelper;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

public class AnalyzeContentAloBg extends AnalyzeContent {

	@Override
	public void analyze() {

		// Get Title
		HtmlHeading1 responseTitle = (HtmlHeading1) page
				.getByXPath("//div[contains(@class, 'big-info theme-color1')]//h1").get(0);
		title = responseTitle.getTextContent().trim();

		// Get Description
		HtmlParagraph responseDescription = (HtmlParagraph) page.getByXPath("//div[@class=\"more-info\"]//p").get(0);
		description = responseDescription.getTextContent().trim();

		// Get content info div
		List<HtmlDivision> responseDivs = page.getByXPath(
				"//div[contains(@class, 'ads-params ads-params-table highlightable')]//div[contains(@class, 'ads-params-row')]");

		// Get Keywords
		List<HtmlSpan> responseKeywords = (responseDivs.get(10)).getByXPath("//span");

		// Get Region
//		responseDivs.get(1);

		// Get Type
//		responseDivs.get(1);

		// Get Currency
//		responseDivs.get(1);

		// Get Price
//		responseDivs.get(1);

		// Get PricePerSquare

		// Get Size

		// Get Floor

		// Get Build Type

		// Get Build At

		// Get Images as List<String>
		List<DomAttr> responseImages = page.getByXPath("//div[@class=\"images\"]//img/@src");

		images = new ArrayList<String>();
		if (!responseImages.isEmpty()) {
			for (DomAttr responseImage : responseImages) {
				images.add(responseImage.getValue());
			}
		}

	}

	@Override
	public String toString() {
		return "AnalyzeContentAloBg [page=" + page + ", title=" + title + ", description=" + description + ", keywords="
				+ keywords + ", region=" + region + ", type=" + type + ", currency=" + currency + ", price=" + price
				+ ", pricePerSquare=" + pricePerSquare + ", size=" + size + ", floor=" + floor + ", buildType="
				+ buildType + ", buildAt=" + buildAt + ", images=" + images + "]";
	}

}
