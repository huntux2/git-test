package com.koRail.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree {
	private static MenuTree menuTree = null;
	
	public static MenuTree getInstance(){
		if(menuTree == null){
			menuTree = new MenuTree();
			return menuTree;
		}else{
			return menuTree;
		}
	}
	
	/*************************************
	 * 메뉴명 설정
	 * @return
	 ***********************************/
	public Map<String, String> setMenu(){
		Map<String, String> menuMap = new HashMap<String, String>();
		
		menuMap.put("home", "HOME");
			menuMap.put("tic", "승차권");
				menuMap.put("tictResve1", "승차권 예약");
					menuMap.put("tictSelect", "승차권 조회");
					menuMap.put("tictResve2", "승차권 예매");
					menuMap.put("setle", "결제");
					menuMap.put("tictt", "발권");
				menuMap.put("ticState", "승차권 현황");
					menuMap.put("dfTic", "승차권 상세");
			menuMap.put("admin", "관리자");
				menuMap.put("state", "현황");
					menuMap.put("ticttState", "승차권 발권 현황");
					menuMap.put("triState", "열차별 승객 현황");
				menuMap.put("mng", "관리");
					menuMap.put("sttMng", "역 관리");
					menuMap.put("triMng", "열차 관리");
					menuMap.put("opratMng", "운행일정 관리");
						menuMap.put("opratAdd", "운행일정 등록");
						menuMap.put("opratUpdate", "운행일정 수정");
					menuMap.put("memberMng", "회원 관리");
			menuMap.put("myInfo", "내 정보");
				menuMap.put("myInfoMng", "개인정보 관리");
					menuMap.put("myInfoUpdate", "개인정보 수정");
				menuMap.put("hstr", "이용 내역");
		
		menuMap.put("memberAdd", "회원 가입");
		
		return menuMap;
	}
	
	/*****************************************
	 * 화면먕에 해당하는 메뉴 return
	 * @param formName
	 * @return
	 ****************************************/
	public List<String> getMenu(String formName){
		Map<String, String> menu = this.setMenu();
		
		List<String> list = new ArrayList<String>();
		
		list.add(menu.get("home"));
		
		switch (formName) {
			/*역 관리*/
			case "statnMngForm":
				list.add(menu.get("mng"));
				list.add(menu.get("sttMng"));
				break;
			case "trainMngForm":
				list.add(menu.get("mng"));
				list.add(menu.get("triMng"));
				break;
			case "opratMngForm":
				list.add(menu.get("mng"));
				list.add(menu.get("opratMng"));
				break;
			case "opratAddForm":
				list.add(menu.get("mng"));
				list.add(menu.get("opratMng"));
				list.add(menu.get("opratAdd"));
				break;
			case "opratUpdateForm":
				list.add(menu.get("mng"));
				list.add(menu.get("opratMng"));
				list.add(menu.get("opratUpdate"));
				break;
			case "memberMngForm":
				list.add(menu.get("mng"));
				list.add(menu.get("memberMng"));
				break;
		}
		
		return list;
	}
}
