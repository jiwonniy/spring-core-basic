package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private  final MemberRepository memberRepository = new MemoryMemberRepository();
    private  final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인에 대한 것은 discountPolicy가 수행하고 결과만 받음, 단일체계원칙을 잘 지킨 것임!

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
