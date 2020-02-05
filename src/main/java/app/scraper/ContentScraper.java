package app.scraper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ContentScraper {

	private HtmlPage page;

	/**
	 * Scrapes the content of url address
	 * 
	 * @param url
	 * @throws Exception
	 */
	public ContentScraper(String url) throws Exception {

		WebClient client = new WebClient(BrowserVersion.CHROME);

		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		page = client.getPage(url);

		client.close();
	}

	/**
	 * 
	 * @return HtmlPage
	 */
	public HtmlPage getPage() {
		return page;
	}

}
