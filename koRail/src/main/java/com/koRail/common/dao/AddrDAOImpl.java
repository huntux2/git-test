package com.koRail.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.koRail.common.to.AddrBean;

@Repository(value="addrDAO")
public class AddrDAOImpl implements AddrDAO {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;	
	
	/************************
	 * 주소검섹
	 * @param umd
	 * @return
	 *************************/
	@Override
	public List<AddrBean> selectAddrList(String umd){
		return (List<AddrBean>)sqlMapClientTemplate.queryForList("Addr.selectAddrList", umd);
	}
}
