<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<div>${loginMember.memberId}님 반갑습니다</div>
	 <a href="${pageContext.request.contextPath}/boardList">게시판</a>
	 <a href="${pageContext.request.contextPath}/logout">로그아웃</a>
</body>
</html>