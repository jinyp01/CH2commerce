import java.util.*;

public class Customer {

    private String customerName;
    private String customerEmail;
    private String customerVIP;

    List<Product> customerCart = new ArrayList<>();

    public Customer () {};
    public Customer (List<Product> customerCart) {
        this.customerCart = customerCart;
    }

    public void addCustomerCart (Product product) {
        this.customerCart.add(product);
    }

    public List<Product> getCustomerCart() {
        return customerCart;
    }

    public void showShoppingCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int total = 0;

        for (Product p : customerCart) {
            System.out.printf("%-4s | %-20s | %10s | %-30s\n",
                    p.getProductName(),
                    p.getProductPrice()+"원",
                    p.getProductDescription(),
                    "수량 : " + p.getProductStock()+"개");
            total += p.getProductPrice();
        }

        System.out.println("[ 총 주문 금액 ]");
        System.out.println(total + "원");
        System.out.printf("%-4s %10s", "1. 주문 확정", "2. 메인으로 돌아가기\n");
        switch(scanner.nextInt()){
            case 1:
                System.out.println("주문이 완료되었습니다! 총 금액 : " + total);
                buyShoppingCart();
                break;
            case 2:
                break;
        }
    }


    public void buyShoppingCart() {
        int total = 0;
        for (Product p : customerCart) {
            if(p.getProductStock()-1 < 0) {
                System.out.printf("%s의 재고가 소진되었습니다!\n", p.getProductName());
                total -= p.getProductPrice();
            }
            System.out.printf("%s 재고가 %d -> %d개로 업데이트되었습니다.\n",p.getProductName(), p.getProductStock(), p.getProductStock()-1);
            total += p.getProductPrice();
        }
    }

    public void cancelShoppingCart() {
        System.out.println("주문이 취소되었습니다!");
        customerCart.clear();
    }
}
