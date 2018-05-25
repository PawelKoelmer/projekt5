package domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
@NamedQueries({
@NamedQuery(name="product.all", query="SELECT p FROM Product p"),
@NamedQuery(name="product.id", query="FROM Product p WHERE p.id=:productId"),
@NamedQuery(name="product.name", query="FROM Product p WHERE p.name=:productName"),
@NamedQuery(name="product.price", query="FROM Product p WHERE p.cena BETWEEN :minPrice AND :maxPrice"),
@NamedQuery(name="product.category", query="FROM Product p WHERE p.category=:Category")
})
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double cena;
	private Categories category;
	
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
}
