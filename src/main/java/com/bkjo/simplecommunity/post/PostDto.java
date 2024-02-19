package com.bkjo.simplecommunity.post;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {
	
	@NoArgsConstructor
	@Getter
	public static class PostRequest {
		
		private String title;
		private String content;
		
		public Post toEntity() {
			return Post.builder()
					.title(title)
					.content(content)
					.build();
		}
		
	}
	
	@NoArgsConstructor
	@Getter
	public static class PostResponse {
		private Long id;
		private String title;
		private String content;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		
		public PostResponse(Post post) {
			this.id = post.getId();
			this.title = post.getTitle();
			this.content = post.getContent();
			this.createdAt = post.getCreatedAt();
			this.updatedAt = post.getUpdatedAt();
		}
	}
	
}
