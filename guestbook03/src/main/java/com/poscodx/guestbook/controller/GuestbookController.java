package com.poscodx.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.vo.GuestBookVo;


@Controller
public class GuestbookController {
	@Autowired
	private GuestbookRepository GuestbookRepository;
	
	@RequestMapping("/")
	public String main(Model model) {
		List<GuestBookVo> list = GuestbookRepository.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	@RequestMapping("/add")
	public String add(GuestBookVo vo) {
		GuestbookRepository.insert(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no,Model model) {
		model.addAttribute("no",no);
		return "delete";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no) {
		GuestbookRepository.deleteByNo(no);
		return "redirect:/";
	}

}
