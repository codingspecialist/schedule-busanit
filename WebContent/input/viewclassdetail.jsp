<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<br />
	<div class="container">
		<form action="/practiceProgress/classtable?cmd=detailUpdateProc" method="post">
			<div class="text-center">
				<h2>${classTable.room }호 상세보기</h2>
				<input name="id" type="hidden" value="${classTable.id }"> 
				<input name="room" type="hidden" value="${classTable.room }">
			</div>
			<br />
			<table class="table table-striped">
				<thead>
					<tr>
						<th>강의실</th>
						<th>${classTable.room }</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>과정번호(임시)</td>
						<td>${processCode }</td>
					</tr>
					<tr>
						<td>훈련명</td>
						<td><input name="className" type="text" style="width: 100%" value="${classTable.className }" required="required"></td>
					</tr>
					<tr>
						<td>분야</td>
						<td><select name="classPart" required="required">
								<option value="${classTable.classPart ne null ? classTable.classPart : '' }">
									<c:choose>
										<c:when test="${classTable.classPart eq 'sw'}">정보기술개발</c:when>
										<c:when test="${classTable.classPart eq 'de'}">디자인</c:when>
										<c:when test="${classTable.classPart eq 'nw'}">네트웍보안</c:when>
										<c:otherwise>없음</c:otherwise>
									</c:choose>
								</option>
								<c:choose>
									<c:when test="${classTable.classPart eq 'sw'}">
										<option value="de">디자인</option>
										<option value="nw">네트웍보안</option>
									</c:when>
									<c:when test="${classTable.classPart eq 'de'}">
										<option value="sw">정보기술개발</option>
										<option value="nw">네트웍보안</option>
									</c:when>
									<c:when test="${classTable.classPart eq 'nw'}">
										<option value="sw">정보기술개발</option>
										<option value="de">디자인</option>
									</c:when>
									<c:otherwise>
										<option value="sw">정보기술개발</option>
										<option value="de">디자인</option>
										<option value="ne">네트웍보안</option>
									</c:otherwise>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td>개강일</td>
						<td><input name="classOpen" type="date" value="${classTable.classOpen }" required="required"></td>
					</tr>
					<tr>
						<td>종강일</td>
						<td><input name="classClose" type="date" value="${classTable.classClose}" required="required"></td>
					</tr>
					<tr>
						<td>담임교사</td>
						<td><input name="homeroomProf" type="text" style="width: 100%" value="${classTable.homeroomProf }" required="required"></td>
					</tr>
					<tr>
						<td>파일명</td>
						<c:choose>
							<c:when test="${empty classTable.excelName }">
								<td><input name="excelName" type="text" style="width: 100%" value="${classTable.excelName }" ></td>
							</c:when>
							<c:otherwise>
								<td>${classTable.excelName }</td>
								<input name="excelName" type="hidden" value="${classTable.excelName}">
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td>상태</td>
						<c:choose>
							<c:when test="${classTable.status eq 'true'}">
								<td>활성화</td>
								<input name="status" type="hidden" value="${classTable.status}">
							</c:when>
							<c:otherwise>
								<td>비활성화</td>
								<input name="status" type="hidden" value="${classTable.status}">
							</c:otherwise>
						</c:choose>
<!-- 						<td><select name="status" required="required"> -->
<%-- 								<option value="${classTable.status }">${classTable.status }</option> --%>
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${classTable.status }"> --%>
<!-- 										<option value="false">false</option> -->
<%-- 									</c:when> --%>
<%-- 									<c:otherwise> --%>
<!-- 										<option value="true">true</option> -->
<%-- 									</c:otherwise> --%>
<%-- 								</c:choose> --%>
<!-- 						</select></td> -->
					</tr>

				</tbody>
			</table>
			<br />
			<div class="text-center">
				<button type="submit" class="btn btn-warning btn-lg">변경하기</button>
				<button type="button" onclick="deleteClass(${classTable.id })" class="btn btn-danger btn-lg">삭제하기</button>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	
	function deleteClass(id) {
		
		location.href = "/practiceProgress/classtable?cmd=detailDeleteProc&id="+id;
		
// 		$.ajax({
			
// 			type: "get",
// 			url: "/practiceProgress/classtable?cmd=detailDeleteProc&room"+room
			
// 		}).done(function(result) {
			
// 			alert("삭제 성공하였습니다.");
// 			opener.location.reload();
// 			window.close();
			
// 		}).fail(function(error) {
			
// 			alert("실패");
			
// 		});
		
	}


</script>
</body>
</html>