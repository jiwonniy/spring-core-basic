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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //=> 2번 호출..? 싱글톤이 깨질까 안깨질까?!

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberService
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //결과
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    @Bean //-> 컨테이너라는 곳에 저장함.
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    //MemberRepository 역할
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //정률 할인 정책으로 간편하게 변경! (사용영역을 변경하지 않음)
    }
}
