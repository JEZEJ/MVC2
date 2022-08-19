<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 상세보기</h1>

	<div>
		<a href="${pageContext.request.contextPath}/boardInsert"> 글쓰기
		</a>
	</div>

	<table border="1">
		<tbody>
			<c:forEach var="b" items="${list}">
				<tr>
					<th>번호</th>
					<td>${b.boardNo}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${b.memberId}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${b.title}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${b.boardContent}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${b.createDate}</td>
				</tr>
				<tr>
					<th>조회</th>
					<td>${b.boardRead}</td>
				</tr>
				<tr>
					<th>좋아요</th><!-- 상세보기에서 추천누르면 좋아요수가 올라가게 설정 -->
					 <td><a href="${pageContext.request.contextPath}/boardNice?boardNo=${b.boardNo}&boardNice=${b.boardNice}">추천</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>