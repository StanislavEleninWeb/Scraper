package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Crawled;

public interface CrawledRepository extends JpaRepository<Crawled, Integer> {

	public Crawled findByUrl(String url);

}
