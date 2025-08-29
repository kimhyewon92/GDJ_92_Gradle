package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void test() {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("user");
		memberVO.setPassword("pw");
		memberVO.setName("name");
		memberVO.setEmail("user@gmail.com");
		memberVO.setBirth(LocalDate.now());
		
		List<MemberRoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleNum(2L);
		
		MemberRoleVO memberRoleVO = new MemberRoleVO();
		memberRoleVO.setMemberVO(memberVO);
		memberRoleVO.setRoleVO(roleVO);
		
		list.add(memberRoleVO);
		
		memberVO.setList(list);
		memberRepository.save(memberVO);
		
	}

}
