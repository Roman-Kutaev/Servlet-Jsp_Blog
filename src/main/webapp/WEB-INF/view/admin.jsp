<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <script src="https://cdn.tiny.cloud/1/koolxfdub4sj7wf52rr59pgggri7b8sk2kvi9tf352z3b1yf/tinymce/5/tinymce.min.js"
            referrerpolicy="origin"></script>
    <style>
        form, h2, div{
            display: block;
            text-align: center;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="resources/css/blog-post.css" rel="stylesheet">

</head>
<body>
<meta charset="utf-8">
<h2>Форма добавления/удаления поста в блог.</h2>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="home"/>">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="login"/>">Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="admin"/>">Admin</a>
                </li>
                <%--                <li class="nav-item">--%>
                <%--                    <a class="nav-link" href="#">Contact</a>--%>
                <%--                </li>--%>
            </ul>
        </div>
    </div>
</nav>

<div>
<c:if test="${error == 'error img'}">
    <p style="color: #b21f2d">Ваш пост не был добавлен в блог, пожалуйста, загрузите изображение.</p>
</c:if>
</div>

<form method="post" enctype="multipart/form-data">
    <label  for="title">Заголовок   <textarea id="title" name="title" required="required" cols="60" rows="1"></textarea></label>
    <hr>
    <br>
    <label for="author">Автор поста  <textarea id="author" name="author" required="required" cols="60" rows="1"></textarea></label>
    <hr>
    <br>
    <label>Фото: <input name="file" type="file"></label>
    <hr>
    <br>
    <label for="author">Краткое описание<textarea id="description" name="description" cols="60" rows="3"></textarea></label>
    <hr>
    <br>
    <label>Текст поста  <textarea id="text" name="text" cols="60" rows="5"></textarea></label>
    <hr>
    <br>
    <label >Черновик: <input type="checkbox" name="draft"/></label>
    <br>
    <br>
    <label >Удалить: <input type="checkbox" name="remove"/> (Чтоб удалить пост необходимо указать его заголовок и автора, поставить галочку)</label>
    <hr>
    <br>
    <input type="submit" value="Подтвердить" />
    </form>

<script>
    tinymce.init({
        selector: '#description, #text',
        plugins: 'advlist autolink lists link image charmap print preview hr anchor pagebreak',
        toolbar_mode: 'floating',
    });
</script>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
