package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import app.entity.Crawled;

@Repository
public interface CrawledRepository extends JpaRepository<Crawled, Integer>, JpaSpecificationExecutor<Crawled> {

	public Crawled findByUrl(String url);

}
