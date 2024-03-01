package br.com.stoom.store.business;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryBO {

	private final CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Optional<Category> findByName(String nameCategory) {
		return categoryRepository.findByNameAndDataExclusionIsNull(nameCategory);
	}

	public void save(Category category) {
		category.setDataInclusion(LocalDateTime.now());
		
		categoryRepository.save(category);
	}

	public Category inactivateCategory(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);

		category.ifPresent(p -> {
			p.setDataChange(LocalDateTime.now());
			p.setDataExclusion(LocalDateTime.now());

			categoryRepository.save(p);
		});

		return category.get();
	}

	public Category activateCategory(Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);

		category.ifPresent(p -> {
			p.setDataChange(LocalDateTime.now());
			p.setDataExclusion(null);

			categoryRepository.save(p);
		});

		return category.get();
	}
}
