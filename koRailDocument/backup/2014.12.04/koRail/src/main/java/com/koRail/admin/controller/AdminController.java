package com.koRail.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koRail.admin.service.AdminServie;
import com.koRail.admin.to.OpratBean;
import com.koRail.admin.to.StatnBean;
import com.koRail.admin.to.TrainBean;
import com.koRail.common.controller.CommonController;
import com.koRail.common.exception.DataDeleteException;
import com.koRail.common.service.CommonService;
import com.koRail.common.to.CommonBean;

@Controller(value="adminController")
@RequestMapping(value="/admin/")
public class AdminController extends CommonController {
		
	@Resource(name="commonService")
	private CommonService commonService;

	@Resource(name="adminServie")
	private AdminServie adminServie;
	
	
	/**********************************************
	 						역 관리
	***********************************************/
	
	/************************************
	 * 역 관리 화면
	 * @param request
	 * @param model
	 * @param commonBean
	 * @return
	 ************************************/
	@RequestMapping(value="statnMng.html")
	public String findStationMngForm(HttpServletRequest request, Model model,
			@ModelAttribute CommonBean commonBean){
		/*레이아웃 변경*/
		super.setLayout(request, "stp");
		
		/*메뉴*/
		super.getMenuTree(model, "statnMngForm");
		
		/*지역조회를 위한 코드설정*/
		commonBean.setSeCode("AREA");
		/*지역*/
		model.addAttribute("commonCodeList", commonService.getCommonCodeList(commonBean));
		
		return "admin/statn/statnMngForm";
	}
	
	/*********************************************
	 * 역 조회
	 * @param model
	 * @param commonBean
	 * @return
	 *********************************************/
	@RequestMapping(value="statnList.do")
	public String findStatnList(Model model, @ModelAttribute CommonBean commonBean){
		/*역 리스트*/
		List<StatnBean> statnList = adminServie.getStatnList(commonBean);
		
		/*리스트 size*/
		model.addAttribute("statnListSize", statnList.size());
		
		/*리스트*/
		model.addAttribute("statnList", statnList);
		
		return "jsonView";
	}
	
