package com.koRail.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.koRail.member.to.ResveBean;

@SuppressWarnings({"deprecation"})
@Repository(value="resveDAO")
public class ResveDAOImpl implements ResveDAO {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	/********************************
	 * 예약 등록
	 * @param resveBean
	 * @return
	 ********************************/
	@Override
	public int insertResve(ResveBean resveBean){
		return sqlMapClientTemplate.update("Resve.insertResve", resveBean);
	}
	
	/*********************************
	 * 결제할 예매 정보 조회
	 * @param resveCode
	 * @return
	 ********************************/
	@Override
	public ResveBean selectResve(String resveCode){
		return (ResveBean) sqlMapClientTemplate.queryForObject("Resve.selectResve", resveCode);
	}
}
