import java.util.*;

public class Customer {

    // 필수 기능의 예시대로 만들어두고 사용은 안하고 있습니다.
    private String customerName;
    private String customerEmail;
    private String customerVIP;

    // 사용자의 장바구니
    List<Product> customerCart = new ArrayList<>();

    public Customer () {};

    // 사용자의 장바구니에 상품을 담습니다.
    public void addCustomerCart (Product product) {
        this.customerCart.add(product);
    }

    // 사용자의 장바구니의 내용물을 반환합니다.
    public List<Product> getCustomerCart() {
        return customerCart;
    }

    // 사용자의 쇼핑카트의 내용물을 확인하는 메소드
    // 구매과정 buyShoppingCart() 로 넘어가 수 있습니다.
    public void showShoppingCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int total = 0; // 전체 결제 금액

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
                buyShoppingCart();
                customerCart.clear();
                break;
            case 2:
                break;
        }
    }


    // 실제 구매가 이루어지는 과정
    // 구매 도중에 재고가 떨어질 경우 계산 과정에서 제외됩니다.
    public void buyShoppingCart() {
        Scanner scanner = new Scanner(System.in);
        int total = 0;

        System.out.println("고객 등급을 입력해주세요.");
        for(VIP vip : VIP.values()){
            System.out.printf("%d. %s  %15s 할인\n", vip.getSelectVIP(),
                    vip.name(), ": "+(vip.getDiscountRate()*100)+"%");
        }


        // selectVIP 를 사용해서 VIP 등급을 결정합니다.
        // VIP 등급에 관한 예외처리
        int selectVIP=-1;
        while(true){
            try{
                selectVIP = scanner.nextInt();
                if(selectVIP<0 || selectVIP>VIP.values().length){
                    System.out.println("해당되는 등급이 없습니다.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                scanner.nextLine();
            }
        }

        // 등급 입력이 완료되면 상품 하나하나의 재고를 확인하며 가격을 계산합니다
        // 결제도중 재고가 소진된 상품에 대해서는 제외하고 계산됩니다.
        for (Product p : customerCart) {
            if(p.getProductStock()-1 < 0) {
                System.out.printf("%s의 재고가 소진되었습니다!\n", p.getProductName());
                continue;
            }
            System.out.printf("%s 재고가 %d -> %d개로 업데이트되었습니다.\n",
                    p.getProductName(),
                    p.getProductStock(),
                    p.getProductStock()-1);
            p.setProductStock(p.getProductStock()-1);
            total += p.getProductPrice();
        }

        // VIP 등급에 대한 할인 계산 진행
        // vip.getSelectVIP    사용자 입력과의 매치를 통해 VIP 등급을 탐색합니다.
        // 1 = BRONZE, 2 = SILVER, 3 = GOLD, 4 = PLATINUM
        // 같은 방식으로 할인율을 구해와서 계산을 진행합니다.
        for(VIP vip : VIP.values()){
            if(vip.getSelectVIP() == selectVIP){
                System.out.println("할인 전 금액: " + total);
                System.out.printf("%s 등급 할인(%s): %s원\n", vip.name(),
                        vip.getDiscountRate()+"%",
                        "-"+total*vip.getDiscountRate());
                System.out.printf("최종 결제 금액: %f원",
                        total - (total*vip.getDiscountRate()));
            }
        }
    }

    public void cancelShoppingCart() {
        System.out.println("주문이 취소되었습니다!");
        customerCart.clear();
    }
}