	/******************************************
	 * 역 등록, 수정, 삭제
	 * @param model
	 * @param statnBean
	 * @param deleteCodeArray
	 * @return
	 ******************************************/
	@RequestMapping(value="statnProcess.do")
	public String processStatn(Model model, @ModelAttribute StatnBean statnBean,
			@RequestParam(value="deleteCodeArray", required=false) String[] deleteCodeArray){
		try{
			adminServie.setStatn(statnBean, deleteCodeArray);
			model.addAttribute("errorCode", 0);
			model.addAttribute("errorMsg", null);
			
			/* 검색한 방법 */
			if(statnBean.getSrcText() == null){
				model.addAttribute("searchMode", "area");				
			}else{
				model.addAttribute("searchMode", "statnNm");
			}
		}catch(DataDeleteException e){
			model.addAttribute("errorCode", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
		
		return "jsonView";
	}
	
	/**********************************************
					열차 관리
	***********************************************/
	
	/****************************************
	 * 열차 관리 화면
	 * @param request
	 * @param model
	 * @param commonBean
	 * @return
	 **************************************/
	@RequestMapping(value="trainMng.html")
	public String findTrainMngForm(HttpServletRequest request, Model model,
			@ModelAttribute CommonBean commonBean){
		/*메뉴*/
		super.getMenuTree(model, "trainMngForm");
	
		/*열차종류 조회를 위한 코드설정*/
		commonBean.setSeCode("TRAIN");
		/*열차종류*/
		model.addAttribute("commonCodeList", commonService.getCommonCodeList(commonBean));
	
		return "admin/train/trainMngForm";
	}
	
	/***************************************
	 * 열차 조회
	 * @param model
	 * @param commonBean
	 * @return
	 ***************************************/
	@RequestMapping(value="trainList.do")
	public String findTrainList(Model model, @ModelAttribute CommonBean commonBean){
		/*열차 리스트*/
		List<TrainBean> trainList = adminServie.getTrainList(commonBean);
		
		/*리스트 size*/
		model.addAttribute("trainListSize", trainList.size());
		
		/*리스트*/
		model.addAttribute("trainList", trainList);
		
		return "jsonView";
	}
	
	/**************************************
	 * 열차 등록, 수정, 삭제
	 * @param model
	 * @param trainBean
	 * @param deleteCodeArray
	 * @return
	 *************************************/
	@RequestMapping(value="trainProcess.do")
	public String processTrain(Model model, @ModelAttribute TrainBean trainBean,
			@RequestParam(value="deleteCodeArray", required=false) String[] deleteCodeArray){
		try{
			adminServie.setTrain(trainBean, deleteCodeArray);
			model.addAttribute("errorCode", 0);
			model.addAttribute("errorMsg", null);
			
			/* 검색한 방법 */
			if(trainBean.getSrcText() == null){
				model.addAttribute("searchMode", "knd");				
			}else{
				model.addAttribute("searchMode", "trainNo");
			}
		}catch(DataDeleteException e){
			model.addAttribute("errorCode", 1);
			model.addAttribute("errorMsg", e.getMessage());
		}
		
		return "jsonView";
	}
	
	/**********************************************
						운행일정 관리
	***********************************************/
	
	/****************************************
	* 운행일정 관리 화면
	* @param request
	* @param model
	* @param commonBean
	* @return
	**************************************/
	@RequestMapping(value="opratMng.html")
	public String findOpratMngForm(HttpServletRequest request, Model model,
			@ModelAttribute CommonBean commonBean){
		/*메뉴*/
		super.getMenuTree(model, "opratMngForm");
	
		/*열차종류 조회를 위한 코드설정*/
		commonBean.setSeCode("TRAIN");
		/*지역*/
		model.addAttribute("commonCodeList", commonService.getCommonCodeList(commonBean));
	
		return "admin/oprat/opratMngForm";
	}
	
	/***************************************
	* 운행일정, 상세운행일정, 호실 조회
	* @param model
	* @param commonBean
	* @return
	***************************************/
	@RequestMapping(value="opratList.do")
	public String findOpratList(Model model, @ModelAttribute CommonBean commonBean){
		/*운행일정 리스트*/
		List<OpratBean> opratList = adminServie.getOpratList(commonBean);
	
		/*리스트 size*/
		model.addAttribute("opratListSize", opratList.size());
	
		/*리스트*/
		model.addAttribute("opratList", opratList);
	
		return "jsonView";
	}
	
	/*****************************
	 * 운행일정 등록 화면
	 * @param model
	 * @return
	 * @throws Exception 
	 ****************************/
	@RequestMapping(value="opratAdd.html")
	public String findOpratAddForm(Model model) {
		/*메뉴*/
		super.getMenuTree(model, "opratAddForm");
		
		return "admin/oprat/opratAddForm";
	}
	
	/******************************************
	 * 상세운행 수정화면
	 * @param model
	 * @param commonBean
	 * @return
	 ******************************************/
	@RequestMapping(value="opratUpdate.html")
	public String findOpratUpdateForm(Model model, @ModelAttribute CommonBean commonBean){
		/*메뉴*/
		super.getMenuTree(model, "opratUpdateForm");
		
		/*검색유형을 update로 설정*/
		commonBean.setFormType("update");
		/*운행일정 리스트*/
		List<OpratBean> opratList = adminServie.getOpratList(commonBean);
		
		model.addAttribute("oprat", opratList.get(0));
		model.addAttribute("detailOpratList", opratList.get(0).getDetailOpratList());
		model.addAttribute("roomList", opratList.get(0).getRoomList());
		
		return "admin/oprat/opratUpdateForm";
	}
	
	/******************************************
	 * 운행일정 등록, 수정, 삭제
	 * 상세운행 등록, 삭제
	 * 호실	 등록,삭제
	 * @param model
	 * @param opratBean
	 * @param json
	 * @param deleteCodeArray
	 * @return
	 ******************************************/
	@RequestMapping(value="opratProcess.do")
	public String processOprat(Model model, @ModelAttribute OpratBean opratBean,
			@RequestParam(value="json", required=false ) String[] json,
			@RequestParam(value="deleteCodeArray", required=false) String[] deleteCodeArray){
		try{	
			adminServie.setOprat(opratBean, json, deleteCodeArray);
			return "redirect:opratMng.html";
		}catch(DataDeleteException e){
			model.addAttribute("errorMsg", e.getMessage());
			return "jsonView";
		}
	}
	
	@RequestMapping(value="memberMng.html")
	public String findMemberMngForm(){
		return "admin/member/memberMngForm";
	}
}