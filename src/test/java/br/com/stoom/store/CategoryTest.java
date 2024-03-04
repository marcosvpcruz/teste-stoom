package br.com.stoom.store;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {

	@Mock
	private CategoryRepository categoryRepository;
	@InjectMocks
	private CategoryBO categoryService;
	
	@Test
	void saveCategory() {
		Category category = Category.builder()
		.id(1L)
		.dataInclusion(LocalDateTime.now())
		.build();
		
		categoryService.save(category);
		
		verify(categoryRepository).save(any(Category.class));
	}
	
	@Test
	void shouldInactivateCategory() {
		Category category = Category.builder().id(1L).dataInclusion(LocalDateTime.now()).build();

		when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
		
		categoryService.activateCategory(category.getId());
		
		verify(categoryRepository).save(any(Category.class));
	}
	
	@Test
	void shouldActivateCategory() {
		Category category = Category.builder().id(1L).dataExclusion(LocalDateTime.now()).build();
		
		when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
		
		categoryService.inactivateCategory(category.getId());
		
		verify(categoryRepository).save(any(Category.class));
	}
}
