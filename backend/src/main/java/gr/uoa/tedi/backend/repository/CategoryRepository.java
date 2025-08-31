package gr.uoa.tedi.backend.repository;

import gr.uoa.tedi.backend.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
