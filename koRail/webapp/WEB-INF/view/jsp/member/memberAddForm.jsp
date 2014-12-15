<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
	<head>
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
			/* ID 중복확인 */
			function doIdCheck(){
				var id = $("#id").val().replace(" ");
				
				if(id == ""){
					alert("아이디를 입력하셔야 합니다.");
				}else{
					$.ajax({
						type:"POST",
						url: "/member/idCheck.do?id="+id,
						Type:"JSON",
						success : function(data) {
							if(data.rtCode == 0){
								$("#idCheckMsg").removeClass("false-text");
								$("#idCheckMsg").addClass("true-text");
							}else{
								$("#idCheckMsg").removeClass("true-text");
								$("#idCheckMsg").addClass("false-text");
							}
							
							$("#idCheckMsg").html(data.rtMsg);
						}
					});	/* $.ajax */
				} /* else end */
			}
			
			/* ID 중복확인 해제 */
			function doIdCheckInit(){
				$("#idCheckMsg").html("");
			}
			
			/* 비밀번호 확인 */
			function doPasswordCheck(obj){
				/* 이벤트가 발생한 tag가 속한 td < .msg */
				var chTag = $(obj).parent().children(".msg");
				
				 chTag.html("");
				
				if($("#password").val() != ""){
					if($("#password").val() == $(obj).val()){
						chTag.removeClass("false-text");
						chTag.addClass("true-text");
						chTag.html("비밀번호가 일치합니다.");
					}else{
						if($(obj).val().length > 0){
							chTag.removeClass("true-text");
							chTag.addClass("false-text");
							chTag.html("비밀번호가 일치하지 않습니다.");
						}else{
							chTag.html("");
						}
					}	
				}
			}
			
			/* 전화번호 형식 확인 */
			function doNumberCheck(obj){
				/*전화번효, 휴대전화번호 형식*/
				var telText = /\d{2,3}-\d{3,4}-\d{4}/;
				/* 숫자형식 */
				var numberText = /\d+/;
				/* 이벤트가 발생한 객채 */
				var text = $(obj);
				/* 이벤트가 발생한 tag가 속한 td < .msg */
				var chTag = $(obj).parent().children(".msg");
				
				if(text.val() == ""){
					chTag.html("");
				}else{
					if(numberText.test(text.val())){
						/* 전화번호의 값 */
						var tel1 = $(text.parent().children("input").get(0)).val();
						var tel2 = $(text.parent().children("input").get(1)).val();
						var tel3 = $(text.parent().children("input").get(2)).val();
						
						if(tel1 && tel2 && tel3 != ""){
							if(telText.test(tel1+"-"+tel2+"-"+tel3)){
								chTag.removeClass("false-text");
								chTag.addClass("true-text");
								chTag.html("옭바른 전화번호 형식 입니다.");
							}else{
								chTag.removeClass("true-text");
								chTag.addClass("false-text");
								chTag.html("형식 ex):010-2228-3324 또는 ex):02-247-8873");
							}
						}
					}else{
						chTag.removeClass("true-text");
						chTag.addClass("false-text");
						chTag.html("숫자만 입력가능 합니다.");
					}
				}
			}
			
			/* 이메일 형식 확인 */
			function doEmailCheck(obj){
				/* 이메일 형식 */
				var emailText = /^[0-9A-Z]([-_\.]?[0-9A-Z])*@[0-9A-Z]([-_\.]?[0-9A-Z])*\.[A-Z]{2,6}$/i;
				/* 이벤트가 발생한 tag가 속한 td < .msg */
				var chTag = $(obj).parent().children(".msg");
				
				var email1 = $("#email1").val();
				var email2 = $("#email2").val();
				var hEmail = $("input[name=email]");
				
				if(email1 == "" || email2 == ""){
					chTag.html("");
				}else{
					if((email1.length+email2.length) == 0){
						chTag.html("");
					}else{
						if(email1 && email2 == ""){
							chTag.html("");
						}else{
							/*서버로 전달할 값 설정*/
							hEmail.val(email1+"@"+email2);
							
							if(emailText.test(hEmail.val())){
								chTag.removeClass("false-text");
								chTag.addClass("true-text");
								chTag.html("옭바른 이메일 형식 입니다.");
							}else{
								chTag.removeClass("true-text");
								chTag.addClass("false-text");
								chTag.html("옭바른 이메일 형식이 아닙니다.");
							}
						} /* else end */
					} /* else end */	
				} /* else end */
			} /* doEmailCheck end */
			
			/*주소검색 다이알로그*/
			function findAddrList(){
				/* 초기화 */
	   			$("#grid").html("<table id='gridBody'></table><div id='footer'><div>");
	   			
   				$("#gridBody").jqGrid({
   					datatype: "LOCAL",
	   				caption:"주소정보",
	   				width: 380,
	   				height: 160,
	   				scroll: 1,
	   				rowNum : 'max',
	   				pager: '#footer',
	   				colNames:["우편번호", "주소"],
	          		colModel : [
						/* { name : "zipCode", align:"center", width: 30, height : 200, sortable:false,
							cellattr: function(rowId, value, rowObject, colModel, arrData) {
								return ' colspan=2';
							}
						}, */
						{ name : "zipCode", width: 30, align:"center", sortable:false},
						{ name : "addr", width: 70, align:"center", sortable:false}
					]
				}); /*jqGrid end*/
				
   				/*초기화면 메세지를 출력하기 위해 그리드 행 추가 및 메세지 설정*/
				$("#gridBody").jqGrid('addRowData', 0, {zipCode:"257-386", addr:"경상남도 함안군 칠서면 무릉리 에이스아파트"});
				//$("#gridBody").jqGrid('addRowData', 0, {zipCode:"회원을 검색해야 합니다."});
				
				/*다이알로그*/
   				$("#addrDialog").dialog({
   					modal: true,
   					width: 450,
					buttons: {
						"확인" : function(){
							
						},
						"취소" : function(){
							$(this).dialog("close");
						}
					}
   				});
			}
		</script>
	</head>
	<body>
		<form action="/member/memberProcess.do" method="POST">
			<table class="d-table" style="text-align: left;">
				<colgroup>
					<col width="20%">
					<col width="*%">
				</colgroup>
				<tbody>
					<tr>
						<td>아이디</td>
						<td>
							<input id="id" name="id" type="text" onkeypress="doIdCheckInit();" onchange="test();" style="width: 120px;">
							<button onclick="doIdCheck();" type="button">중복확인</button>
							<label id="idCheckMsg"></label>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input id="password" name="password" type="password" style="width: 120px;">
						</td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td>
							<input type="password" onkeyup="doPasswordCheck(this);" style="width: 120px;">
							<label class="msg"></label>
						</td>
					</tr>
					<tr>
						<td>성명</td>
						<td>
							<input name="nm" type="text" type="text" style="width: 120px;">
						</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<select name="gndr" style="width: 63.5px;">
								<option value="0">남</option>
								<option value="1">여</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="text" type="text" onkeyup="doNumberCheck(this);" style="width: 50px;">
							<label>-</label>
							<input type="text" type="text" onkeyup="doNumberCheck(this);" style="width: 80px;">
							<label>-</label>
							<input type="text" type="text" onkeyup="doNumberCheck(this);" style="width: 80px;">
							<input name="telNo" type="hidden">
							<label class="msg"></label>
						</td>
					</tr>
					<tr>
						<td>휴대전화번호</td>
						<td>
							<input type="text" type="text" onkeyup="doNumberCheck(this);" style="width: 50px;">
							<label>-</label>
							<input type="text" type="text" onkeyup="doNumberCheck(this);" style="width: 80px;">
							<label>-</label>
							<input type="text" type="text" onkeyup="doNumberCheck(this);" style="width: 80px;">
							<input name="mbtlnum" type="hidden">
							<label class="msg"></label>
						</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>
							<input id="email1" type="text" onkeyup="doEmailCheck(this);" style="width: 110px;">
							<label>@</label>
							<input id="email2" type="text" onkeyup="doEmailCheck(this);" style="width: 118px;">
							<input name="email" type="hidden">
							<label class="msg"></label>
						</td>
					</tr>
					<tr>
						<td rowspan="3">주소</td>
						<td>	
							<input style="width: 75px" type="text">
							<label>-</label>
							<input style="width: 75px" type="text">
							<button onclick="findAddrList();" type="button">우편번호 검색</button>
							<input name="zipCode" type="hidden">
						</td>
					</tr>
					<tr>
						<td>
							<input name="addrs" type="text" style="width: 290px;">
						</td>
					</tr>
					<tr>
						<td>
							<input name="detailAddrs" type="text" style="width: 290px;">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div id="addrDialog" title="우편번호 검색" style="display: none; text-align: center;">
   			<div>
	   			<strong style="color: #B2CCFF">읍 / 면 / 동으로 검색 하십시오.</strong>
   			</div>
   			<div style="margin-top: 5px; margin-bottom: 5px;">
	   			<input type="text" style="width: 163px;">
   			</div>
   			
   			<div id="grid" style="width: 380px; margin: 0 auto;"></div>
		</div>
		
		<div class="button-group" style="text-align: center;">	
			<button type="button">가입</button>
			<button type="button">취소</button>
		</div>
	</body>
</html>