package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Product;

public class ProductService {
	
	private static List<Product> db = new ArrayList<Product>();
	private static int id=1;
	
	public List<Product> getAll(){
		return db;
	}
	public Product get(int id){
		for(Product p : db){
			if(p.getId() == id)
				return p;
		}
		return null;
	}
	public void add(Product p){
		p.setId(++id);
		db.add(p);
	}
	
	public void update(Product product){
		for(Product p : db){
			if(p.getId()==product.getId()){
				p.setName(product.getName());
				p.setCena(product.getCena());
				p.setCategory(product.getCategory());
			}
		}
	}

}
