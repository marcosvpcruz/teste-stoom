package br.com.stoom.store.business;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;

@Service
public class BrandBO implements IBrandBO{

	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> findByNameBrand(String nameBrand) {
		return brandRepository.findByNameAndDataExclusionIsNull(nameBrand);
	}

	public void saveBrand(Brand brand) {
		brand.setDataInclusion(LocalDateTime.now());
		
		brandRepository.save(brand);
	}

	public Brand inactivateBrand(Long brandId) {
		Optional<Brand> brand = brandRepository.findById(brandId);

		brand.ifPresent(b -> {
			b.setDataChange(LocalDateTime.now());
			b.setDataExclusion(LocalDateTime.now());

			brandRepository.save(b);
		});

		return brand.get();
	}

		public Brand activateBrand(Long brandId) {
			Optional<Brand> brand = brandRepository.findById(brandId);
			
			brand.ifPresent(b -> {
				b.setDataChange(LocalDateTime.now());
				b.setDataExclusion(null);
				
				brandRepository.save(b);
			});
			
			return brand.get();
		}
}
