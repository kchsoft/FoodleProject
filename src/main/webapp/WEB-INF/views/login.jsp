<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> <%-- jstl에서 버전 문제 발생시 , _rt가 버전 문제 해결해줌 --%>
<!doctype html>
<html lang="ko">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
    rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value='/css/login/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/login/topbar.css'/>">
    <title>Foodle</title>

  </head>
  <body>
    <div id="Topbar">
      <ul>
          <li>Foodle</li>
          <li>Welcome to Foodle!</li>
      </ul>
  </div>
    <c:if test="${not empty msg}">
        <p style="color:red">${msg}</p>
    </c:if>
      <form method="post" class="LoginForm">
        <div class="Name">Foodle</div>
        <div id="msg"></div>
        <input class="Input-Form" type="text" name="id" placeholder="ID를 입력하세요" value="${cookie.id.value}" autofocus >
        <input class="Input-Form" type="password" name="password" placeholder="비밀번호를 입력하세요">
        <div class="LoginRegister">
          <div class="idCheckbox">
            <input type="checkbox" name="idStore" value="on" ${empty cookie.id.value ? "" : "checked"}> 아이디 기억
          </div>
          <button type="submit" class="btn btn-warning" id="loginbutton">로그인</button>
          <a href="<c:url value='/register'/>" class="RegisterLink" id="registerlink">회원가입</a>
        </div>
      </form>
  </body>
</html>