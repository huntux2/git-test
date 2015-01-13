<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 파라미터 인코딩 형식 -->
<% request.setCharacterEncoding("UTF-8"); %>

<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			.tcktTable {
				background: #FFFFFF;
				padding: 5px;
				width: 600px;
				font-weight: bolder;
				border-collapse: collapse;
				margin: 0 auto;
				margin-top: 30px;
			}
			.tcktTable .head {
				background-color: #515151;
				color: #FFFFFF;
			}
			.tckt-group .tcktTable:FIRST-CHILD {
				margin-top: 10px;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function(){
				/*Action style*/
				$($(".menu td").get(1)).addClass("set");
				$($(".lmb tr").get(0)).children("td").addClass("set");
				
				if("${requestForm == 'setleSuccess.html'}"){
					/*lmb img*/
					$("#lmbImg").attr("src", "/res/img/tra_visual01.jpg");
				}else{
					alert("else");
				}
			});
		</script>
	</head>
	<body>
		<c:if test="${requestForm == 'setleSuccess.html'}">
			<div style="font-size: 35px; padding-bottom: 15px;">
	   			<strong style="float: left;">
	   				${menuTree[3]}
	   			</strong>
	   			<div style="float: right; vertical-align: bottom; background: #FFFFFF; border-radius: 7px;">
	   				<img src="/res/img/step_tck01.gif">
	   				<img src="/res/img/step_tck02.gif">
	   				<img src="/res/img/step_tck03.gif">
	   				<img src="/res/img/step_tck04_on.gif">
	   			</div>
	   		</div>
	   		
			<!-- 사용방법 -->
	   		<div style="clear: left; clear: right;">
				<div class="caption" style="margin-top: 40px; margin-bottom: 0px;">
					* 방금 결제하신 승차권에 대한 결제내역과 좌석정보 입니다.<br>
					* 승차권 현황 보기를 이용하여 다른 승차권에 대한 현황을 보실 수 있습니다.<br>
					* 승차권 예매를 취소는 승차권 현황화면에서 가능합니다.
				</div>
	   		</div>
   		
			<div style="text-align: center; margin-top: 10px;">
				<button type="button" >승차권 현황 보기</button>
			</div>
		</c:if>
   		
   		<!-- 승차권정보 -->
   		<div class="tckt-group">
   			<c:forEach var="data" items="${tcktInfo.seatInList}">
	   			<table class="tcktTable" style="border: 1px solid black; text-align: center;">
		   			<thead>
		   				<tr class="head">
			   				<td colspan="3">승차권</td>
		   				</tr>
		   			</thead>
		   			<tbody>
		   				<tr style="font-size: 30px;">
		   					<td>${data.startStatn}</td>
		   					<td>▶</td>
		   					<td>${data.arvlStatn}</td>
		   				</tr>
		   				<tr>
		   					<td>${data.startTm}</td>
		   					<td></td>
		   					<td>${data.arvlTm}</td>
		   				</tr>
		   				<tr>
		   					<td>${data.trainNo} ${data.trainKnd}</td>
							<td>${data.room} 호차 (${data.roomKnd})</td>
							<td>${data.seatNo} 석</td>
						</tr>
						<tr>
		   					<td colspan="3">승차자 ${data.psngrNm} (${data.psngrKnd})</td>
		   				</tr>
						<tr class="head">
		   					<td>운임금액 ${data.frAmount} 원</td>
		   					<td>할인금액 ${data.dscntAmount} 원</td>
		   					<td>영수금액 ${data.rcptAmount} 원</td>
		   				</tr>
						<tr>
							<td>결제자 ${tcktInfo.id}</td>
							<td>결제일 ${tcktInfo.rgsde}</td>
							<td>매 수 ${tcktInfo.resveCo} 장</td>
						</tr>
						<tr>
							<td>총 운임요금 ${tcktInfo.frAmount} 원</td>
							<td>총 할인금액 ${tcktInfo.dscntAmount} 원</td>
							<td>추가 할인금액 ${tcktInfo.usePint} 원</td>
						</tr>
						<tr class="head">
							<td colspan="3">결제금액 ${tcktInfo.setleAmount} 원</td>
						</tr>
		   			</tbody>
		   		</table>
	  	 	</c:forEach>
		</div>
   	</body>
</html>