package com.yamicode.lphiebackend.Services;

import com.yamicode.lphiebackend.Models.Member;
import com.yamicode.lphiebackend.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(Long id, Member updatedMember){
     Member member = getMemberById(id);

     member.setFirstName(updatedMember.getFirstName());
     member.setLastName(updatedMember.getLastName());
     member.setRole(updatedMember.getRole());
     return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Member with id " + id + " does not exist")
        );
        }


    public boolean deleteMember(Long id) {

        if (!memberRepository.existsById(id)) {
            return false;
        }

        memberRepository.deleteById(id);

        return true;
    }

}
