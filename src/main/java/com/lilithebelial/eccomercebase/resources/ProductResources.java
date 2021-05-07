package com.lilithebelial.eccomercebase.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lilithebelial.eccomercebase.models.ModelProduct;
import com.lilithebelial.eccomercebase.repositories.ProductRepository;

@RestController
@RequestMapping(value="/api")
public class ProductResources {
	@Autowired
	ProductRepository prodRepo;
	
	/*GETTERs*/
	//All products
	@GetMapping(value="/products")
	public List<ModelProduct> getProducts(){
		return prodRepo.findAll();
	}
	//Specific product
	@GetMapping(value="/product/{id}")
	public Optional<ModelProduct> getProduct(@PathVariable(value="id") long id){
		return prodRepo.findById(id);
	}	
	//Specific product quantity
	@GetMapping(value="/productQuant/{id}")
	public BigDecimal getProdQuant(@PathVariable(value="id") long id) {
		return prodRepo.findById((id)).get().getQuantity();
	}
	//    //   product description
	@GetMapping(value="/productDesc/{id}")
	public String getProdDesc(@PathVariable(value="id") long id) {
		return prodRepo.findById((id)).get().getDescription();
	}
	//  //   product value
	@GetMapping(value="/productValue/{id}")
	public BigDecimal getProdValuer(@PathVariable(value="id") long id) {
		return prodRepo.findById((id)).get().getValue();
	}

	
	/*SETTERs*/
	@PostMapping(value="/product")
	public ModelProduct addProduct(@RequestBody ModelProduct produto) {
		return prodRepo.save(produto);
	}
	
	/*REMOVE PRODUCT*/
	@DeleteMapping(value="/productDel/{id}")
	public String delProduct(@PathVariable(value="id")long id) {
		ModelProduct prod = prodRepo.findById(id).get();
		
		try{
			prodRepo.delete(prod);
			return "SUCCESS";
		}
		catch(Exception e){
			return "ERROR ON REMOVE PRODUCT";
		}
		
	}
	
}
