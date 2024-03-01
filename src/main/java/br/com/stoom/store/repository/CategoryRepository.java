package br.com.stoom.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stoom.store.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findById(Long id);

	Optional<Category> findByNameAndDataExclusionIsNull(String nameCategory);
}
