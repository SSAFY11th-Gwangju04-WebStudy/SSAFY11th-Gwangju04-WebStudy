package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //select m from Member m where m.name = ? 식으로 JPA가 만들어줌
    //이름을 맞춰주면 알아서 만들어줌
    @Override
    Optional<Member> findByName(String name);
}
/*
CRUD 등이 모두 Spring JPA에 갖고 있어 제공된다.
공통화가 되지 않는 것은 따로 적어줄 것.
 */