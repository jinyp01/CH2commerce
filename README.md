커머스 과제 수행
List<Category> -> List<Product> 를 사용하여 전체 상품목록을 관리하고 있습니다.

CommerceSystem.java
adminMenu(), cartMenu(), defaultMenu() 로 나눠 메뉴화면을 관리하고 있습니다.
showProduct(List<Product> products) 상품 목록을 출력하는 메서드로 필터링된 상품목록을 받거나
전체 상품목록을 받아 출력하고 장바구니에 넣는 과정까지 수행할 수 있습니다.
exampleProduct() 프로그램 실행에 필요한 상품목록을 생성하는 과정을 맡고있습니다.

Customer.java
cartMenu()에서 장바구니 선택 -> showShoppingCart() -> buyShoppingCart() 장바구니 목록을 보여준 다음 구매 선택 시 구매과정으로 연결됩니다.
cancelShoppingCart() 구매 취소 메서드
