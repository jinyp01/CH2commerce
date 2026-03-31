
 class Product {

    private String productName;
    private int productPrice;
    private String productDescription;
    private int productStock;
    private int categoryNum;



    public Product(String productName, int productPrice, String productDescription, int productStock ) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStock = productStock;
    }

     public Product(String productName, int productPrice, String productDescription, int productStock, int categoryNum) {
         this.productName = productName;
         this.productPrice = productPrice;
         this.productDescription = productDescription;
         this.productStock = productStock;
         this.categoryNum = categoryNum;
     }


     public String getProductName() {
         return productName;
     }

     public int getProductPrice() {
         return productPrice;
     }

     public String getProductDescription() {
         return productDescription;
     }

     public int getProductStock() {
         return productStock;
     }

     public void setProductPrice(int productPrice) {
         this.productPrice = productPrice;
     }

     public void setProductDescription(String productDescription) {
         this.productDescription = productDescription;
     }

     public void setProductStock(int productStock) {
         this.productStock = productStock;
     }

     public void setCategoryNum(int categoryNum) {
         this.categoryNum = categoryNum;
     }











 }
