<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page session="false"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

       <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
   rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

   <!-- CSS -->
   <link rel="stylesheet" href="<c:url value="/css/recipeboard/topbar.css"/> ">
   <link rel="stylesheet" href="<c:url value='/css/recipeboard/recipewrite.css'/> ">


   <!-- Top Bar -->
   <title>글쓰기</title>
  </head>
  <body>
    <div id="Topbar" class="Tobar">
      <ul class="Topul">
        <a href="<c:url value='/main'/> "class="Topa" id="init">Foodle</a>
        <a href="<c:url value='/recipeboard'/> " class="Topa" id="mainpage">Welcome to Recipe!</a>
      </ul>
    </div>


<div class="PostForm">
  <form class="ContentSubmit" action="<c:url value="/recipepost"/> " method="post">
    <div class="ContentForm">
      <div class="TopContent">
        <input class="Title" type="text"  name="title"  id="title" placeholder=" 제목을 입력해 주세요."></input>
          <button type="button" class="btn btn-warning" id="WriteBtn" onclick="writePost()" >등록</button>
        </div>
      </div>
      <textarea class="form-control Content" name="content" id="content" rows="10" cols="100" ></textarea>
    </div>
  </form>

  <div class="FtnBtn">
    <a href="<c:url value="/recipeboard"/> ">
      목록
    </a>
  </div>
</div>


<script>

  function writePost(event){
    var title = document.getElementById('title').value
    var content = document.getElementById('content').value

    fetch("<c:url value='/recipepost'/>", {
      method: "POST",
      headers: { 'Content-Type' : "application/json"},
      body: JSON.stringify({title: title, content: content})
    })
    .then(response => {response.text()})
    .then(message => {
      alert(message);
      window.location.href = "<c:url value="/recipeboard"/>";
    })
    .catch(error => alert("게시물 작성에 실패했습니다. : "+error));
  }

</script>
    
  </body>

</html>
