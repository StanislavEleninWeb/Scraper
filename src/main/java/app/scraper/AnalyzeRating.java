package app.scraper;

import org.springframework.beans.factory.annotation.Autowired;

import app.entity.CrawledInfo;
import app.entity.Rating;
import app.service.CrawledService;

public class AnalyzeRating {

	@Autowired
	private CrawledService crawledService;

	private float avg;
	private float price;
	private float pricePerSquare;
	private float size;

	public AnalyzeRating(CrawledInfo crawledInfo) {
		calculatePriceRating(crawledInfo.getPrice());
		calculatePricePerSquareRating(crawledInfo.getPricePerSquare());
		calculateSizeRating(crawledInfo.getSize());
		calculateAvgRating();
	}

	private void calculatePriceRating(double price) {

	}

	private void calculatePricePerSquareRating(double pricePerSquare) {

	}

	private void calculateSizeRating(double size) {

	}

	private void calculateAvgRating() {
		avg = (price + pricePerSquare + size) / 3;
	}

	public Rating getRating() {
		Rating rating = new Rating();

		rating.setPrice(price);
		rating.setPricePerSquare(pricePerSquare);
		rating.setSize(size);
		rating.setAvg(avg);

		return rating;
	}
}
