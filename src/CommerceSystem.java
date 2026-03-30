import java.util.*;

public class CommerceSystem {

    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private Map<Integer, Category> categoryList = new HashMap<>();
    // Category 클래스들을 담는 리스트를 추가
    // 이후 반복문에서 Category 리스트 -> Product 리스트 -> Product
    // 순서대로 정보를 출력할 수 있다.

    private Customer customer = new Customer();
    private int menuNum;

    public CommerceSystem(){}
    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    // menuNum 을 전역변수로 설정하고
    // menuNum의 값에 따라 관리메뉴의 활성화를 경정하도록 하였다.
    // start() 에서는 defaultMenu() 와 cartMenu()를 menuNum에 따라 선택하는 기능만을 남겼다.
    public void start() {
        menuNum = -1;
        exampleProduct();
        // 임시 상품데이터들을 카테고리에 맞춰서 생성

        while (menuNum != 0) {
            if (menuNum == -1) defaultMenu();
            else cartMenu();
        }
    }

    // 맨 처음에 활성화되는 메뉴
    // 관리메뉴를 표시하지 않는다.
    public void defaultMenu() {
        Scanner scanner = new Scanner(System.in);
        menuNum = -1;
        int managementNum = -1;

        while(menuNum != 0) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for(int i=0; i<categories.size(); i++){
                System.out.printf("%-4s %s\n", (i+1)+".", categories.get(i).getCategoryName());
            }

            // 잘못된 입력에 대한 예외처리
            try {
                menuNum = scanner.nextInt();
                if(menuNum < 0 || menuNum > categories.size()) {
                    System.out.println("해당 번호의 카테고리가 없습니다.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요!\n");
                scanner.next();
                continue;
            }
            if (menuNum == 0) {
                System.out.println("커머스 플랫폼을 종료합니다. ");
                break;
            }
            while(menuNum != 0) {
                Category category = null;
                category = categories.get(menuNum-1);
                Product p = null;
                System.out.println();
                System.out.printf("%s 카테고리\n", category.getCategoryName());
                for(int i=0; i<category.getProducts().size(); i++){
                    p = category.getProducts().get(i);
                    System.out.printf("%-4s | %-20s | %,10d | %-30s\n", (i+1) + "." ,
                            p.getProductName(),
                            p.getProductPrice(),
                            p.getProductDescription());
                }
                System.out.printf("0. 뒤로가기\n");
                int input = scanner.nextInt();
                System.out.println();
                if (input == 0){
                    if ( customer.getCustomerCart().isEmpty()) {
                        menuNum = -1; return;
                    } else {
                        menuNum = -2; return;
                    }
                }
                else {
                    p = category.getProducts().get(input-1);
                    System.out.printf("선택한 상품 %s | %d원 | %s | 재고: %d개\n",
                            p.getProductName(),
                            p.getProductPrice(),
                            p.getProductDescription(),
                            p.getProductStock());
                    System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                    System.out.printf("1. 확인 %8s\n", "2. 취소");
                    switch (scanner.nextInt()){
                        case 1: if(p.getProductStock() != 0) {
                            System.out.println(p.getProductName() + "가 장바구니에 추가되었습니다.");
                            customer.addCustomerCart(p);
                        } else {
                            System.out.println("재고가 부족합니다!");
                            break;
                        }
                        case 2: break;
                        default:
                            System.out.println("잘못된 입력입니다!");
                    }
                }
            }
        }
    }

