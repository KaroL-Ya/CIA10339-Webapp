package com.post.model;

import java.util.*;

public interface PostDAO_interface {
          public void insert(PostVO postVO);
          public void update(PostVO postVO);
          public void delete(Integer postId);
          public PostVO findByPrimaryKey(Integer postId);
          public List<PostVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
