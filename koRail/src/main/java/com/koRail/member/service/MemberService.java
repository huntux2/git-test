package com.koRail.member.service;

import java.util.Map;

import com.koRail.common.to.MemberBean;

public interface MemberService {
	/***************************
	 * ID 중복확인
	 * @param id
	 * @return
	 **************************/
	public Map<String, String> doIdCheck(String id);
	
	/***********************
	 * 회원 등록, 수정, 삭제
	 * @param memberBean
	 ***********************/
	public void setMember(MemberBean memberBean);
}
