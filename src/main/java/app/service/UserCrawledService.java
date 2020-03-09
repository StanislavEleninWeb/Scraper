package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Crawled;
import app.entity.User;
import app.entity.UserCrawled;
import app.repository.UserCrawledRepository;

@Service
@Transactional
public class UserCrawledService {

	@Autowired
	private UserCrawledRepository userCrawledRepository;

	public UserCrawled findByUserAndCrawled(User user, Crawled crawled) {
		return userCrawledRepository.findByUserAndCrawled(user, crawled);
	}

}
