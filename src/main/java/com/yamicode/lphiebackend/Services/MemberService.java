package com.yamicode.lphiebackend.Services;

import com.yamicode.lphiebackend.Models.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private final List<Member> members = new ArrayList<>();
    private Long idCounter = 1L;

    public Member createMember(Member member) {
        member.setId(idCounter++);
        members.add(member);
        return member;
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public Member updateMember(Long id, Member updatedMember){
        for(Member m : members) {
            if(m.getId().equals(id)) {
                m.setFirstName(updatedMember.getFirstName());
                m.setLastName(updatedMember.getLastName());
                m.setRole(updatedMember.getRole());
            }
        }
        return null;
    }

    public Member getMemberById(Long id) {
        for(Member m : members) {
            if(m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public boolean deleteMember(Long id){
        return members.removeIf(member -> member.getId().equals(id));
    }
}
