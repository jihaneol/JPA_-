package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository; // 값이 안들어오니 빨간불

    /**
     * 회원 가입
     */
    @Transactional // 쓰기
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member); // 영속성 컨테스트 넣을시 key값(id)은 있다.
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
