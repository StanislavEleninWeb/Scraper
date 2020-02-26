package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import app.entity.CrawledInfo;

public interface CrawledInfoRepository
		extends JpaRepository<CrawledInfo, Integer>, JpaSpecificationExecutor<CrawledInfo> {

}
