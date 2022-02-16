package toyProject.juniorForum.domain.member;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {
    private static final Map<Long, Member> members = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Member join(Member member) {
        member.setId(++sequence);
        return member;
    }

    public Member findById(Long memberId) {
        Member member = members.get(memberId);
        return member;
    }
}
