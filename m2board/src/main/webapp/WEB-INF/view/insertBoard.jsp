<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertBoard</title><!-- 외래키를 줘서 아이디가 있는것만 쓸수있음 -->
</head>
<body>
	<h3>게시글 작성</h3>
	<form method="post" action="${pageContext.request.contextPath}/boardInsert">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="member_id" id="member_id"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="board_title" id="board_title"> </td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea row="5" cols="20" name="board_content" id="board_content"></textarea></td>
			</tr>
		</table>
		<button type="submit">등록</button>
	</form>
</body>
</html>