package com.bkjo.simplecommunity.reply;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkjo.simplecommunity.post.Post;
import com.bkjo.simplecommunity.post.PostRepository;
import com.bkjo.simplecommunity.reply.ReplyDto.ReplyRequest;
import com.bkjo.simplecommunity.reply.ReplyDto.ReplyResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReplyService {
	
	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	@Transactional
	public ReplyResponse saveReply(Long id, ReplyRequest request) {
		
		Post repliedPost = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
		
		Reply savedReply = request.toEntity();
		savedReply.setPost(repliedPost);
		
		return new ReplyResponse(replyRepository.save(savedReply));
		
	}
	
	public List<ReplyResponse> findAllRepliesOfPost(Long id) {
		Post repliedPost = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
		return repliedPost.getReplies()
				.stream()
				.map(ReplyResponse::new)
				.toList();
	}
	
	public ReplyResponse findReplyById(Long id) {
		Reply reply = replyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
		return new ReplyResponse(reply);
	}
	
	@Transactional
	public void deleteReply(Long id) {
		Reply deletedReply = replyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
		replyRepository.delete(deletedReply);
	}

	@Transactional
	public ReplyResponse updateReply(Long id, ReplyRequest request) {
		Reply updatedReply = replyRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("id not found: " + id));
		updatedReply.update(request);
		return new ReplyResponse(updatedReply);
	}
	
	
}
