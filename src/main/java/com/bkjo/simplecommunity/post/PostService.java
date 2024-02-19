package com.bkjo.simplecommunity.post;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bkjo.simplecommunity.post.PostDto.PostRequest;
import com.bkjo.simplecommunity.post.PostDto.PostResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {
	
	private final PostRepository postRepository;
	
	@Transactional
	public PostResponse savePost(PostRequest request) {
		Post savedPost = postRepository.save(request.toEntity());
		return new PostResponse(savedPost);
	}
	
	public List<PostResponse> findAllPosts() {
		return postRepository.findAll()
				.stream()
				.map(PostResponse::new)
				.toList();
	}
	
	public PostResponse findPostById(Long id) {
		Post foundPost = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("post not found: " + id));
		return new PostResponse(foundPost);
	}
	
	@Transactional
	public void deletePostById(Long id) {
		Post foundPost = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("post not found: " + id));
		postRepository.delete(foundPost);
	}
	
	@Transactional
	public PostResponse updatePost(Long id, PostRequest request) {
		Post foundPost = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("post not found: " + id));
		foundPost.update(request);
		return new PostResponse(foundPost);
	}
	
}
