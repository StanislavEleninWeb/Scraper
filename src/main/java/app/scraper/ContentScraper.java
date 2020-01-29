package app.scraper;

import app.entity.CrawledInfo;

public class ContentScraper {

	private String title;
	private String description;
	private String keywords;
	private String region;
	private String currency;
	private double price;
	private double pricePerSquare;
	private double size;
	private int floor;
	private String premiseType;

	public ContentScraper(String string) {
		System.err.println("Constructor with string");
	}

	public CrawledInfo getCrawledInfo() {

		CrawledInfo info = new CrawledInfo();

		info.setTitle(title);
		info.setDescription(description);
		info.setKeywords(keywords);
		info.setRegion(region);
		info.setCurrency(currency);
		info.setPrice(price);
		info.setPricePerSquare(pricePerSquare);
		info.setSize(size);
		info.setFloor(floor);
		info.setPremiseType(premiseType);

		return info;
	}

}
