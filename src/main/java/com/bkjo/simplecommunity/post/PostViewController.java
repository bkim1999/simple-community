package com.bkjo.simplecommunity.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkjo.simplecommunity.post.PostDto.PostResponse;
import com.bkjo.simplecommunity.reply.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostViewController {
	
	private final PostService postService;
	private final ReplyService replyService;
	
	@GetMapping("/new-post")
	public String postForm(@RequestParam(required = false, value = "id") Long id, Model model) {
        if (id == null) {
            model.addAttribute("post", new PostResponse());
        } else {
            model.addAttribute("post", postService.findPostById(id));
        }
        return "postForm";
    }
	
	@GetMapping("")
	public String postListView(Model model) {
		model.addAttribute("posts", postService.findAllPosts());
		return "postList";
	}
	
	@GetMapping("/{id}")
	public String postDetailView(@PathVariable("id") Long id, Model model) {
		model.addAttribute("post", postService.findPostById(id));
		model.addAttribute("replies", replyService.findAllRepliesOfPost(id));
		return "postDetail";
	}
	
}
