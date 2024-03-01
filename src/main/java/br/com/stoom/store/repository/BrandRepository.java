package br.com.stoom.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stoom.store.model.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long>{
	
	Optional<Brand> findById(Long brandId);

	List<Brand> findByNameAndDataExclusionIsNull(String nameBrand);
}
