package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source, Integer> {

	public List<Source> findByActive(Boolean active);

}
