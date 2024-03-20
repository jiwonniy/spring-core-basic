package hello.core.member;

public interface MemberRepository {

    //Repository는 주로 데이터의 CRUD 연산과 관련된 표준화된 이름을 사용
    //이렇게 함으로써, 영속성 영역과 도메인 로직을 명확하게 분리하고, 각 영역의 책임을 명확히!
    void save(Member member);
    Member findById(Long memberId);
}
