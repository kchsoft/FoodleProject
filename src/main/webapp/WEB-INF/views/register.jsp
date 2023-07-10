<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <link rel="stylesheet" href="<c:url value='/css/auth/register.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/auth/topbar.css'/> ">
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
        <input class="Input-Form" type="text" name="username" placeholder="ID" autofocus>

        <label for="">비밀번호</label>
        <input input class="Input-Form" type="password" name="password" placeholder="비밀번호">

        <label for="">이름</label>
        <input class="Input-Form" type="text" name="name" placeholder="이름">

        <label for="">이메일</label>
        <input class="Input-Form" type="text" name="email" placeholder="example@site.com"> 

        <label for="">생일</label>
        <input class="Date-Form" type="date" name="birth" value="">

        <div class="Register">
          <button type="button" class="btn btn-warning" id="registerbutton" onclick="checkForm()">회원가입</button>
          <button type="button" class="btn btn-warning" id="dpulicatebutton" onclick="checkId()">ID중복체크</button>
          <a href="/auth/login" class="LoginLink" id="loginlink">로그인 화면</a>
        </div>
        <script>
         function checkId() {
            // username 변수에 id가 'username'인 요소의 값을 할당
            var username = document.getElementsByName('username')[0].value;
            // fetch 함수를 사용하여 'check_id' URL로 POST 요청을 보냄
            fetch('check_id', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                // 요청 바디에 JSON 형식으로 username 값을 담아 보냄
                body: JSON.stringify({ username: username })
            })
            // 응답을 JSON 형식으로 파싱하여 처리
            .then(response => response.json())
            .then(data => {
                // id가 'message'인 요소를 찾아 message 값을 할당
                var messageElem = document.getElementById('error-message');
                messageElem.innerText = data.message;
            });
        }
        function checkForm() {
          const name = document.getElementsByName('name')[0].value;
          const username = document.getElementsByName('username')[0].value;
          const password = document.getElementsByName('password')[0].value;
          const email = document.getElementsByName('email')[0].value;
          const birth = document.getElementsByName('birth')[0].value;

          // API에 POST 요청을 보냅니다.
          fetch('check_form', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              name: name,
              username: username,
              password: password,
              email: email,
            })
          }).then(response => {
            if (response.ok) {
              // 데이터가 유효한 경우, 회원가입 API를 호출하여 회원가입을 진행합니다.
              fetch('register', {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                  name: name,
                  username: username,
                  password: password,
                  email: email,
                  birth: birth
                })
              })
              .then(response => {
                if (response.ok) {
                  console.log(response.status)
                  if(response.status == 200){
                    window.location.href = '/auth/login';
                  }
                  else if(response.status == 202){
                    alert("중복된 회원정보가 있습니다.")
                  }
                    
                }
              })
            } else {
              // 데이터가 유효하지 않은 경우, 에러 메시지를 출력합니다.
              response.json().then(data => {
                const error = data.error;
                document.getElementById('error-message').textContent = error;
              });
            }
          }).catch(error => {
            console.error('Error:', error);
          });
        }
        </script>
  </body>
</html>