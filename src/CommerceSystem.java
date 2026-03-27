import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    // Category 클래스들을 담는 리스트를 추가
    // 이후 반복문에서 Category 리스트 -> Product 리스트 -> Product
    // 순서대로 정보를 출력할 수 있다.


    public CommerceSystem(){}
    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int menuNum = -1;

        exampleProduct();
        // 임시 상품데이터들을 카테고리에 맞춰서 생성

        while(menuNum != 0) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for(int i=0; i<categories.size(); i++){
                System.out.printf("%-4s %s\n", (i+1)+".", categories.get(i).getCategoryName());
            }
            System.out.printf("0. 종료 %10s", "| 프로그램 종료\n");
            menuNum = scanner.nextInt();
            if (menuNum == 0) {
                System.out.println("커머스 플랫폼을 종료합니다. ");
                break;
            }

            while(menuNum != 0) {
                Product p = null;
                System.out.printf("%s 카테고리\n", categories.get(menuNum-1).getCategoryName());
                for(int i=0; i<categories.get(menuNum-1).getProducts().size(); i++){
                    p = categories.get(menuNum-1).getProducts().get(i);
                    System.out.printf("%-4s | %-20s | %,10d | %-30s\n", (i+1) + "." ,
                        p.getProductName(),
                        p.getProductPrice(),
                        p.getProductDescription());
                }
                System.out.printf("0. 뒤로가기\n");
                menuNum = scanner.nextInt();
                System.out.println();
                if (menuNum == 0){ menuNum = -1; break; }
                else {
                    System.out.printf("선택한 상품 %s | %d원 | %s | 재고: %d개\n",
                            p.getProductName(),
                            p.getProductPrice(),
                            p.getProductDescription(),
                            p.getProductStock());
                }
            }
        }

//        while(menuNum != 0){
//            System.out.println(" 실시간 커머스 플랫폼 - 전자제품 ");
//            for(int i = 0; i < products.size(); i++){
//                Product p = products.get(i);
//                // 점을 바로 뒤에 붙여서 출력되도록 바꾸기 위해 %s 로 출력
//                System.out.printf("%-4s | %-20s | %,10d | %-30s\n", (i+1) + "." ,
//                        p.getProductName(),
//                        p.getProductPrice(),
//                        p.getProductDescription());
//            }
//            System.out.printf("%-4d. | 종료  %15s| 프로그램 종료", 0, "");
//
//            // 인터넷에서 확인한 줄맞춤하여 출력하기
//            // %-4d, 음수는 왼쪽정렬, 4번째 칸부터
//            // %20s, 양수는 20번째 칸부터 오른쪽 정렬
//
//            System.out.println();
//            menuNum = scanner.nextInt();
//        }
//
//        System.out.println("프로그램을 종료합니다. ");
    }

    public void exampleProduct() {
        // Category 클래스에 넣을 3가지의 예시 카테고리와  제품들의 데이터를
        // 입력하는 코드를 작성해달라고 ChatGPT에 요청

        // 전자제품
        Product e1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10);
        Product e2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 10);
        Product e3 = new Product("MacBook Pro", 2400000, "M3 칩셋 노트북", 10);

        List<Product> electronics = new ArrayList<>();
        electronics.add(e1);
        electronics.add(e2);
        electronics.add(e3);

        // 의류
        Product c1 = new Product("나이키 후드티", 89000, "기모 안감 후드티", 20);
        Product c2 = new Product("아디다스 트레이닝복", 99000, "편한 착용감의 트레이닝 세트", 15);
        Product c3 = new Product("유니클로 청바지", 59000, "슬림핏 데님 팬츠", 25);

        List<Product> clothes = new ArrayList<>();
        clothes.add(c1);
        clothes.add(c2);
        clothes.add(c3);

        // 식품
        Product f1 = new Product("한우 등심 1kg", 120000, "1등급 한우", 5);
        Product f2 = new Product("제주 감귤 5kg", 30000, "산지 직송 감귤", 30);
        Product f3 = new Product("유기농 계란 30구", 15000, "무항생제 유기농 계란", 40);

        List<Product> foods = new ArrayList<>();
        foods.add(f1);
        foods.add(f2);
        foods.add(f3);

        Category category1 = new Category("전자제품", electronics);
        Category category2 = new Category("의류", clothes);
        Category category3 = new Category("식품", foods);


        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
    }
}
