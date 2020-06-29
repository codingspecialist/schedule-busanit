<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/nav.jsp"%>

	<div class="container">
		<h2>현재 과정</h2>
		<br />
		<p>아래 사항들을 확인하고 엑셀 데이터를 세팅해주세요</p>
		<br />
		<table class="table table-striped">
			<thead>
				<tr>
					<th>호실</th>
					<th>훈련명</th>
					<th>기간</th>
					<th>담임</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="trueClassTable" items="${trueClassTables}">
					<tr>
						<td id="room${trueClassTable.room}">${trueClassTable.room}호</td>
						<td>${trueClassTable.className }</td>
						<td>${trueClassTable.classOpen} ~ ${trueClassTable.classClose}</td>
						<td>${trueClassTable.homeroomProf }</td>
						<td>
							<button type="button" class="btn btn-outline-danger btn-sm" onclick="deActivateProc(${trueClassTable.id})">비활성화</button> <c:choose>
								<c:when test="${empty trueClassTable.excelName }">
									<button type="button" class="btn btn-outline-primary btn-sm" onclick="addExcel(${trueClassTable.id})">등록하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-outline-warning btn-sm" onclick="changeExcel(${trueClassTable.id})">변경하기</button>
									<button type="button" class="btn btn-outline-info btn-sm" 
									onclick="statistics(${trueClassTable.id}, ${trueClassTable.room}, '${trueClassTable.classOpen}', '${trueClassTable.classClose}')">검증</button>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <br />
		<div class="col text-center"></div>
	</div>
	<br/>
	<br/>
	<div class="container">
		<h2>비활성 과정</h2>
		<br />
		<p>훈련 기간이 최근 1년 이내인 훈련만 나타납니다.</p>
		<br />
		<table class="table table-striped">
			<thead>
				<tr>
					<th>호실</th>
					<th>수업명</th>
					<th>기간</th>
					<th>담임</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="falseClassTable" items="${falseClassTables}">
					<tr>
						<td>${falseClassTable.room}호</td>
						<td>${falseClassTable.className }</td>
						<td>${falseClassTable.classOpen}~ ${falseClassTable.classClose}</td>
						<td>${falseClassTable.homeroomProf }</td>
						<td>
							<button type="button" class="btn btn-outline-danger btn-sm" onclick="activateProc(${falseClassTable.id}, ${falseClassTable.room})">활성화</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <br />
		<div class="col text-center"></div>
	</div>
	
	<script type="text/javascript">
	
		function statistics(id, room, classOpen, classClose) {
			
			// - (570 / 2) 등은 창 위치 조정을 위함
			
			var popupX = (document.body.offsetWidth / 2) - (700 / 2);

			var popupY = (window.screen.height / 2) - (800 / 2);
			
			var pop = window.open("/practiceProgress/practicetable?cmd=statistics&id="+id+"&room="+room+"&classOpen="+classOpen+"&classClose="+classClose, "pop",
			"width=700, height=630, left="+ popupX + ", top="+ popupY+", scrollbars=yes, resizable=yes");
			
		}
	
		function changeExcel(id) {
			
			// - (570 / 2) 등은 창 위치 조정을 위함
			
			var popupX = (document.body.offsetWidth / 2) - (700 / 2);

			var popupY = (window.screen.height / 2) - (630 / 2);
			
			var pop = window.open("/practiceProgress/practicetable?cmd=changeExcel&id="+id, "pop",
			"width=700, height=630, left="+ popupX + ", top="+ popupY+", scrollbars=yes, resizable=yes");
			
		}
	
		function addExcel(id) {
			
			// - (570 / 2) 등은 창 위치 조정을 위함
			
			var popupX = (document.body.offsetWidth / 2) - (570 / 2);

			var popupY = (window.screen.height / 2) - (300 / 2);
			
			var pop = window.open("/practiceProgress/practicetable?cmd=addExcel&id="+id, "pop",
			"width=570, height=300, left="+ popupX + ", top="+ popupY+", scrollbars=yes, resizable=yes");
			
		}
	
		function deActivateProc(id) {
			
			$.ajax({
				
				type: "get",
				url: "/practiceProgress/practicetable?cmd=deActivateProc&id="+id,
				dataType: "text"
				
			}).done(function(result) {
			
				location.reload();
				
			}).fail(function(error) {
				
				alert("비활성화에 실패하였습니다.");
				
			});
			
		}
	
		function activateProc(id, room) {
			
			$.ajax({
				
				type: "get",
				url: "/practiceProgress/practicetable?cmd=activateProc&id="+id+"&room="+room,
				dataType: "text"
				
			}).done(function(result) {
				if(result == 2){
					alert("활성 상태의 같은 번호의 강의실이 있습니다.");
				} else{
					location.reload();					
				}
				
			}).fail(function(error) {
				
				alert("활성화에 실패하였습니다.");
				
			});
			
		}
		
	</script>
	
<%@include file="../include/footer.jsp"%>