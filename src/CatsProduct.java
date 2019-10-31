import java.math.BigDecimal;

public class CatsProduct {

	private String name;
	private String category;
	private String description;
	private BigDecimal price;

	public CatsProduct() {
		super();
	}

	public CatsProduct(String name, String category, String description, BigDecimal price) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return name + "," + category + "," + description + "," + price;

	}
	
	public static CatsProduct fromString(String csvRow) {
	   String[] csvMembers = csvRow.split(",");
	   return new CatsProduct(csvMembers[0], csvMembers[1], csvMembers[2], new BigDecimal(csvMembers[3]));
	}

//		CatsProduct cat1 = new CatsProduct("Murloc", "Tuxedo Cat", "Cute", new BigDecimal("100.00"));
//		System.out.println(cat1);

//	}

}
