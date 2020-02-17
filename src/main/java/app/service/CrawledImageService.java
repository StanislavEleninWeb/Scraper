package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.CrawledImage;
import app.repository.CrawledImageRepository;

@Service
@Transactional
public class CrawledImageService {

	@Autowired
	private CrawledImageRepository crawledImageRepository;

	public CrawledImage save(CrawledImage crawledImage) {
		return crawledImageRepository.save(crawledImage);
	}

}
