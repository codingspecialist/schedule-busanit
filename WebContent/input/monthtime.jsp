<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../include/nav.jsp"%>

<style>

.nowTimeColor {
	background-color: rgba(178, 204, 255, .2);
}

thead tr th:hover {
	background-color: #e9ecef;
}
</style>

<div class="container">

	<form action="/busanit/teachertable?cmd=status" class="form-inline"  method="post">
		<input id="my_year" class="form-control" type="text" name="year" placeholder="년도 입력 : 예) 2020" required/>
		<select class="form-control" name="month" required="required">
			<option value="">선택</option>
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select>
		&nbsp;
		<button class="btn btn-primary">월별 검색</button>
	</form>
	<hr/>
	<table class="table">
		<thead>
			<tr>
				<th class="my_title" scope="col">구분</th>
				<c:forEach var="teacher" items="${teachers}">
					<th scope="col">${teacher.teacherName}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${dtos}">
				<tr>
					<td class="my_title">${dto.className}</td>
					<c:forEach var="data" items="${dto.monthTime}">
						<td>${data.time}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>교강사별 시수 합계</th>
				<c:forEach var="count"  items="${monthCount}">
					<th>${count}</th>	
				</c:forEach>	
			</tr>
		</tfoot>
	</table>
	<br/> <br/>
	
	<table class="table">
		<thead>
			<tr>
				<th class="all_title" scope="col">구분</th>
				<c:forEach var="teacher" items="${teachers}">
					<c:if test="${teacher.teacherType eq '전임강사' }">
						<th class="all_col" scope="col">${teacher.teacherName}</th>
					</c:if>
				</c:forEach>
				<th class="all_col" scope="col">소계</th>
				<th class="all_col" scope="col">비율</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="all_title">2020년 총시수(전임강사)</td>
				<c:forEach var="count" items="${inTeacherCount}">
					<td class="all_col">${count}</td>
				</c:forEach>
			</tr>
		</tbody>

	</table>
	<table class="table">
		<thead>
			<tr>
				<th class="all_title" scope="col">구분</th>
				<c:forEach var="teacher" items="${teachers}">
					<c:if test="${teacher.teacherType eq '외래강사' }">
						<th class="all_col"scope="col">${teacher.teacherName}</th>
					</c:if>
				</c:forEach>
				<th class="all_col" scope="col">소계</th>
				<th class="all_col" scope="col">비율</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="all_title">2020년 총시수(외래강사)</td>
				<c:forEach var="count" items="${outTeacherCount}">
					<td class="all_col">${count}</td>
				</c:forEach>
			</tr>
		</tbody>

	</table>
</div>




<%@include file="../include/footer.jsp"%>