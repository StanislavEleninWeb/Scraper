package app.scraper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class LinksScraper {

	private String url;
	private String regex;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public List<String> getLinks() throws Exception {

		List<String> links = new ArrayList<String>();

		WebClient client = new WebClient(BrowserVersion.CHROME);

		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		HtmlPage page = client.getPage(url);

		List<DomAttr> items = page.getByXPath(regex);

		if (items.isEmpty()) {
			logger.info("For address : " + url + " No matches");
		} else {
			for (DomAttr item : items) {
				links.add(item.getValue());
			}
		}

		client.close();

		return links;
	}

}
