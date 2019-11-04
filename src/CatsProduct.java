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
	
	
	//Prints a single cat for details with formatted display
	public String pretty() {
		String FORMAT_STRING = " %-12s || %s\n";
		String MONEY_FORMAT_STRING = "$%.2f";
		return "============================================\n" +
				String.format(FORMAT_STRING, "Cat Name", name) + 
				String.format(FORMAT_STRING, "Category", category) + 
				String.format(FORMAT_STRING, "Description", description) +
				String.format(FORMAT_STRING, "Price", String.format(MONEY_FORMAT_STRING, price));
		
		
		
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	//Used to print to file 
	@Override
	public String toString() {
		return name + "," + category + "," + description + "," + price;

	}
	
	public static CatsProduct fromString(String csvRow) {
		   String[] csvMembers = csvRow.split(",");
		   return new CatsProduct(csvMembers[0], csvMembers[1], csvMembers[2], new BigDecimal(csvMembers[3]));
		}

	public static void main(String[] args) {
	CatsProduct cat1 = new CatsProduct("Murloc", "Tuxedo Cat", "Cute", new BigDecimal("100.00"));
	System.out.println(cat1.pretty());

	}

}
