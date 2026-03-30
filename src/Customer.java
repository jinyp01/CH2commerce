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

    // 고객의 장바구니에 어떤식으로 살려는 물품의 정보를 저장할까
    // 장바구니 클래스를 신설해야되는가?
    // 고객 클래스에 추가항목을 만들어야하는가?
    // 고객별 장바구니로 정보를 저장한다면 고객클래스에 항목을 추가하는 것이 맞는데
    // 차라리 고객클래스에서 프로덕트 리스트를 저장하는게 좋을 것 같다.
    // 다만 menuNum을 저장해두는 것이 나중이 탐색이 쉬울테니 같이 저장해두려면
    // Hash는 못쓰겠다. category name 과 product 간에 1대1 매칭이 이루어지지 않는다.
    // 그냥 Product에 menuNum 하나만 추가해서 저장하고 싶은데 흠...



}
