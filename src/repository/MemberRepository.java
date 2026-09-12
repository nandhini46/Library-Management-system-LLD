package repository;

import model.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private final List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public Member findById(String memberId) {

        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }

        return null;
    }

    public List<Member> getAllMembers() {
        return members;
    }
}