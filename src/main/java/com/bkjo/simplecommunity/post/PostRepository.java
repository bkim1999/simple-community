package com.bkjo.simplecommunity.post;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkjo.simplecommunity.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
