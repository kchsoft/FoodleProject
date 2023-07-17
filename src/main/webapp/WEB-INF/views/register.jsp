<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page session="false"%>
<!doctype html>
<html lang="ko">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
    rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value='/css/register/register.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/register/topbar.css'/> ">
    <title>Foodle</title>
    
  </head>
  <body>
    <div id="Topbar">
      <ul>
          <li>Foodle</li>
          <li>Sign up for Foodle!</li>
      </ul>
    </div>
    
      <form method="post" class="RegisterForm">
        <div class="Name">Foodle</div>
        <div id="error-message" style="color:red"></div>
        <div id="avail-message" style="color:green"></div>

        <label for="">아이디</label>
        <input class="Input-Form" type="text" onchange="changeCheck()" name="id" placeholder="ID" autofocus required>

        <label for="">비밀번호</label>
        <input input class="Input-Form" type="password" name="password" placeholder="비밀번호" required>

        <label for="">이름</label>
        <input class="Input-Form" type="text" name="name" placeholder="이름" required>

        <label for="">이메일</label>
        <input class="Input-Form" type="text" name="email" placeholder="example@site.com"> 

        <label for="">생일</label>
        <input class="Date-Form" type="date" name="birthday" value="">

        <div class="Register">
          <button type="button" class="btn btn-warning" id="registerbutton" onclick="register()">회원가입</button>
          <button type="button" class="btn btn-warning" id="dpulicatebutton" onclick="idDupCheck()">ID중복체크</button>
          <a href="<c:url value="/login"/>" class="LoginLink" id="loginlink">로그인 화면</a>
        </div>
      </form>


      <script>
        let isIdDupCheck = false;

        function changeCheck(){
          isIdDupCheck = false;
        }

        function idDupCheck() {
          var id = document.getElementsByName('id')[0].value;
          fetch("register/idcheck", {
              method: 'POST',
              headers: { 'Content-Type': 'text/plain'},
              body: id
          })
          .then(response => response.text())
          .then(message => {
            alert("" + message);
            isIdDupCheck = true;
          })
          .catch(function(error){
            alert("ID Check Fail :" + error);
          })
      }

      function register() {
         if(!isIdDupCheck){
           alert("ID 중복체크를 확인해 주세요.");
           return;
         }
        const name = document.getElementsByName('name')[0].value;
        const id = document.getElementsByName('id')[0].value;
        const password = document.getElementsByName('password')[0].value;
        const email = document.getElementsByName('email')[0].value;
        const birthday = document.getElementsByName('birthday')[0].value;

        fetch('register', {
          method: "POST",
          headers: {'content-type': 'application/json'},
          body: JSON.stringify({
            name: name,
            id: id,
            password: password,
            email: email,
            birthday: birthday
          })
        })
        .then(response => response.text())
        .then(message => {
          alert(message);
          window.location.href = "<c:url value="/login"/> ";
        })
        .catch(error => alert("회원가입에 실패하였습니다. : " + error));
        }

      </script>
  </body>
</html>