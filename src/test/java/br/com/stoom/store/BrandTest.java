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

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;

@ExtendWith(MockitoExtension.class)
public class BrandTest {
	
	@Mock
	private BrandRepository brandRepository;
	
	@InjectMocks
	private BrandBO brandService;
	
	@Test
	void shouldSaveBrand() {
		brandService.saveBrand(Brand.builder()
				.id(1L)
				.name("Eletrolux")
				.build());
		
		verify(brandRepository).save(any(Brand.class));
	}
	
	@Test
	void shouldInactivateBrand() {
		
		Brand brand = Brand.builder()
		.id(1L)
		.name("Nestle")
		.dataInclusion(LocalDateTime.now())
		.build();
		
		
		when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));
		
		brandService.inactivateBrand(brand.getId());
		
		verify(brandRepository).save(any(Brand.class));
	}
	
	@Test
	void shouldActivateBrand() {
		
		Brand brand = Brand.builder()
				.id(1L)
				.name("Nestle")
				.dataExclusion(LocalDateTime.now())
				.build();
		
		
		when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));
		
		brandService.activateBrand(brand.getId());
		
		verify(brandRepository).save(any(Brand.class));
	}
}
