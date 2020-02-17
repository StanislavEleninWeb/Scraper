package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.CrawledImage;

@Repository
public interface CrawledImageRepository extends JpaRepository<CrawledImage, Integer> {

}
