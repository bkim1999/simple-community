package com.bkjo.simplecommunity.reply;

import java.time.LocalDateTime;

import com.bkjo.simplecommunity.post.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReplyDto {

	@NoArgsConstructor
	@Getter
	public static class ReplyRequest {
		private String content;
		public Reply toEntity() {
			return Reply.builder()
					.content(content)
					.build();
		}
	}
	
	@NoArgsConstructor
	@Getter
	public static class ReplyResponse {
		private Long id;
		private String content;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		public ReplyResponse(Reply reply) {
			this.id = reply.getId();
			this.content = reply.getContent();
			this.createdAt = reply.getCreatedAt();
			this.updatedAt = reply.getUpdatedAt();
		}
	}
	
}
