<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select name="job" onchange="test(this.value)">
		<option value="">직업선택</option>
		<option value="학생">학생</option>
		<option value="회사원">회사원</option>
		<option value="기타">기타</option>
	</select>
	<script type="text/javascript">
	
	function test(value) {
		alert(value);
	}
	
	</script>
</body>
</html>