package br.com.stoom.store.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.model.Category;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/categorys")
@RestController
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryBO categoryService;
	
	@GetMapping(value = "/")
	public ResponseEntity<?> findAll(){
		try {
			List<Category> categorys = categoryService.findAll();
	        if(!categorys.isEmpty())
	            return new ResponseEntity<>(categorys, HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/category")
    public ResponseEntity<Category> findCategoryByName(@RequestParam String nameCategory) {
		try {
			Optional<Category> category = categoryService.findByName(nameCategory);
	        if(!category.isEmpty())
	            return new ResponseEntity<>(category.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/category")
	public ResponseEntity<?> saveProduct(@RequestBody Category product) {
		try {
			categoryService.save(product);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/category/inactivate/{id}")
    public ResponseEntity<?> inactivateProduct(@PathVariable("id") Long categoryId) {
    	try {
    		Category category = categoryService.inactivateCategory(categoryId);
    		return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PutMapping(value = "/category/activate/{id}")
    public ResponseEntity<?> activateProduct(@PathVariable("id") Long categoryId) {
    	try {
    		Category category = categoryService.activateCategory(categoryId);
    		return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
}}
