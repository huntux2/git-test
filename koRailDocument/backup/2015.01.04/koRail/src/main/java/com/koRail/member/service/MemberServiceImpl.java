package com.koRail.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.koRail.common.dao.DetailResveDAO;
import com.koRail.common.dao.MemberDAO;
import com.koRail.common.dao.RoomDAO;
import com.koRail.common.to.DetailResveBean;
import com.koRail.common.to.MemberBean;
import com.koRail.common.to.RoomBean;
import com.koRail.common.util.JSONParser;
import com.koRail.member.dao.ResveDAO;
import com.koRail.member.dao.SeatNoInfoDAO;
import com.koRail.member.to.ResveBean;
import com.koRail.member.to.TcktBean;

@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	/* 회원DAO */
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	/* 예약DAO */
	@Resource(name="resveDAO")
	private ResveDAO resveDAO;
	
	/* 상세예약DAO */
	@Resource(name="detailResveDAO")
	private DetailResveDAO detailResveDAO;
	
	/* 호실DAO */
	@Resource(name="roomDAO")
	private RoomDAO roomDAO;
	
	/* 좌석정보DAO */
	@Resource(name="seatNoInfoDAO")
	private SeatNoInfoDAO seatNoInfoDAO;
	
	/*****************************************
					회원 게인정보
	******************************************/
	
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
	
	/**********************************
	 * 회원가입, 개인정보수정, 회원탈퇴
	 * @param memberBean
	 **********************************/
	@Override
	public void setMember(MemberBean memberBean){
		if("insert".equals(memberBean.getState())){
			memberDAO.insertMember(memberBean);
		}else if("update".equals(memberBean.getState())){
			System.out.println("u");
		}else if("delete".equals(memberBean.getState())){
			System.out.println("d");
		}else{
			return;
		}
	}
	
	/*****************************************
						승차권
	******************************************/

	/***********************************
	 * 승차권 예매를 위한 운행일정 조회
	 * @param tcktBean
	 * @return
	 **********************************/
	@Override
	public List<TcktBean> getTcktList(TcktBean tcktBean){
		return resveDAO.selectTcktList(tcktBean);
	}

	/***********************************************************
	 * 예약을 위해 선택한 승차권에 대한 호실정보 조회 및 예약된 좌석정보 조회
	 * @param roomBean
	 * @return
	 *********************************************************/
	@Override
	public Map<String, ?> getTcktRoomInfoList(RoomBean roomBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("roomList", roomDAO.selectTcktRommList(roomBean));
		map.put("seatNoList", seatNoInfoDAO.selectSeatNoInfoBean(roomBean));
		
		return map;
	}

	/*****************************************
	 * 예약 등록, 삭제
	 * @param resveBean
	 * @param json
	 * @return
	 *****************************************/
	@Override
	public String setResve(ResveBean resveBean, String json) {
		String resveCode = null;
		
		if("insert".equals(resveBean.getState())){
			/* 예약이 등록되었다면 상세예약을 등록 */
			if(resveDAO.insertResve(resveBean) > 0){
				/* 상세예약 리스트 */
				@SuppressWarnings("unchecked")
				List<DetailResveBean> detailResvList = (List<DetailResveBean>) JSONParser.getInstance().processJSONToBean(json, "detailResveList", "com.koRail.common.to.DetailResveBean");
				
				/* 상세예약 등록 */
				for(DetailResveBean detailResveBean : detailResvList){
					detailResveDAO.insertDetailResve(detailResveBean);
					/* 예약코드 설정 */
					resveCode = detailResveBean.getCode();
				}
			}
		}else if("delete".equals(resveBean.getState())){
			System.out.println("d");
		}
		
		return resveCode;
	}
	
	/*****************************************
	 * 결제할 예매 정보 조회
	 * @param resveCode
	 * @return
	 ****************************************/
	@Override
	public ResveBean getResve(String resveCode){
		return resveDAO.selectResve(resveCode);
	}
}