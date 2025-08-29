package com.winter.app.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data 부모와 자식 둘다 썼을때 오류가능성 있음
@Getter
@Setter
@ToString
@MappedSuperclass //안쓰면 JPA가 entitiy로 x
public class BoardVO {

	@Id //PK로 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment 해줌
	private Long boardNum;
	
	@Column(name = "boardTitle", nullable = true, unique = false, length = 255, insertable = true, updatable = true)
	private String boardTitle;
	private String boardWriter;
//	@Column(columnDefinition = "LONGTEXT") 혹은 @Lob 사용
	@Lob
	private String boardContents;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp //@UpdateTimeStamp 수정시간
	private LocalDateTime boardDate;
	
//	@Column(columnDefinition = "BIGINT DEFAULT 0", insertable = false)
	@Column
	@ColumnDefault(value="0")
	private Long boardHit;
	
	@Transient //테이블에 넣지않고 DB에 들어가지 않게 하겠다
	private String kind;
	
}
