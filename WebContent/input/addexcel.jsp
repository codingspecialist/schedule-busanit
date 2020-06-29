<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>엑셀 추가</title>
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
	  <h2>엑셀 데이터 등록하기</h2>
	  <br />
	  <p>엑셀 파일을 등록하세요</p>
	  <br />
	  <form action="/practiceProgress/practicetable?cmd=addExcelProc" method="post" enctype="multipart/form-data">
	    <div class="form-group">
	      <input type="file" class="form-control-file border" name="file">
	      <input type="hidden" value="${id }" name="id">
	    </div>
	    <div class="text-center">
	    	<button type="submit" class="btn btn-outline-primary">등록하기</button>
	    </div>
	  </form>
	</div>

</body>
</html>