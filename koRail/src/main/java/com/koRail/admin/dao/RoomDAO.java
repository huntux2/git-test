package com.koRail.admin.dao;

import com.koRail.admin.to.RoomBean;

public interface RoomDAO {
	/***************************
	 * 호실 등록
	 * @param roomBean
	 ***************************/
	public void insertRoom(RoomBean roomBean);

	/*****************************
	 * 호실 삭제
	 * @param roomCode
	 ****************************/
	public void deleteRoom(String roomCode);
}
