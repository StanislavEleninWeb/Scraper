package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.User;
import app.entity.UserCriteria;

@Repository
public interface UserCriteriaRepository extends JpaRepository<UserCriteria, Integer> {

	public UserCriteria findByUserAndPrimary(User user, Boolean primary);

}
