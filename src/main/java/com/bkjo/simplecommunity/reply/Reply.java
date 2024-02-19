package com.bkjo.simplecommunity.reply;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bkjo.simplecommunity.common.Time;
import com.bkjo.simplecommunity.post.Post;
import com.bkjo.simplecommunity.reply.ReplyDto.ReplyRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "reply")
public class Reply extends Time{
	
	@Id @GeneratedValue
	@Column(name = "reply_id")
	private Long id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	@Builder
	public Reply(String content) {
		this.content = content;
	}
	
	public void update(ReplyRequest request) {
		this.content = request.getContent();
	}
	
	// 연관관계 메소드
	public void setPost(Post post) {
		if(this.post != null) {
			post.getReplies().remove(this);
		}
		this.post = post;
		post.getReplies().add(this);
	}
	
}
