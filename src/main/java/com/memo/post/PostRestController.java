package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	/*
	 * 
	 * 
	 * 
	 * 
	 * */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		
		// 글쓴이 번호 - session에 있는 userId를 꺼낸다.]
		int userId = (int)session.getAttribute("userId");
		String userLogindId = (String)session.getAttribute("userLoginId");
		
		// DB  INSERT
		postBO.addPost(userId, userLogindId, subject, content, file);
		
		
		// RESPONSE VALUE
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("postId") int postId,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");

		// DB update
		postBO.updatePostById(userId, userLoginId, postId, subject, content, file);
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		// DB delete
		postBO.deletePostByPostIdUserId(postId, userId);
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
