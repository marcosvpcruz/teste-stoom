package br.com.stoom.store.controller;

import java.util.List;

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

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.model.Brand;

@Controller
@RequestMapping("/api/brands")
@RestController
public class BrandController {

	@Autowired
	private BrandBO brandService;

	@GetMapping(value = "/")
	public ResponseEntity<?> findAll() {
		try {
			List<Brand> brands = brandService.findAll();

			if (!brands.isEmpty())
				return new ResponseEntity<>(brands, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/band")
	public ResponseEntity<?> findbyName(@RequestParam String nameProduct) {
		try {
			List<Brand> brands = brandService.findByNameBrand(nameProduct);

			if (!brands.isEmpty())
				return new ResponseEntity<>(brands, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/brand")
	public ResponseEntity<?> saveBrand(@RequestBody Brand brand) {
		try {
			brandService.saveBrand(brand);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/brand/inactivate/{id}")
	public ResponseEntity<?> inactivateBrand(@PathVariable("id") Long brandId) {
		try {
			Brand brand = brandService.inactivateBrand(brandId);
			return new ResponseEntity<>(brand, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/brand/activate/{id}")
	public ResponseEntity<?> activateProduct(@PathVariable("id") Long productId) {
		try {
			Brand product = brandService.activateBrand(productId);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
