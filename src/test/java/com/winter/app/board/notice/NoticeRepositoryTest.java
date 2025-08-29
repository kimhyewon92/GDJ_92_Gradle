package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
//@Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	@Test
	void test() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title4");
		noticeVO.setBoardContents("Contents4");
		noticeVO.setBoardWriter("Writer4");
//		noticeRepository.save(noticeVO);
		
		List<NoticeFileVO> noticeList = new ArrayList<>();
		NoticeFileVO noticeFileVO = new NoticeFileVO();
//		noticeVO.setBoardNum(1L);
		
		noticeFileVO.setNoticeVO(noticeVO);
//		noticeFileVO.setBoardNum(10L);
		noticeFileVO.setOriName("ori");
		noticeFileVO.setSaveName("save");
	
		noticeList.add(noticeFileVO);
		noticeVO.setList(noticeList);
		
		noticeVO = noticeRepository.save(noticeVO);
		
		assertNotNull(noticeVO);
	}

	//@Test
	void test2() {
		Optional<NoticeVO> result = noticeRepository.findById(7L);
		NoticeVO noticeVO = result.get();
		log.info("{}", noticeVO.getBoardNum());
		log.info("{}", noticeVO.getList().getFirst().getSaveName());
		
		assertNotNull(noticeVO);
	}
	
	@Rollback(true)
	@Test
	void test3() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("boardNum").descending());
		
		List<NoticeVO> list = noticeRepository.findByBoardTitleLike("%%", pageable);
		
		list.forEach((e)->{
			log.info("{}", e.getBoardTitle()); 
		});
		
		log.info("{}", list.size());
		log.info("{}", list.getFirst().getBoardTitle());
		
	}
	
	
	
	
}
