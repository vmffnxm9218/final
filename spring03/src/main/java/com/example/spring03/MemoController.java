package com.example.spring03;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MemoController {
	@Inject
	MemoDao dao;
	
	@RequestMapping("/") //시작페이지
	public ModelAndView list(ModelAndView mav) {
		List<MemoDto> items=dao.list();
		mav.setViewName("list");//페이지 url 지정, views/list.jsp
		mav.addObject("list", items);//변수명 list, 값 items
		return mav;
	}
	@RequestMapping("insert.do")
	public String insert(MemoDto dto) {
		dao.insert(dto.getWriter(), dto.getMemo());
		return "redirect:/";
	}
	@RequestMapping("view/{idx}")
	public ModelAndView view(@PathVariable int idx, ModelAndView mav) {
		mav.setViewName("view");
		mav.addObject("dto", dao.view(idx));
		return mav;
	}
	@RequestMapping("update/{idx}")
	public String update(@PathVariable int idx, MemoDto dto) {
		dao.update(dto);
		return "redirect:/";
	}
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx) {
		dao.delete(idx);
		return "redirect:/";
	}
}
