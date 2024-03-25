package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //appConfig 객체는 memoryMemberRepository 객체를 생성하고,
    //그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
    //클라이언트인 memberServiceImpl 입장에서는 의존 관계를 마치 외부에서 주입해주는 것과 같음!
    public MemberService memberService(){

        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }


}
