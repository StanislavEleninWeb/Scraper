package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Crawled;
import app.entity.User;
import app.entity.UserCrawled;

@Repository
public interface UserCrawledRepository extends JpaRepository<UserCrawled, Integer> {

	public UserCrawled findByUserAndCrawled(User user, Crawled crawled);

}
