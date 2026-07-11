package com.yamicode.lphiebackend.Controllers;

import com.yamicode.lphiebackend.Models.Member;
import com.yamicode.lphiebackend.Services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member){
        try {
            Member createdMember = memberService.createMember(member);
            return ResponseEntity.status(201).body(createdMember);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        return ResponseEntity.ok(memberService.getAllMembers());
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if(member == null) {
            return ResponseEntity.status(404).body("Member with id " + id + " not found");
        }
        return ResponseEntity.ok(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody Member member) {
        try {
            Member updatedMember = memberService.updateMember(id, member);
            if(updatedMember == null) {
                return ResponseEntity.status(404).body("Member with id " + id + " not found");
            }
            return ResponseEntity.ok(updatedMember);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        boolean deleted = memberService.deleteMember(id);
        if(!deleted){
            return ResponseEntity.status(404).body("Member with id " + id + " not found");
        }
        return ResponseEntity.ok("Member with id " + id + " is deleted");
    }
}
