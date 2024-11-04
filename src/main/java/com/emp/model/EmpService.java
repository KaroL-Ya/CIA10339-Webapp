package com.emp.model;

import java.sql.Date;
import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpJDBCDAO();
	} 

	public EmpVO addEmp( Integer postId ,Integer cafeId,Integer memId ,Date time
	, String title ,String  content, Integer count ,Byte status ) {

		EmpVO empVO = new EmpVO();
		empVO.setPostId(postId);
		empVO.setCafeId(cafeId);
		empVO.setMemId(memId);
		empVO.setTime(time);
		empVO.setTitle(title);
		empVO.setContent(content);
		empVO.setCount(count);
		empVO.setStatus(status);
		
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(Integer postId ,Integer cafeId,Integer memId ,Date time
			, String title ,String  content, Integer count ,Byte status) {

		EmpVO empVO = new EmpVO();

		empVO.setPostId(postId);
		empVO.setCafeId(cafeId);
		empVO.setMemId(memId);
		empVO.setTime(time);
		empVO.setTitle(title);
		empVO.setContent(content);
		empVO.setCount(count);
		empVO.setStatus(status);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public EmpVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
