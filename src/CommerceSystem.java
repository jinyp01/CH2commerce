import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private List<Product> products = new ArrayList<>();

    public CommerceSystem(){}
    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int menuNum = -1;

        while(menuNum != 0){
            System.out.println(" 실시간 커머스 플랫폼 - 전자제품 ");
            for(int i = 0; i < products.size(); i++){
                Product p = products.get(i);
                // 점을 바로 뒤에 붙여서 출력되도록 바꾸기 위해 %s 로 출력
                System.out.printf("%-4s | %-20s | %,10d | %-30s\n", (i+1) + "." ,
                        p.getProductName(),
                        p.getProductPrice(),
                        p.getProductDescription());
            }
            System.out.printf("%-4d. | 종료  %15s| 프로그램 종료", 0, "");

            // 인터넷에서 확인한 줄맞춤하여 출력하기
            // %-4d, 음수는 왼쪽정렬, 4번째 칸부터
            // %20s, 양수는 20번째 칸부터 오른쪽 정렬

            System.out.println();
            menuNum = scanner.nextInt();
        }

        System.out.println("프로그램을 종료합니다. ");

    }
}
