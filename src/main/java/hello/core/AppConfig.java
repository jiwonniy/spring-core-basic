package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //**IoC 컨테이너, DI 컨테이너, 어셈블러, 오브젝트 팩토리 등으로 불린다~**
    //appConfig 객체는 memoryMemberRepository 객체를 생성하고,
    //그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
    //클라이언트인 memberServiceImpl 입장에서는 의존 관계를 마치 외부에서 주입해주는 것과 같음!
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    //MemberRepository 역할
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //정률 할인 정책으로 간편하게 변경! (사용영역을 변경하지 않음)
    }
}
