package com.yamicode.lphiebackend.Repository;

import com.yamicode.lphiebackend.Models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
