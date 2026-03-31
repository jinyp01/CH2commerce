import java.util.*;

public class CommerceSystem {

    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private Map<Integer, Category> categoryList = new HashMap<>();
    // Category 클래스들을 담는 리스트를 추가
    // 이후 반복문에서 Category 리스트 -> Product 리스트 -> Product
    // 순서대로 정보를 출력할 수 있다.

    private Customer customer = new Customer();
    private Administrator admin = new Administrator();
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
        int managementNum = categories.size();

        while(menuNum != 0) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            for(int i=0; i<categories.size(); i++){
                System.out.printf("%-4s %s\n", (i+1)+".", categories.get(i).getCategoryName());
            }
            System.out.printf("%-4s %s\n", (managementNum+3)+".", "관리자 모드");

            // 잘못된 입력에 대한 예외처리
            try {
                menuNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요!\n");
                scanner.next();
                continue;
            }
            if (menuNum == 0) {
                System.out.println("커머스 플랫폼을 종료합니다. ");
                break;
            }

            // 관리자 모드로 접근하기위한 알고리즘
            if (menuNum == managementNum+3){
                System.out.println("관리자 비밀번호를 입력해주세요");
                int adminTry = 0;
                while (adminTry<3){
                    if(scanner.next().equals(admin.getPassword())){
                        // 관리자메뉴로 접근
                        adminMenu();
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다!");
                        adminTry += 1;
                    }
                }
                if ( customer.getCustomerCart().isEmpty()) {
                    menuNum = -1; return;
                } else {
                    menuNum = -2; return;
                }
            }

