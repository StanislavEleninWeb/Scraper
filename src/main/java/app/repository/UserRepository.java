package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findByActivationCode(String activationCode);

}
