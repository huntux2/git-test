package com.koRail.common.to;

/* 상세예약 */
public class DetailResveBean {
	private String detailResveCode;	/* 상세예약코드 */
	private String resveCode; 		/* 예약코드 */
	private String psngrnm; 		/* 승객명 */
	private String roomKndCode; 	/* 객실유형코드 */
	private String roomKndValue; 	/* 객실유형코드 값 */
	private String seatNo; 			/* 좌석번호 */
	private String psngrKndCode; 	/* 승객유형코드 */
	private String psngrKndValue; 	/* 승객유형코드 값 */
	private String dspsnGrad; 		/* 장애인등급 */
	private String room; 			/* 호실 */
	private String frAmount; 		/* 운임금액 */
	private String dscntAmount; 	/* 할인금액 */
	private String rcptAmount; 		/* 영수금액 */
	
	public String getDetailResveCode() {
		return detailResveCode;
	}
	public void setDetailResveCode(String detailResveCode) {
		this.detailResveCode = detailResveCode;
	}
	public String getResveCode() {
		return resveCode;
	}
	public void setResveCode(String resveCode) {
		this.resveCode = resveCode;
	}
	public String getPsngrnm() {
		return psngrnm;
	}
	public void setPsngrnm(String psngrnm) {
		this.psngrnm = psngrnm;
	}
	public String getRoomKndCode() {
		return roomKndCode;
	}
	public void setRoomKndCode(String roomKndCode) {
		this.roomKndCode = roomKndCode;
	}
	public String getRoomKndValue() {
		return roomKndValue;
	}
	public void setRoomKndValue(String roomKndValue) {
		this.roomKndValue = roomKndValue;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getPsngrKndCode() {
		return psngrKndCode;
	}
	public void setPsngrKndCode(String psngrKndCode) {
		this.psngrKndCode = psngrKndCode;
	}
	public String getPsngrKndValue() {
		return psngrKndValue;
	}
	public void setPsngrKndValue(String psngrKndValue) {
		this.psngrKndValue = psngrKndValue;
	}
	public String getDspsnGrad() {
		return dspsnGrad;
	}
	public void setDspsnGrad(String dspsnGrad) {
		this.dspsnGrad = dspsnGrad;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getFrAmount() {
		return frAmount;
	}
	public void setFrAmount(String frAmount) {
		this.frAmount = frAmount;
	}
	public String getDscntAmount() {
		return dscntAmount;
	}
	public void setDscntAmount(String dscntAmount) {
		this.dscntAmount = dscntAmount;
	}
	public String getRcptAmount() {
		return rcptAmount;
	}
	public void setRcptAmount(String rcptAmount) {
		this.rcptAmount = rcptAmount;
	}
}
