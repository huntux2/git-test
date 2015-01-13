package com.koRail.member.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.koRail.common.controller.CommonController;
import com.koRail.common.service.CommonService;
import com.koRail.common.to.CommonBean;
import com.koRail.common.to.DetailResveBean;
import com.koRail.common.to.MemberBean;
import com.koRail.common.to.RoomBean;
import com.koRail.member.service.MemberService;
import com.koRail.member.to.ResveBean;
import com.koRail.member.to.SetleBean;
import com.koRail.member.to.TcktBean;

@Controller(value="memberController")
@RequestMapping("/member/")
public class MemberController extends CommonController {
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	/****************************************
	 				회원/개인정보
	 ***************************************/
	
	/********************************
	 * 회원가입 화면
	 * @return
	 ********************************/
	@RequestMapping(value="memberAdd.html")
	public String findMemberAddForm(){
		return "member/memberAddForm";
	}
	
	/***************************
	 * id 중복확인
	 * @param model
	 * @param id
	 * @return
	 **************************/
	@RequestMapping(value="idCheck.do")
	public String doIdCheck(Model model, @RequestParam("id") String id){
		Map<String, String> stringMap = memberService.doIdCheck(id);
		
		model.addAttribute("rtCode", stringMap.get("rtCode"));
		model.addAttribute("rtMsg", stringMap.get("rtMsg"));
		
		return "jsonView";
	}
	
	/********************************
	 * 회원가입, 개인정보수정, 회원탈퇴
	 * @param memberBean
	 * @return
	 *******************************/
	@RequestMapping(value="memberProcess.do")
	public String processMember(@ModelAttribute MemberBean memberBean){
		memberService.setMember(memberBean);
		return "redirect:login.html";
	}
	
	@RequestMapping("tdyPint.do")
	public String findTdyPint(Model model, @RequestParam("id") String id){
		model.addAttribute("tdyPint", memberService.getTdyPint(id));
		return "jsonView";
	}
	
	/******************************************************
	 	 					승차권
	 ******************************************************/
	
	/***************************************
	 * 승차권 예매를 위한 운행일정 조회 화면
	 * @param model
	 * @param request
	 * @return
	 ***************************************/
	@RequestMapping(value="tcktSearch.html")
	public String findTcktSearchForm(Model model, HttpServletRequest request){
		/*메뉴*/
		super.getMenuTree(model, "tcktSearchForm");
		
		request.getSession().setAttribute("type2", "tckt");
		
		/*열차종류 조회를 위한 코드설정*/
		CommonBean commonBean = new CommonBean();
		commonBean.setSeCode("TRAIN");
		/*열차*/
		model.addAttribute("commonCodeList", commonService.getCommonCodeList(commonBean));
		return "/member/resve/tcktSearchForm";
	}
	
	/*********************************
	 * 승차권 예매를 위한 운행일정 조회
	 * @param model
	 * @param tcktBean
	 * @return
	 *********************************/
	@RequestMapping(value="tcktList.do")
	public String findTcktList(Model model, TcktBean tcktBean){
		List<TcktBean> tcktList = memberService.getTcktList(tcktBean);
		
		model.addAttribute("tcktList", tcktList);
		model.addAttribute("tcktListSize", tcktList.size());
		
		return "jsonView";
	}
	
	/**********************************************************
	 * 예약을 위해 선택한 승차권에 대한 호실정보 조회 및 예약된 좌석정보 조회
	 * @param model
	 * @param roomBean
	 * @return
	 **********************************************************/
	@RequestMapping(value="tcktRoomInfo.do")
	public String findTcktRoomInfo(Model model, @ModelAttribute RoomBean roomBean){
		Map<String, ?> map = memberService.getTcktRoomInfoList(roomBean);
		
		model.addAttribute("tcktRoomInfoList", map.get("roomList"));
		model.addAttribute("seatNoList", map.get("seatNoList"));
		
		return "jsonView";
	}
	
