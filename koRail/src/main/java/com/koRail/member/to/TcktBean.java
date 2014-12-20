package com.koRail.member.to;

import com.koRail.common.to.CommonBean;

/* 승치권 조회 */
public class TcktBean extends CommonBean {
	private String trainNo; 		/* 열차코드 */
	private String trainKndCode; 	/* 열차종류코드 */
	private String trainKndValue; 	/* 열차종류값 */
	private String startStatnCode; 	/* 출발역코드 */
	private String startStatnValue; /* 출발역값 */
	private String startTm;			/* 출발시각 */
	private String arvlStatnCode; 	/* 도착역코드 */
	private String arvlStatnValue; 	/* 도착역값 */
	private String arvlTm; 			/* 도착시각 */
	private String seatCo;			/* 좌석수 */
	private String prtclrRoomY;		/* 특실 좌석수 */
	private String prtclrRoomN;		/* 일반실 좌석수 */
	private String fare; 			/* 요금 */
	
	public String getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getTrainKndCode() {
		return trainKndCode;
	}
	public void setTrainKndCode(String trainKndCode) {
		this.trainKndCode = trainKndCode;
	}
	public String getTrainKndValue() {
		return trainKndValue;
	}
	public void setTrainKndValue(String trainKndValue) {
		this.trainKndValue = trainKndValue;
	}
	public String getStartStatnCode() {
		return startStatnCode;
	}
	public void setStartStatnCode(String startStatnCode) {
		this.startStatnCode = startStatnCode;
	}
	public String getStartStatnValue() {
		return startStatnValue;
	}
	public void setStartStatnValue(String startStatnValue) {
		this.startStatnValue = startStatnValue;
	}
	public String getStartTm() {
		return startTm;
	}
	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}
	public String getArvlStatnCode() {
		return arvlStatnCode;
	}
	public void setArvlStatnCode(String arvlStatnCode) {
		this.arvlStatnCode = arvlStatnCode;
	}
	public String getArvlStatnValue() {
		return arvlStatnValue;
	}
	public void setArvlStatnValue(String arvlStatnValue) {
		this.arvlStatnValue = arvlStatnValue;
	}
	public String getArvlTm() {
		return arvlTm;
	}
	public void setArvlTm(String arvlTm) {
		this.arvlTm = arvlTm;
	}
	public String getPrtclrRoomY() {
		return prtclrRoomY;
	}
	public void setPrtclrRoomY(String prtclrRoomY) {
		this.prtclrRoomY = prtclrRoomY;
	}
	public String getPrtclrRoomN() {
		return prtclrRoomN;
	}
	public void setPrtclrRoomN(String prtclrRoomN) {
		this.prtclrRoomN = prtclrRoomN;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getSeatCo() {
		return seatCo;
	}
	public void setSeatCo(String seatCo) {
		this.seatCo = seatCo;
	}
}