    // 관리메뉴를 같이 출력하는 함수
    public void cartMenu() {
        Scanner scanner = new Scanner(System.in);
        menuNum = -2;
        int managementNum = -1;

        while(menuNum != 0) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for(int i=0; i<categories.size(); i++){
                System.out.printf("%-4s %s\n", (i+1)+".", categories.get(i).getCategoryName());
            }
            managementNum = categories.size();
            System.out.println();
            System.out.printf("%-4s\n", "[ 주문 관리 ]");
            System.out.printf("%-4s %-14s | %s\n",
                    (managementNum+1) + ".",
                    "장바구니 확인",
                    "장바구니를 확인 후 주문합니다.");

            System.out.printf("%-4s %-14s | %s\n",
                    (managementNum+2) + ".",
                    "주문 취소",
                    "진행중인 주문을 취소합니다.");


            // 잘못된 입력에 대한 예외처리
            try {
                menuNum = scanner.nextInt();
                if(menuNum < 0 || menuNum > managementNum+2) {
                    System.out.println("해당 번호의 카테고리가 없습니다.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요!\n");
                scanner.next();
                continue;
            }
            if (menuNum == 0) {
                System.out.println("커머스 플랫폼을 종료합니다. ");
                break;
            }

            if (menuNum == managementNum+1){
                customer.showShoppingCart();
                menuNum = -1;
                return;
            } else if (menuNum == managementNum+2) {
                System.out.println("주문을 취소하시겠습니까?");
                System.out.printf("%-4s %-14s\n",
                        "1. 주문 취소",
                        "2. 메인메뉴로 돌아가기");
                switch ( scanner.nextInt() ){
                    case 1:
                        customer.cancelShoppingCart();
                        menuNum = -1;
                        return;
                    case 2: continue;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        continue;
                }
            }
            while(menuNum != 0) {
                Category category = null;
                category = categories.get(menuNum-1);

                Product p = null;
                System.out.println();
                System.out.printf("%s 카테고리\n", category.getCategoryName());
                for(int i=0; i<category.getProducts().size(); i++){
                    p = category.getProducts().get(i);
                    System.out.printf("%-4s | %-20s | %,10d | %-30s\n", (i+1) + "." ,
                            p.getProductName(),
                            p.getProductPrice(),
                            p.getProductDescription());
                }
                System.out.printf("0. 뒤로가기\n");
                int input = scanner.nextInt();
                System.out.println();
                if (input == 0){ menuNum = -1; break; }
                else {
                    p = category.getProducts().get(input-1);
                    System.out.printf("선택한 상품 %s | %d원 | %s | 재고: %d개\n",
                            p.getProductName(),
                            p.getProductPrice(),
                            p.getProductDescription(),
                            p.getProductStock());
                    System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                    System.out.printf("1. 확인 %8s\n", "2. 취소");
                    switch (scanner.nextInt()){
                        case 1: if(p.getProductStock() != 0) {
                            System.out.println(p.getProductName() + "가 장바구니에 추가되었습니다.");
                            customer.addCustomerCart(p);
                        } else {
                            System.out.println("재고가 부족합니다!");
                            break;
                        }
                        case 2: break;
                        default:
                            System.out.println("잘못된 입력입니다!");
                    }
                }
            }
        }
    }


    public void exampleProduct() {
        // Category 클래스에 넣을 3가지의 예시 카테고리와  제품들의 데이터를
        // 입력하는 코드를 작성해달라고 ChatGPT에 요청

        // 전자제품
        Product e1 = new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10, 1);
        Product e2 = new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 10, 1);
        Product e3 = new Product("MacBook Pro", 2400000, "M3 칩셋 노트북", 10, 1);

        List<Product> electronics = new ArrayList<>();
        electronics.add(e1);
        electronics.add(e2);
        electronics.add(e3);

        // 의류
        Product c1 = new Product("나이키 후드티", 89000, "기모 안감 후드티", 20, 2);
        Product c2 = new Product("아디다스 트레이닝복", 99000, "편한 착용감의 트레이닝 세트", 15, 2);
        Product c3 = new Product("유니클로 청바지", 59000, "슬림핏 데님 팬츠", 25, 2);

        List<Product> clothes = new ArrayList<>();
        clothes.add(c1);
        clothes.add(c2);
        clothes.add(c3);

        // 식품
        Product f1 = new Product("한우 등심 1kg", 120000, "1등급 한우", 5, 3);
        Product f2 = new Product("제주 감귤 5kg", 30000, "산지 직송 감귤", 30, 3);
        Product f3 = new Product("유기농 계란 30구", 15000, "무항생제 유기농 계란", 40, 3);

        List<Product> foods = new ArrayList<>();
        foods.add(f1);
        foods.add(f2);
        foods.add(f3);

        Category category1 = new Category("전자제품", electronics, 1);
        Category category2 = new Category("의류", clothes, 2);
        Category category3 = new Category("식품", foods, 3);

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
    }
}
