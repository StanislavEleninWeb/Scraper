package app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.User;
import app.entity.UserCriteria;
import app.repository.UserCriteriaRepository;

@Service
@Transactional
public class UserCriteriaService {

	@Autowired
	private UserCriteriaRepository userCriteriaRepository;

	public UserCriteria findByUserAndPrimary(User user, Boolean primary) {
		return userCriteriaRepository.findByUserAndPrimary(user, primary);
	}

}
