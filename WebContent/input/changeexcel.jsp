<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>엑셀 변경</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
	<br />
	  <h2>${classTable.room }호 엑셀 데이터 변경</h2>
	  	<br />
	  <h4>훈련명:</h4>
	  <h5>${classTable.className }</h5>
	  <h4>담임: ${classTable.homeroomProf }</h4>
	  	<br />
	  <p>기존 훈련의 엑셀 데이터를 삭제하고 새 데이터를 입력합니다.</p>
	  <br />
	  <form action="/busanit/practicetable?cmd=changeExcelProc" method="post" enctype="multipart/form-data">
	    <div class="form-group">
	      <input type="file" class="form-control-file border" name="file">
	      <input type="hidden" value="${id }" name="id">
	    </div>
	    <div class="text-center">
	    	<button type="submit" class="btn btn-outline-primary">변경하기</button>
	    </div>
	  </form>
	  <br />
	  <br />
	</div>
	<div class="container-fluid">        
	  <h3>금일 데이터 미리보기</h3>
	  <p>아래 내용이 원하는 내용과 다를 경우 파일을 변경해주세요.</p>
	  <table class="table table-bordered">
	    <thead>
	      <tr>
	        <th>과정명</th>
	        <th>과목</th>
	        <th>호실</th>
	      </tr>
	    </thead>
	    <tbody>
		  <c:forEach var="pt" items="${pts }">
			  <tr>
		        <td>${pt.className }</td>
		        <td>${pt.subject1 }</td>
		        <td>${pt.room }</td>
		      </tr>
		  </c:forEach>
	    </tbody>
	  </table>
	</div>

</body>
</html>