<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/nav.jsp"%>

<style>
td {
	max-width: 48px !important;
	overflow: hidden;
}

.nowTimeColor {
	background-color: rgba(178, 204, 255, .2);
}

thead tr th:hover {
	background-color: #e9ecef;
}
</style>

<div class="container">
	<h2>강사 등록</h2>
	<br />
	<form action="/busanit/teachertable?cmd=inputProc"
		method="post">
		<div class="row">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">강사유형</span>
				</div>
				<select id="sel1" name="teacherType" required="required">
					<option value="">선택</option>
					<option value="전임강사">전임강사</option>
					<option value="외래강사">외래강사</option>
				</select>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">훈련분야</span>
					</div>
					<select name="teacherPart" required="required">
						<option value="">선택</option>
						<option value="sw">정보기술개발</option>
						<option value="de">디자인</option>
						<option value="nw">네트워크보안</option>
					</select>
				</div>
				<div class="input-group-prepend mb-3">
					<span class="input-group-text">이름</span> <input name="teacherName"
						type="text" class="form-control" placeholder="강사명을 입력해주세요"
						required="required">
				</div>

				<br />

			</div>
			<input class="btn btn-outline-primary" type="submit" value="등록하기">
			<br />

		</div>
	</form>
	<br /> <br />

	<table class="table">
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">강사유형</th>
				<th scope="col">훈련분야</th>
				<th scope="col">강사명</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="teacher" items="${teachers}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${teacher.teacherType}</td>
					<td>${teacher.teacherPart}</td>
					<td>${teacher.teacherName}</td>
					<td><a class="btn btn-danger"
						href="/busanit/teachertable?cmd=delete&id=${teacher.id}">삭제</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>


<script>
	
</script>

<%@include file="../include/footer.jsp"%>