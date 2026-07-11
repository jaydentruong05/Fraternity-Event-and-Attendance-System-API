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

        if(member.getFirstName() == null || member.getFirstName().isBlank()){
            throw new IllegalArgumentException("First name is required");
        }

        if(member.getLastName() == null || member.getLastName().isBlank()){
            throw new IllegalArgumentException("Last name is required");
        }

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
                if(updatedMember.getFirstName() != null && !updatedMember.getFirstName().isBlank()) {
                    m.setFirstName(updatedMember.getFirstName());
                }
                if(updatedMember.getLastName() != null && !updatedMember.getLastName().isBlank()) {
                    m.setLastName(updatedMember.getLastName());
                }
                if(updatedMember.getRole() != null) {
                    m.setRole(updatedMember.getRole());
                }
                return m;
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
