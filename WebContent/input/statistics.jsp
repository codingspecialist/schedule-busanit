<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<br />
	<div class="container">
		<h2>데이터 상태</h2>
		<ul class="list-group">
			<li class="list-group-item"><c:choose>
					<c:when test="${sameServiceTime }">
						<b style="color: green;">총시수와 선생님들 시수의 합이 같습니다.</b>
					</c:when>
					<c:otherwise>
						<b style="color: red">총시수와 선생님들 시수의 합이 다릅니다.</b><br/>
						<b style="color: red">엑셀 데이터를 확인하세요.</b>
					</c:otherwise>
				</c:choose></li>
			<li class="list-group-item"><c:choose>
					<c:when test="${noWrongClassDate }">
						<b style="color: green;">데이터가 모두 기간 내에 있습니다.</b>
					</c:when>
					<c:otherwise>
						<b style="color: red">훈련 기간을 이탈한 데이터가 있습니다.(${countWrongClassDate }건)</b><br/>
						<b style="color: red">기간 설정을 확인하시거나 엑셀 데이터를 확인하세요.</b>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</div>
	<br />
	<div class="container">
		<br />
		<table class="table table-striped">
			<thead>
				<tr>
					<th>총시수</th>
					<th>${allServiceTime }</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pst" items="${profServiceTimeDtos }">
					<tr>
						<td>${pst.prof }</td>
						<td>${pst.serviceTime }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr />
	</div>
</body>
</html>