            if(menuNum < 0 || menuNum > categories.size()) {
                System.out.println("해당 번호의 카테고리가 없습니다.");
                continue;
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

    public void adminMenu(){

        Scanner scanner = new Scanner(System.in);

        while (menuNum != 0) {
            System.out.println("[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");
            int selectAdminMenu = -1;
            try{
                System.out.println("관리모드를 선택해주세요");
                selectAdminMenu = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("올바른 숫자를 입력해주세요!");
                scanner.nextLine();
            }
            switch (selectAdminMenu) {
                case 0:
                    System.out.println("관리메뉴를 종료합니다");
                    menuNum = 0;
                    break;
                case 1:
                    System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
                    for(int i=0; i<categories.size(); i++) {
                        System.out.println((i+1) + ". " + categories.get(i).getCategoryName());
                    }
                    int categoryIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (categoryIndex < 0 || categoryIndex >= categories.size()) {
                        System.out.println("잘못된 카테고리 번호입니다.");
                        continue;
                    }

                    Category c = categories.get(categoryIndex);
                    System.out.println("[ " + c.getCategoryName() + " 카테고리에 상품 추가 ]");

                    System.out.print("상품명을 입력해주세요: ");
                    String new_productName = scanner.nextLine();
                    for(Product p : c.getProducts()){
                        if(p.getProductName().equals(new_productName)){
                            System.out.println("같은 이름의 상품이 이미 존재합니다!");
                            new_productName = null;
                            break;
                        }
                    }
                    if(new_productName == null) break;

                    System.out.print("가격을 입력해주세요: ");
                    int new_productPrice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("상품 설명을 입력해주세요: ");
                    String new_productDescription = scanner.nextLine();

                    System.out.print("재고수량을 입력해주세요: ");
                    int new_productStock = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println(new_productName + " | "
                    + new_productPrice + " | "
                    + new_productDescription + " | "
                    + "재고: " + new_productStock + "개");
                    System.out.println("위 정보로 상품을 추가하시겠습니까?");
                    System.out.println("1. 확인      2. 취소");
                    switch (scanner.nextInt()){
                        case 1:
                            c.addProduct(new Product(new_productName, new_productPrice
                            , new_productDescription, new_productStock, c.getCategoryNum()));
                            System.out.println("상품이 성공적으로 추가되었습니다!");
                            break;
                        case 2:
                            System.out.println("상품 추가가 취소되었습니다.");
                            break;
                        default:
                            System.out.println("잘못된 입력입니다.");
                    }
                    break;
                case 2:
                    System.out.print("수정할 상품명을 입력해주세요: ");
                    String EditProduct = scanner.nextLine();
                    Product currentProduct = null;
                    for(Category category : categories){
                        for(Product product : category.getProducts()){
                            if (product.getProductName().equals(EditProduct)) {
                                currentProduct = product;
                                break;
                            }
                        }
                    }
                    if(currentProduct == null) {
                        System.out.println("해당 상품이 존재하지 않습니다!");
                        break;
                    }
                    System.out.printf("현재 상품 정보: %s | %d원 | %s | 재고 : %d개",
                            currentProduct.getProductName(),
                            currentProduct.getProductPrice(),
                            currentProduct.getProductDescription(),
                            currentProduct.getProductStock());
                    System.out.print("수정할 항목을 선택해주세요\n");
                    System.out.println("1. 가격\n2. 설명\n3. 재고수량");
                    int selectEditMenu;
                    try {
                        selectEditMenu = scanner.nextInt();
                    }catch (InputMismatchException e) {
                        System.out.println("숫자를 입력하세요");
                        scanner.nextLine();
                        break;
                    }
                    switch (selectEditMenu) {
                        case 1:
                            System.out.println("현재 가격 : " + currentProduct.getProductPrice());
                            System.out.print("새로운 가격을 입력해주세요");
                            try {
                                int edit_productPrice = scanner.nextInt();
                                System.out.printf("%s의 가격이 %d원 -> %d원으로 수정되었습니다.\n",
                                        currentProduct.getProductName(),
                                        currentProduct.getProductPrice(),
                                        edit_productPrice);
                                currentProduct.setProductPrice(edit_productPrice);
                            } catch(InputMismatchException e) {
                                System.out.println("올바른 정보를 입력해주세요");
                                scanner.nextLine();
                            } break;
                        case 2:
                            scanner.nextLine();
                            System.out.println("현재 상품 설명 : " + currentProduct.getProductDescription());
                            System.out.print("새로운 상품 설명을 입력해주세요 : ");
                            String edit_productDescription = scanner.nextLine();
                            currentProduct.setProductDescription(edit_productDescription);
                            System.out.println("상품설명이 성공적으로 변경되었습니다!");
                            System.out.println("현재 상품 설명 : " + edit_productDescription + "\n");
                            break;
                        case 3:
                            System.out.println("현재 재고수량 : " + currentProduct.getProductStock());
                            System.out.print("변경된 재고수량을 입력해주세요 : ");
                            try {
                                int edit_productStock = scanner.nextInt();
                                System.out.printf("재고수량이 %d개 -> %d개로 변경되었습니다!\n",
                                        currentProduct.getProductStock(),
                                        edit_productStock);
                                currentProduct.setProductStock(edit_productStock);
                            } catch (InputMismatchException e) {
                                System.out.println("올바른 정보를 입력해주세요");
                                scanner.nextLine();
                            }
                    }
                    break;
                case 3:
                    System.out.print("삭제할 상품명을 입력해주세요 : ");
                    Product deleteProduct = null;
                    String deleteProductName = scanner.nextLine();
                    for(Category category : categories){
                        for(Product product : category.getProducts()){
                            if (product.getProductName().equals(deleteProductName)) {
                                deleteProduct = product;
                                break;
                            }
                        }
                    }
                    if (deleteProduct == null) {
                        System.out.println("입력된 이름의 상품이 없습니다!");
                        break;
                    }
                    System.out.printf("정말 %s 상품을 삭제하시겠습니까?\n1. 확인   2. 취소\n", deleteProductName);
                    int selectdelete = 0;
                    try{
                        selectdelete = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("숫자를 입력해주세요!");
                        scanner.nextLine();
                    }
                    switch (selectdelete){
                        case 1:
                            customer.getCustomerCart().removeIf(p->p.getProductName().equals(deleteProductName));
                            for (Category category : categories) {
                                List<Product> products = category.getProducts();

                                for (int i = 0; i < products.size(); i++) {
                                    category.getProducts().
                                            removeIf(p->p.getProductName().equals(deleteProductName));
                                }
                            }
                            break;
                        case 2:
                            System.out.println("삭제가 취소되었습니다!");
                            break;
                        default:
                            System.out.println("잘못된 입력입니다!");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("전체 상품 현황");
                    for(Category category : categories){
                        System.out.println(category.getCategoryName() + " 상품 현황");

                        for(int i=0; i<category.getProducts().size(); i++){
                            Product p = category.getProducts().get(i);
                            System.out.printf("%-4s | %-20s | %,10d | %-30s\n", (i+1) + "." ,
                                    p.getProductName(),
                                    p.getProductPrice(),
                                    p.getProductDescription());
                        }
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다!");
            }
            System.out.println();
        }
        if(customer.getCustomerCart().isEmpty()){
            menuNum = -1;
        } else menuNum = -2;
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
            System.out.printf("%-4s %s\n", (managementNum+3)+".", "관리자 모드");


            // 잘못된 입력에 대한 예외처리
            try {
                menuNum = scanner.nextInt();
                if(menuNum < 0 || menuNum > managementNum+3) {
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
            if (menuNum == managementNum+3){
                System.out.println("관리자 비밀번호를 입력해주세요");
                int adminTry = 0;
                while (adminTry<3){
                    if(scanner.next().equals(admin.getPassword())){
                        // 관리자메뉴로 접근
                        adminMenu();
                        break;
                    } else {
                        System.out.println("잘못된 입력입니다!");
                        adminTry += 1;
                    }
                }
                if ( customer.getCustomerCart().isEmpty()) {
                    menuNum = -1; return;
                } else {
                    menuNum = -2; return;
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
