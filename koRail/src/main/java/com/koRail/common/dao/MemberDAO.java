package com.koRail.common.dao;

import java.util.List;
import java.util.Map;

import com.koRail.common.to.MemberBean;

public interface MemberDAO {
	/**************************
	 * ID 중복확인
	 * @param stringMap
	 ***************************/
	public void selectMemberId(Map<String, String> stringMap);
	
	/*********************************
	 * 회원조회
	 * @param memberBean
	 * @return
	 *********************************/
	public List<MemberBean> selectMemberList(MemberBean memberBean);

	/*********************************
	 * 회원조회
	 * @param memberBean
	 * @return
	 *********************************/
	public MemberBean selectMember(MemberBean memberBean);

	/*********************************
	 * 회원 등록
	 * @param memberBean
	 *********************************/
	public void insertMember(MemberBean memberBean);
	
	/***********************************
	 * 만료되지 않은 승차권 예매 수
	 * @param id
	 * @return
	 ************************************/
	public int selectResveCount(String id);
	
	/***********************************
	 * 회원 수정
	 * @param memberBean
	 **********************************/
	public void updateMember(MemberBean memberBean);
	
	/***********************************
	 * 회원 삭제
	 * @param id
	 ***********************************/
	public void deleteMember(String id);
}