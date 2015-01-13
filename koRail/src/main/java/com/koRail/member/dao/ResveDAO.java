package com.koRail.member.dao;

import com.koRail.member.to.ResveBean;

public interface ResveDAO {
	/********************************
	 * 예약 등록
	 * @param resveBean
	 * @return
	 ********************************/
	public int insertResve(ResveBean resveBean);
	
	/*********************************
	 * 결제할 예매 정보 조회
	 * @param resveCode
	 * @return
	 ********************************/
	public ResveBean selectResve(String resveCode);
}
