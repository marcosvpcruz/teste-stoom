package br.com.stoom.store.business.interfaces;

import java.util.List;

import br.com.stoom.store.model.Brand;

public interface IBrandBO {

	List<Brand> findAll();
	List<Brand> findByNameBrand(String nameProduct);
}
