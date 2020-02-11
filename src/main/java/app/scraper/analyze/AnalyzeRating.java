package app.scraper.analyze;

import java.math.BigDecimal;

import app.entity.CrawledInfo;
import app.entity.CrawledRating;

public class AnalyzeRating {

	private double avg;
	private double price;
	private double pricePerSquare;
	private double size;

	public AnalyzeRating(CrawledInfo crawledInfo) {
		calculatePriceRating(crawledInfo.getPrice());
		calculatePricePerSquareRating(crawledInfo.getPricePerSquare());
		calculateSizeRating(crawledInfo.getSize());
		calculateAvgRating();
	}

	private void calculatePriceRating(BigDecimal price) {
		this.price = 5;
	}

	private void calculatePricePerSquareRating(BigDecimal pricePerSquare) {
		this.pricePerSquare = 4;
	}

	private void calculateSizeRating(Short size) {
		this.size = 4;
	}

	private void calculateAvgRating() {
		avg = (price + pricePerSquare + size) / 3;
	}

	public CrawledRating getCrawledRating() {
		CrawledRating rating = new CrawledRating();

		rating.setPrice(price);
		rating.setPricePerSquare(pricePerSquare);
		rating.setSize(size);
		rating.setAvg(avg);

		return rating;
	}

}
