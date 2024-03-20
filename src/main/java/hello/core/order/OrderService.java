package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); //4. 주문 결과를 반환한다. (3가지 반환)
}
