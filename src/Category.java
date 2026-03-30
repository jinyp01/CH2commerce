import java.util.ArrayList;
import java.util.List;

public class Category {

    private String categoryName;
    private int categoryNum;
    private List<Product> products = new ArrayList<>();

    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public Category(String categoryName, List<Product> products, int categoryNum) {
        this.categoryName = categoryName;
        this.products = products;
        this.categoryNum = categoryNum;
        init();
    }

    private void init() {
        for(Product p : products) {
            p.setCategoryNum(this.categoryNum);
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getCategoryNum() {
        return categoryNum;
    }


}
