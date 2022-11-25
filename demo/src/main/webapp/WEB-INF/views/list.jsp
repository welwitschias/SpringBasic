<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
	<h3>List</h3>
	<div id="memberDiv">
		<c:forEach items="${lists.content}" var="member">
			아이디 : ${member.id}
			<br>
			이름 : ${member.name}
			<br>
			비밀번호 : ${member.password}
			<br>
			이메일 : ${member.email}
			<br>
			주소 : ${member.addr}
			<br>
			<a href="detail/${member.id}">상세보기</a>
			<a href="javascript:funDetail(${member.id})">상세보기(ajax)</a>
			<br>
			<button type="button" onclick="funDelete(${member.id})">
				삭제하기(onclick)</button>
			<button type="button" class="btnDel" data-mid="${member.id}">
				삭제하기(class)</button>
			<br>
			<hr>
		</c:forEach>
	</div>
	
	<a href="?page=${lists.number-1}">이전</a>
	<a href="?page=${lists.number+1}">다음</a>
	<a href="/">홈으로</a>
	<hr>
	
	<div id="detailDiv"></div>
	<script>
		function funDetail(id) {
			// alert(id)
			$.ajax({
				type : 'GET',
				url : '/detail2/'+id
			})
			.done(function(resp) {
				// alert('성공 : ' + resp.name)
				str = "name : " + resp.name + "<br>"
				str += "email : " + resp.email + "<br>"
				str += "memo : " + resp.memo + "<br>"
				$("#detailDiv").html(str)
			})
			.fail(function(e) {
				alert('실패')
			})
		}
	
		function funDelete(id) {
			// alert(id)
			$.ajax({
				type : 'DELETE',
				url : '/delete/'+id
			})
			.done(function(resp) {
				alert(resp + '번 삭제 성공')
				location.href = '/list'
			})
			.fail(function(e) {
				alert('삭제 실패')
			})
		}
		
		// 함수를 변수에 저장해서 사용
		var delfun = function() {
			$.ajax({
				type : 'DELETE',
				url : '/delete/'+$(this).data('mid')
			})
			.done(function(resp) {
				alert(resp + '번 삭제 성공')
				location.href = '/list'
			})
			.fail(function(e) {
				alert('삭제 실패')
			})
		}
		$("#memberDiv").on("click", ".btnDel", delfun)
		
		// id=memberDiv 중에서 click 했을 때 btnDel class만 실행되는 함수
		/*
		$("#memberDiv").on("click", ".btnDel", function() {
			// alert($(this).attr('data-mid'))
			// alert($(this).data('mid')) // 위의 코드랑 동일함
			$.ajax({
				type : 'DELETE',
				url : '/delete/'+$(this).data('mid')
			})
			.done(function(resp) {
				alert(resp + '번 삭제 성공')
				location.href = '/list'
			})
			.fail(function(e) {
				alert('삭제 실패')
			})
		})
		*/
	</script>
</body>
</html>