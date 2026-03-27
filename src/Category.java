import java.util.ArrayList;
import java.util.List;

public class Category {

    private String categoryName;
    private List<Product> products = new ArrayList<>();

    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }
}
