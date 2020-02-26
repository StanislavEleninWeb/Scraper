package app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import app.entity.CrawledInfo;
import app.repository.CrawledInfoRepository;

@Service
@Transactional
public class CrawledInfoService {

	@Autowired
	private CrawledInfoRepository crawledInfoRepository;

	public Page<CrawledInfo> findAll(Specification<CrawledInfo> spec, Pageable pageable) {
		return crawledInfoRepository.findAll(spec, pageable);
	}

}
