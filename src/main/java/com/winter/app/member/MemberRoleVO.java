package com.winter.app.member;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="member_role")
//@IdClass(MemberRolePK.class)
public class MemberRoleVO {
	
//	@Id
//	private String username;
//	@Id
//	private Long roleNum;
	
//	@EmbeddedId
//	private MemberRolePK memberRolePK;
	
	@ManyToOne
	@JoinColumn(name="username", insertable = false, updatable = false)
	
	//또는 MemberRolePK생성 embeddable
	@Id
	@MapsId("username")
	private MemberVO memberVO;
	
	@ManyToOne
	@JoinColumn(name="roleNum", insertable = false, updatable = false)
	@Id
	private RoleVO roleVO;
	
}
