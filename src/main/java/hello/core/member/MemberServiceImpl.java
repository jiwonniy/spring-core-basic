package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //final : 객체 변경을 강제할 수 있는 final 키워드의 장점을 가져가면서 의존관계 주입을 사용할 수 있기 때문에
    //final 키워드를 사용한 생성자 주입을 권장하여 사용한 것
    //private  final MemberRepository memberRepository = new MemoryMemberRepository();
    //=> 문제점: 실제 할당하는 부분이 구현체를 의존함.. 추상화에도 의존, 구체화에도 의존 DIP 를 위반하고 있다.

    //MemberRepository의 구현체가 뭐가 들어갈지를 생성자를 통해서 결정
    //여기 어디에도 MemoryMemberRepository가 없음. **생성자 주입**
    private  final MemberRepository memberRepository;

    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public  void join(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

    //테스트 용도 (싱글톤 확인)
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
