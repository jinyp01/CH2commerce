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

        System.out.println(" 실시간 커머스 플랫폼 - 전자제품 ");
        for(int i = 0; i < products.size(); i++){
            Product p = products.get(i);
            System.out.printf("%-4d. | %-20s | %,10d | %-30s\n", (i+1),
            p.getProductName(),
            p.getProductPrice(),
            p.getProductDescription());
        }
        System.out.printf("%-4d. | 종료  %15s| 프로그램 종료", 0, "");
        // 인터넷에서 확인한 줄맞춤하여 출력하기
        // %-4d, 음수는 왼쪽정렬, 4번째 칸부터
        // %20s, 양수는 20번째 칸부터 오른쪽 정렬

        System.out.println();
        int menuNum = scanner.nextInt();
        if(menuNum == 0) System.out.println("프로그램을 종료합니다. ");





    }
}