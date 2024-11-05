package com.post.model;

import java.sql.Date;
import java.util.List;

public class PostService {

	private PostDAO_interface dao;

	public PostService() {
		dao = new PostJDBCDAO();
	} 

	public PostVO addPost( Integer postId ,Integer cafeId,Integer memId ,Date time
	, String title ,String  content, Integer count ,Byte status ) {

		PostVO postVO = new PostVO();
		postVO.setPostId(postId);
		postVO.setCafeId(cafeId);
		postVO.setMemId(memId);
		postVO.setTime(time);
		postVO.setTitle(title);
		postVO.setContent(content);
		postVO.setCount(count);
		postVO.setStatus(status);
		
		dao.insert(postVO);

		return postVO;
	}

	public PostVO updatePost(Integer postId ,Integer cafeId,Integer memId ,Date time
			, String title ,String  content, Integer count ,Byte status) {

		PostVO postVO = new PostVO();

		postVO.setPostId(postId);
		postVO.setCafeId(cafeId);
		postVO.setMemId(memId);
		postVO.setTime(time);
		postVO.setTitle(title);
		postVO.setContent(content);
		postVO.setCount(count);
		postVO.setStatus(status);
		dao.update(postVO);

		return postVO;
	}

	public void deletePost(Integer postId) {
		dao.delete(postId);
	}

	public PostVO getOnePost(Integer postId) {
		return dao.findByPrimaryKey(postId);
	}

	public List<PostVO> getAll() {
		return dao.getAll();
	}
}
