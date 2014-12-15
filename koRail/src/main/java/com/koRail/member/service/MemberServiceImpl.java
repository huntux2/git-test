package com.koRail.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.koRail.common.dao.MemberDAO;
import com.koRail.common.to.MemberBean;

@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	/***************************
	 * ID 중복확인
	 * @param id
	 * @return
	 **************************/
	public Map<String, String> doIdCheck(String id){
		Map<String, String> stringMap = new HashMap<String, String>();
		
		/* Map 설정 */
		stringMap.put("id", id);
		stringMap.put("rtId", null);
		stringMap.put("rtCode", null);
		stringMap.put("rtMsg", null);
		
		memberDAO.selectMemberId(stringMap);
		
		return stringMap;
	}
	
	/***********************
	 * 회원 등록, 수정, 삭제
	 * @param memberBean
	 ***********************/
	@Override
	public void setMember(MemberBean memberBean){
		System.out.println("S: servie");
	}
}
