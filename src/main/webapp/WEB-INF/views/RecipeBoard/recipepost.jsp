<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page session="true"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

       <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
   rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

   <!-- CSS -->
   <link rel="stylesheet" href="<c:url value='/css/recipeboard/topbar.css'/>">
   <link rel="stylesheet" href="<c:url value='/css/recipeboard/recipepost.css'/>">


   <!-- Top Bar -->
   <title>${recipe.title}</title>
  </head>
  <body>
    <div id="Topbar" class="Tobar">
      <ul class="Topul">
        <a href="<c:url value='/'/> "class="Topa" id="init">Foodle</a>
        <a href="<c:url value='/recipeboard'/> " class="Topa" id="boardpage">Welcome to Recipe!</a>
      </ul>
    </div>


  <div class="PostForm">
    <div class="ContentForm">
      <div class="TopContent">
        <input class="Title" type="text"  name="title"  id="title" placeholder=" 제목을 입력해 주세요." value="<c:out value='${recipe.title}'/>"readonly />
        <div class="Info" >작성자: ${recipe.writer} |
          작성일: ${recipe.register_date}</div>
        <span class="Info">조회수: ${recipe.view_cnt}</span>
        <div class="LikeModify">
          <span class="Info" id="like">좋아요: ${recipe.like_cnt}</span>
          <c:if test="${sessionScope.id eq recipe.writer}">
            <button type="button" class="btn btn-warning" id="ModifyBtn" onclick="modifyButtons()">수정</button>
            <button type="submit" class="btn btn-warning" id="ModifyRegisterBtn" onclick="modifyButtons()" style="display: none;">등록</button>
          </c:if>
        </div>
      </div>
      <textarea class="form-control Content" name="content" id="content" rows="10" cols="100"  readonly><c:out value='${recipe.content}'/></textarea>
    </div>

    <div class="FtnBtn">
      <c:if test="${sessionScope.id eq recipe.writer}">
      <button type="button" class="btn btn-warning" id="DeleteBtn" onclick="confirmDelete()">삭제</button>
      </c:if>

      <form method="post" id="likebtn">
        <button type="submit" class="btn btn-warning">좋아요</button>
      </form>

      <a href="{{ url_for('board.list_post',page=page,per_page=per_page,option=option,keyword=keyword) }}">
        목록
      </a>    
    </div> 
</div>


<script>
  document.getElementById("ModifyRegisterBtn").addEventListener("click",sendModified)

  function confirmDelete() {
    var status
    if (confirm("삭제하시겠습니까?")) {
        fetch("<c:url value='/recipepost/${recipe.bno}'/>",{
          method : "DELETE",
        })
        .then(response => {
          status = response.status
          return response.text()
        })
        .then(message => {
          alert(message)
          if(status == 200 ) window.location.href= "<c:url value='/recipeboard'/>"
        })
        .catch(error => alert(error))
    }
  }

  function sendModified(){
    var title = document.getElementById("title").value
    var content = document.getElementById("content").value

    fetch("<c:url value='/recipepost/${recipe.bno}'/>", {
      method: "PUT",
      headers: {"Content-Type" : "application/json"},
      body: JSON.stringify({title: title, content: content , bno : ${recipe.bno}})
    })
    .then(response => response.text())
    .then(message => alert(message))
    .catch(error => alert(error))
  }

    function modifyButtons() {
      var modifyBtn = document.getElementById("ModifyBtn");
      var modifyRegisterBtn = document.getElementById("ModifyRegisterBtn");

      var titleInput = document.getElementById("title");
      var contentInput = document.getElementById("content");

      if (modifyBtn.style.display === "none") {
        // 수정 버튼이 사라져 있을 때
        modifyBtn.style.display = "block";
        modifyRegisterBtn.style.display = "none";

        // readonly 속성 추가
        titleInput.setAttribute("readonly", true);
        contentInput.setAttribute("readonly", true);
      } else {
        // 수정 버튼이 보여질 때
        modifyBtn.style.display = "none";
        modifyRegisterBtn.style.display = "block";

        // readonly 속성 제거
        titleInput.removeAttribute("readonly");
        contentInput.removeAttribute("readonly");
      }
    }
</script>
  </body>
</html>
