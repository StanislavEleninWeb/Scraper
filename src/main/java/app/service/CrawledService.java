package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Crawled;
import app.repository.CrawledRepository;

@Service
@Transactional
public class CrawledService {

	@Autowired
	private CrawledRepository crawledRepository;

	public boolean isCrawledUrlAlreadySaved(String url) {
		return crawledRepository.findByUrl(url) == null ? false : true;
	}

	public void save(Crawled crawled) {
		crawledRepository.save(crawled);
	}

}
