import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Product product1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10);
        Product product2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 10);
        Product product3 = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10);
        Product product4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        CommerceSystem commerceSystem = new CommerceSystem(products);
        commerceSystem.start();
        // products 상품리스트를 만들어서 commerceSystem으로 전달
        // commerceSystem에서 상품 목록 출력과 선택 반복문 실행

    }
}