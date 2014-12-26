package com.koRail.common.dao;

import java.util.List;

import com.koRail.common.to.DetailResveBean;

public interface DetailResveDAO {
	/**************************
	 * 상세예약 조회
	 * @param detailResveBean
	 * @return
	 **************************/
	public List<DetailResveBean> selectDetailResveList(DetailResveBean detailResveBean);
}
