<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	// 	Users principal = (Users) session.getAttribute("principal");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/busanit/classtable?cmd=home">부산IT 과정관리</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="justify-content-between collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
			    <li class="nav-item"><a class="nav-link" href="/busanit/classtable?cmd=home">진행중과정</a></li>
			    <li class="nav-item"><a class="nav-link" href="/busanit/teachertable?cmd=status">월간강사배정현황</a></li>
			    <li class="nav-item"><a class="nav-link" href="/busanit/teachertable?cmd=input">강사등록</a></li>
				<li class="nav-item"><a class="nav-link" href="/busanit/classtable?cmd=input">훈련과정등록</a></li>
				<li class="nav-item"><a class="nav-link" href="/busanit/practicetable?cmd=input">시간표등록</a></li>
			</ul>
		</div>
	</nav>
	<br>