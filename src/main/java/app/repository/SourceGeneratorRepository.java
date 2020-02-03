package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.SourceGenerator;

@Repository
public interface SourceGeneratorRepository extends JpaRepository<SourceGenerator, Integer> {

}
