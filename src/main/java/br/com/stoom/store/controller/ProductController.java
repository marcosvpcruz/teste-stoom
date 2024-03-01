package br.com.stoom.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.dto.ProductDto;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;

@Controller
@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductBO productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Product>> findAll() {
    	try {
    		List<Product> p = productService.findAll();
            if(!p.isEmpty())
                return new ResponseEntity<>(p, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
    
    @GetMapping(value = "/product")
    public ResponseEntity<Product> findProductByName(@RequestParam String nameProduct) {
    	try {
    		Optional<Product> product = productService.findByName(nameProduct);
            if(!product.isEmpty())
                return new ResponseEntity<>(product.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
    
    @GetMapping(value = "/product/band")
	public ResponseEntity<?> findProductByNameBrand(@RequestParam String nameBrand) {
		try {
			List<ProductRepository.ProductDto> products = productService.findByNameBrand(nameBrand);
			if (!products.isEmpty())
				return new ResponseEntity<>(products, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
    
    @GetMapping(value = "/product/category")
	public ResponseEntity<?> findProductByNameCategory(@RequestParam String nameCategory) {
		try {
			List<ProductRepository.ProductCategoryDto> products = productService.findByNameCategory(nameCategory);
			if (!products.isEmpty())
				return new ResponseEntity<>(products, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
    
    @PostMapping(value = "/product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto product) {
    	try {
    		productService.save(product);
    		return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PutMapping(value = "/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productUpdate) {
    	try {
    		Product product = productService.updateProduct(productId, productUpdate);
    		return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @DeleteMapping(value = "/product/inactivate/{id}")
    public ResponseEntity<?> inactivateProduct(@PathVariable("id") Long productId) {
    	try {
    		Product product = productService.inactivateProduct(productId);
    		return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PutMapping(value = "/product/activate/{id}")
    public ResponseEntity<?> activateProduct(@PathVariable("id") Long productId) {
    	try {
    		Product product = productService.activateProduct(productId);
    		return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
