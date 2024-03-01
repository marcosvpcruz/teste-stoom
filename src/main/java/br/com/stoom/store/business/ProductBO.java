package br.com.stoom.store.business;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.ProductRepository;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

	@Override
	public Optional<Product> findByName(String nameProduct) {
		return productRepository.findByNameAndDataExclusionIsNull(nameProduct);
	}

	public void save(ProductDto productDto) {
		productRepository.save(converterProductDto(productDto));
	}

	private Product converterProductDto(ProductDto productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setDataInclusion(LocalDateTime.now());
		product.setSku(productDto.getSku());
		
		try {
			Optional<Brand> brand = brandRepository.findById(productDto.getBrandId());
			Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
			
			product.setCategory(category.get());
			product.setBrand(brand.get());
			
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("Brand or Category invalid");
		}
		
		return product;
	}

	public Product updateProduct(Long productId, ProductDto productUpdate) {
		Optional<Product> product = productRepository.findById(productId);
		
		product.ifPresent(p -> {
			p.setName(productUpdate.getName());
			p.setSku(productUpdate.getSku());
			p.setDataChange(LocalDateTime.now());
			p.setPrice(productUpdate.getPrice());
			
			productRepository.save(p);
		});
		return product.get();
	}

	public Product inactivateProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		
		product.ifPresent(p -> {
			p.setDataChange(LocalDateTime.now());
			p.setDataExclusion(LocalDateTime.now());
			
			productRepository.save(p);
		});
		
		return product.get();
	}

	public Product activateProduct(Long productId) {
		Optional<Product> product = productRepository.findById(productId);
		
		product.ifPresent(p -> {
			p.setDataChange(LocalDateTime.now());
			p.setDataExclusion(null);
			
			productRepository.save(p);
		});
		
		return product.get();
	}

	public List<ProductRepository.ProductDto> findByNameBrand(String nameBrand) {
		return productRepository.findProductsByNameBrand(nameBrand);
	}

	public List<ProductRepository.ProductCategoryDto> findByNameCategory(String nameCategory) {
		return productRepository.findProductsByNameCategory(nameCategory);
	}
}
