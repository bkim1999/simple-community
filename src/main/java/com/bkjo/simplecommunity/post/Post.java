package com.bkjo.simplecommunity.post;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bkjo.simplecommunity.common.Time;
import com.bkjo.simplecommunity.post.PostDto.PostRequest;
import com.bkjo.simplecommunity.reply.Reply;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends Time {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@OneToMany(mappedBy = "post", orphanRemoval = true)
	private List<Reply> replies;
	
	@Builder
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public void update(PostRequest request) {
		this.title = request.getTitle();
		this.content = request.getContent();
	}
	
	
}
