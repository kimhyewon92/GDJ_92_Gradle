package com.winter.app.board.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "noticefiles")
public class NoticeFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileNum;
	
	private String saveName;
	
	private String oriName;
//	private Long boardNum;

	@ManyToOne
	@JoinColumn(name = "boardNum")
	@JsonIgnore //JSON 직렬화 할 때 재외
	private NoticeVO noticeVO;
	
	
}
