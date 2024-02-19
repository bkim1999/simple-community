package com.bkjo.simplecommunity.reply;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkjo.simplecommunity.reply.ReplyDto.ReplyRequest;
import com.bkjo.simplecommunity.reply.ReplyDto.ReplyResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReplyApiController {
	
	private final ReplyService replyService;
	
	@PostMapping("/posts/{id}/replies")
	public ResponseEntity<ReplyResponse> replyAdd(@PathVariable("id") Long id, @RequestBody ReplyRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(replyService.saveReply(id, request));
	}
	
	@GetMapping("/posts/{id}/replies")
	public ResponseEntity<List<ReplyResponse>> replyListOfPost(@PathVariable("id") Long id) {
		return ResponseEntity.ok()
				.body(replyService.findAllRepliesOfPost(id));
	}
	
	@PutMapping("/replies/{id}")
	public ResponseEntity<ReplyResponse> replyModify(@PathVariable("id") Long id, @RequestBody ReplyRequest request) {
		return ResponseEntity.ok()
				.body(replyService.updateReply(id, request));
	}
	
}