	/***************************************
	 * 예약 등록, 삭제
	 * @param model
	 * @param resveBean
	 * @param json
	 * @param redirectAttributes
	 * @return
	 ***************************************/
	@RequestMapping(value="processResve.do")
	public String doTest(Model model, @ModelAttribute ResveBean resveBean,
			@RequestParam(value="json") String json, RedirectAttributes redirectAttributes){
		if("insert".equals(resveBean.getState())){
			/* 
				* 예약 등록
			 	* Redirect post type parameter setting
			*/
			redirectAttributes.addFlashAttribute("resveCode", memberService.setResve(resveBean, json));
			
			return "redirect:resveAdd.html";
		}else{			
			return "json";
		}
	}
	
	/*********************************
	 * 결제할 예매 정보 조회
	 * @param model
	 * @param request
	 * @param resveCode
	 * @return
	 **********************************/
	@RequestMapping(value="resveAdd.html")
	public String findResveAddForm(Model model, HttpServletRequest request,
			@RequestParam(value="resveCode", required=false) String resveCode) {
		/* Redirect post type parameter */
		if(resveCode == null){
			Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
			resveCode = (String) flashMap.get("resveCode");
        }
		
		/*메뉴*/
		super.getMenuTree(model, "resveAddForm");
		
		/* 결제할 예매 정보 */
		model.addAttribute("resve", memberService.getResve(resveCode));
		/*예약코드*/
		model.addAttribute("resveCode", resveCode);
		
		return "/member/resve/resveAddForm";
	}
	
	/************************************
	 * 결제화면 이동
	 * @param model
	 * @param resveBean
	 * @param detailResveBean
	 * @return
	 ************************************/
	@RequestMapping(value="setle.html")
	public String findsetleForm(Model model, @ModelAttribute ResveBean resveBean,
			@ModelAttribute DetailResveBean detailResveBean){
		/*메뉴*/
		super.getMenuTree(model, "setleForm");
		
		/*카드종류 조회를 위한 코드설정*/
		resveBean.setSeCode("CARD_KND");
		/*카드종류*/
		model.addAttribute("commonCodeList", commonService.getCommonCodeList(resveBean));
		
		/*예약코드*/
		model.addAttribute("resveCode", resveBean.getResveCode());
		/*총 운임금액*/
		model.addAttribute("allFrAmount", resveBean.getAllFrAmount());
		/*총 할인금액*/
		model.addAttribute("allDscntAmount", resveBean.getAllDscntAmount());
		/*총 영수금액*/
		model.addAttribute("allRcptAmount", resveBean.getAllRcptAmount());
		
		return "/member/resve/setleForm";
	}
	
	/********************************
	 * 결제, 결제취소
	 * @param setleBean
	 * @return
	 *********************************/
	@RequestMapping(value="setleProcess.do")
	public String processSetle(RedirectAttributes redirectAttributes,
			@ModelAttribute SetleBean setleBean){
		if("insert".equals(setleBean.getState())){
			/*결제*/
			memberService.setSetle(setleBean);
			/* Redirect post type parameter setting */
			redirectAttributes.addFlashAttribute("resveCode", setleBean.getResveCode());
		}
		
		return "redirect:/member/setleSuccess.html";
	}
	
	/*************************************
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="setleSuccess.html")
	public String findSetleSuccessForm(Model model, HttpServletRequest request){
		/* Redirect post type parameter */
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		/*메뉴*/
		super.getMenuTree(model, "setleSuccessForm");
		
		/*조회*/
		model.addAttribute("tcktInfo", memberService.getDetailTcktRcrdList((String)flashMap.get("resveCode")));
		
		/*요청화면 : setleSuccess.html*/
		model.addAttribute("requestForm", request.getRequestURI().split("/")[2]);
		
		return "/member/rcrd/detailResveRcrdForm";
	}
	
	@RequestMapping(value="tcktRcrd.html")
	public String findTcktRcrdForm(Model model){
		return "/member/rcrd/tcktRcrdForom";
	}
}
