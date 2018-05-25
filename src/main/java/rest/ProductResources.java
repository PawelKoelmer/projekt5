package rest;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Categories;
import domain.Product;
import domain.services.ProductService;



@Path("/product")
@Stateless
public class ProductResources {

	private ProductService db = new ProductService();
	
	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Product product) {
		em.persist(product);
		return Response.ok(product.getId()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	@GET
	@Path("/search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("name") String name){
		Product result = em.createNamedQuery("product.name", Product.class)
				.setParameter("productName", name)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	@GET
	@Path("/search/price/{min}/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> get(@PathParam("min") double min, @PathParam("max") double max){
		return em.createNamedQuery("product.price", Product.class)
				.setParameter("minPrice", min)
				.setParameter("maxPrice", max)
				.getResultList();	
	}
	
	@GET
	@Path("/search/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> get(@PathParam("category") Categories category){
		return em.createNamedQuery("product.category", Product.class)
				.setParameter("Category", category)
				.getResultList();	
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setCena(p.getCena());
		result.setCategory(p.getCategory());
		db.update(p);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}
	
	
}
