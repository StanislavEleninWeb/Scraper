package app.scraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ContentScraper {

	private HtmlPage page;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public ContentScraper(String url) {

		WebClient client = new WebClient(BrowserVersion.CHROME);

		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		try {
			page = client.getPage(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.close();
	}

	public HtmlPage getPage() {
		return page;
	}

}
