package hello.core.member;

public interface MemberService {

    //비즈니스 로직인 회원가입에서는 저장 뿐 아니라 다양한 기능들이 포함되므로
    //그 의미를 내포하는 join이 save라는 이름보다는 조금 더 적절하다.
    void join(Member member);
    Member findMember(Long memberId);
}
