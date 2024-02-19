package com.bkjo.simplecommunity.reply;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/replies")
public class ReplyViewController {
	
	private final ReplyService replyService;
	
	@GetMapping("/modify-reply")
	public String postForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("reply", replyService.findReplyById(id));
        return "replyForm";
    }
	
}
