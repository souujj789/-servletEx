<!-- 회원 수정페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.vo.member" %>        <!-- vo 추가 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!-- 수정페이지는 수정하는 데이터의 기본키값을 반드시 저장 -->
<!-- 누구를 수정할것인가? 기본키 -->
<%
//한개의 레코드를 읽어와서 저장
member members = (member)request.getAttribute("members");
%>
<h3>회원 수정</h3><!-- 각 항목에 값을 출력 value에 -->
<form action="update" method="post" class="col-3">
  <input type="hidden" name="mon" value=<%=members.getMon() %>> <!-- 숨겨진 기본키 -->
  <div class="mb-3 mt-3">
    <label for="tname" class="form-label">회원이름:</label>
    <input type="text" class="form-control" id="tname" placeholder="회원이름을 입력하세요." name="tname" value=<%=members.getMname() %>>
  </div>
  <div class="mb-3">
    <label for="email" class="form-label">이메일:</label>
    <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요." name="email" value=<%=members.getEmail() %>>
  </div>
  <div class="mb-3">
    <label for="pwd" class="form-label">비밀번호:</label>
    <input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요." name="pwd" value=<%=members.getPwd() %>>
  </div>
  <button type="submit" class="btn btn-primary">수정</button>
</form>
</body>
</html>