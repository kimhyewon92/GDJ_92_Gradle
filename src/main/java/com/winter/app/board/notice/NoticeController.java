package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice/**")
@CrossOrigin
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("")
	public Page<NoticeVO> list(@PageableDefault(size=5, sort="boardNum", direction = Sort.Direction.DESC) Pageable pageable) throws Exception{
		return noticeService.list(pageable);
	}
	
	@GetMapping("{boardNum}")
	public NoticeVO detail(@PathVariable("boardNum") Long boardNum) throws Exception{
		NoticeVO noticeVO = noticeService.detail(boardNum);
		System.out.println(noticeVO.getList().get(0).getSaveName());
		return noticeVO;
	}
	
	
	
}
