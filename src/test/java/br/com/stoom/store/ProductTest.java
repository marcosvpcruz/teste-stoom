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

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductTest {
	
	@Mock
	private ProductRepository productRepository;
	@Mock
	private BrandRepository brandRepository;
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private ProductBO productService;
	
	@Test
	void shouldSaveProduct() {

		ProductDto product = ProductDto.builder().name("Arroz").sku("Arroz").brandId(1L).categoryId(1L)
				.dataInclusion(LocalDateTime.now()).build();

		Brand brand = Brand.builder().name("Camil").id(1L).dataInclusion(LocalDateTime.now()).build();

		Category category = Category.builder().id(1l).name("Alimento").dataInclusion(LocalDateTime.now()).build();

		when(brandRepository.findById(product.getBrandId())).thenReturn(Optional.of(brand));
		when(categoryRepository.findById(product.getCategoryId())).thenReturn(Optional.of(category));

		productService.save(product);

		verify(productRepository).save(any(Product.class));
	}
	
	@Test
	void shouldActivateProduct() {
		ProductDto product = ProductDto.builder().name("Arroz").sku("Arroz").brandId(1L).categoryId(1L).id(1L)
				.dataExclusion(LocalDateTime.now()).build();

		when(productRepository.findById(product.getId())).thenReturn(Optional.of(Product.builder()
				.id(product.getId())
				.dataExclusion(product.getDataExclusion())
				.name(product.getName())
				.sku(product.getSku()).build()));
		
		productService.activateProduct(product.getId());
		
		verify(productRepository).save(any(Product.class));
	}
	
	@Test
	void shouldInactivateProduct() {
		ProductDto product = ProductDto.builder().name("Arroz").sku("Arroz").brandId(1L).categoryId(1L).id(1L)
				.dataInclusion(LocalDateTime.now()).build();
		
		when(productRepository.findById(product.getId())).thenReturn(Optional.of(Product.builder()
				.id(product.getId())
				.dataExclusion(product.getDataExclusion())
				.name(product.getName())
				.sku(product.getSku()).build()));
		
		productService.inactivateProduct(product.getId());
		
		verify(productRepository).save(any(Product.class));
	}
}
