<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
 <h1>로그인</h1>
   <form method="post" action="${pageContext.request.contextPath}/login">
   <table border="1">
      <tr>
         <td>ID : <input type="text" name="member_id" id="member_id"></td>
      </tr>
      <tr>
         <td>PASSWORD : <input type="password" name="member_pw" id="member_pw"></td>
      </tr>
   </table>
   <button type="submit">로그인</button>
   <a href="${pageContext.request.contextPath}/addMember">회원가입</a>
   </form>
   <!-- 회원가입태그만들기 -->
   

</body>
</html>