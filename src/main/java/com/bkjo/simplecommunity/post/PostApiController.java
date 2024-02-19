package com.bkjo.simplecommunity.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkjo.simplecommunity.post.PostDto.PostRequest;
import com.bkjo.simplecommunity.post.PostDto.PostResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostApiController {
	
	private final PostService postService;
	
	@PostMapping("")
	public ResponseEntity<PostResponse> postAdd(@RequestBody PostRequest request) {
		PostResponse response = postService.savePost(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@GetMapping("")
	public ResponseEntity<List<PostResponse>> postList() {
		return ResponseEntity.ok()
				.body(postService.findAllPosts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> postSearchById(@PathVariable("id") Long id) {
		return ResponseEntity.ok()
				.body(postService.findPostById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PostResponse> postRemoveById(@PathVariable("id") Long id) {
		postService.deletePostById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> postModify(
			@PathVariable("id") Long id
		  , @RequestBody PostRequest request) {
		return ResponseEntity.ok()
				.body(postService.updatePost(id, request));
	}
	
	
	
}
