package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

	public Crawled save(Crawled crawled) {
		return crawledRepository.save(crawled);
	}

	public List<Crawled> findAll() {
		return crawledRepository.findAll();
	}

	public Page<Crawled> findAll(Pageable pageable) {
		return crawledRepository.findAll(pageable);
	}

	public Crawled findById(int id) {
		return crawledRepository.findById(id).get();
	}

	public Page<Crawled> findAll(Specification<Crawled> spec, Pageable pageable) {
		return crawledRepository.findAll(spec, pageable);
	}

}
