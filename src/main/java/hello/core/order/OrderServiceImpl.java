package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정할인금액
    //=> 문제점: FixDiscountPolicy (구체화)에 의존하고 있음, DiscountPolicy(추상화)에만 의존하고 있었던 것이 아님.
    //=> 문제점: 따라서 DIP 위반

    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //10%할인
    //=> 문제점, 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 위와 같이 갈아 끼워야됨.
    //=> OCP, DIP 준수를 안하고 있음

    //추상화에 의존하도록 변경!
    //MemoryMemberRepository와 FixDiscountPolicy 객체의 의존관계가 주입된다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //=> NPE 발생하므로, 누군가가 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야한다!

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인에 대한 것은 discountPolicy가 수행하고 결과만 받음, 단일체계원칙을 잘 지킨 것임!

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
