package app.scheduled;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.entity.Source;
import app.scraper.LinksScraper;
import app.service.SourceService;

@Component
@EnableAsync
public class ScrapeScheduled {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SourceService sourceService;

	@Async
	@Scheduled(fixedRate = 3600000)
	public void scheduledDeadlineTaskCheck() throws InterruptedByTimeoutException {

		logger.info("------------------->>>>>>>>> Start scraping <<<<<<<<<<<<--------------------------");

		Long start_scraping = System.currentTimeMillis();

		// Create links scraper class
		LinksScraper linksScraper = new LinksScraper();

		// Get all sources
		List<Source> sources = sourceService.findAll();

		// Iterate over each source
		for (Source source : sources) {

			if (source.getSourceGenerator() == null) {
				logger.info("Missing generator for " + source);
				continue;
			}
			
			// Set LinksScraper
			linksScraper.setRegex(source.getSourceGenerator().getRegex());
			linksScraper.setRegex(source.getSourceGenerator().getGenerator());
		}

		Long finish_scraping = System.currentTimeMillis();

		logger.info("Report : ");
		logger.info("        Started at : " + new Date(start_scraping));
		logger.info("        Finished  in : " + (finish_scraping - start_scraping) / 1000);

		logger.info("------------------->>>>>>>>> Finish scraping <<<<<<<<<<<<--------------------------");
	}
}
