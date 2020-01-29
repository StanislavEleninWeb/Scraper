package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Source;

public interface SourceRepository extends JpaRepository<Source, Integer> {

	public List<Source> findByActive(Boolean active);

}
