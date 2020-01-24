package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.SourceGenerator;

public interface SourceGeneratorRepository extends JpaRepository<SourceGenerator, Integer> {

}
