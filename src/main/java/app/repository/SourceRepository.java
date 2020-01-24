package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Source;

public interface SourceRepository extends JpaRepository<Source, Integer> {

}
