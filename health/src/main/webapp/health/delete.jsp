<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!-- 메뉴영역 -->
<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">건강검진결과</a> <!-- 계속 선택한 상태(로고) -->
      </li>
      <li class="nav-item">
        <a class="nav-link" href="list">검진목록</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="insert">검진등록</a>
      </li>
      <li class="nav-item">
        <a class="nav-link active" href="delete">검진삭제</a>
      </li>
    </ul>
  </div>
</nav>
<!-- 삭제 폼 -->
<div class="container mt-3">
  <h2>건강검진 삭제</h2>
  <form action="#">
    <div class="mb-3 mt-3">
      <label for="email">검진번호:</label>
      <input type="number" class="form-control" id="email" placeholder="Enter email" name="email">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>
</body>
</html>