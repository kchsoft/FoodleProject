<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <!-- Bootstrap CSS -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
   rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

   <!-- CSS -->
   <link rel="stylesheet" href="<c:url value='/css/recipeboard/topbar.css'/>">
   <link rel="stylesheet" href="<c:url value='/css/recipeboard/recipeboard.css'/>">

  <title>게시판</title>
</head>

<body>

  <div id="Topbar" class="Tobar">
    <ul class="Topul">
        <a href="<c:url value="/main"/>"class="Topa" id="init">Foodle</a>
        <a href="<c:url value="/recipeboard"/>" class="Topa" id="mainpage">Welcome to Recipe!</a>
    </ul>
</div>

<div class="Post-Container">

  <!-- Search Bar & Write & Rank -->
  <form action="" method="GET" class="input-group flex-nowrap">
    <span class="input-group-text" id="addon-wrapping">
      <select name="option" class="form-select" aria-label="Default select example">
        <option value="T" {% if option == 'T' %}selected{% endif %}>제목</option>
        <option value="C" {% if option == 'C' %}selected{% endif %}>내용</option>
        <option value="TC" {% if option == 'TC' %}selected{% endif %}>제목+내용</option>
      </select>
    </span>
    <input type="text" name ="keyword" value="" class="form-control" placeholder="검색할 내용을 입력해 주세요." aria-label="Username" aria-describedby="addon-wrapping">
    <button type="submit" class="btn btn-outline-warning">검색</button>
    <div class="WriteRanking">
      <a href="<c:url value='/recipepost'/>" class="link-warning">글쓰기</a>
      <a href="{{ url_for('board.show_top_posts',type='view') }}" class="link-warning">랭킹</a>
    </div> 
  </form>

  <!-- per_page 10,20,30 -->
  <div  class="per_page">
    <div class="page-item {% if posts.per_page == 10 %}active{% endif %}">
      <a class="page-link" href="{{ url_for('board.list_post', page=1, per_page=10,option=option,keyword=keyword) }}">10</a>
    </div>
    <div class="page-item {% if posts.per_page == 20 %}active{% endif %}">
      <a class="page-link" href="{{ url_for('board.list_post', page=1, per_page=20,option=option,keyword=keyword) }}">20</a>
    </div>
    <div class="page-item {% if posts.per_page == 30 %}active{% endif %}">
      <a class="page-link" href="{{ url_for('board.list_post', page=1, per_page=30,option=option,keyword=keyword) }}">30</a>
    </div>
  </div>

  <!-- Title -->
  <table class="table table-hover">
    <thead>
      <tr class="PostInfo">
        <th scope="col" class="PostId">번호</th>
        <th scope="col" class="PostAuthor">작성자</th>
        <th scope="col" class="PostTitle">제목</th>
        <th scope="col" class="PostView">조회수</th>
        <th scope="col" class="PostLike">좋아요</th>
        <th scope="col" class="PostTime">작성 일자</th>
      </tr>
    </thead>

    <!-- Post -->             <!-- pageContext.request.getAttribute('recipe')} ,  recipe  , requestScope.recipe-->
    <tbody>                           <!-- is possible , Model is in requestScope-->
    <c:forEach var="postDto" items="${recipe}">
    <tr class="PostInfo">
        <th scope="row" class="PostId">${postDto.bno}</th>
        <td class="PostWriter">${postDto.writer}</td>
          <td class="PostTitle">
            <a href="<c:url value='/recipepost/${postDto.bno}'/> ">
            ${postDto.title}
            </a> 
          </td>
            <td class="PostView">${postDto.view_cnt}</td>
            <td class="PostLike">${postDto.like_cnt}</td>
            <td class="PostTime">${postDto.register_date}</td>
    </tr>
    </c:forEach>

    </tbody>
  </table>

    <!-- pagination -->
    <ul class="pagination">
      <!-- prev -->
      {% if posts.has_prev %}
      <li class="page_item"> 
        <a class="page-link" href="">
          이전
        </a>
      </li>
      {% else %}
      <li class="page-item disabled">
        <a class="page-link" tabindex="-1" aria-disabled="true" href="#">이전</a>
      </li>
      {% endif %}
      
      <!-- page num -->
      {% for page_num in posts.iter_pages() %}
        {% if page_num %}
        <li class="page-item {% if posts.page == page_num %}active{% endif %}">
          <a class="page-link" href="{{ url_for('board.list_post', page=page_num,per_page=posts.per_page,option=option,keyword=keyword) }}" >
            {{ page_num }}
          </a>
        </li>
        {% else %}
          <li class="disabled">
            <a class="page-link" href="#">...</a>
          </li>
        {% endif %}
      {% endfor %}


      <!-- next -->
      {% if posts.has_next %}
      <li class="page_item"> 
        <a class="page-link" href="{{ url_for('board.list_post', page=posts.next_num,per_page=posts.per_page,option=option,keyword=keyword) }}">
          다음
        </a>
      </li>
      {% else %}
      <li class="page-item disabled">
        <a class="page-link" tabindex="-1" aria-disabled="true" href="#">다음</a>
      </li>
      {% endif %}
    </ul>

</div>
</body>
</html>    
    
    
    
    
    
   