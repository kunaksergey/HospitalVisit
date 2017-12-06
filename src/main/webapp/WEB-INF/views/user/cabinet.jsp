<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Особистий кабінет</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="/js/bootstrap.bundle.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/cabinet.js"></script>
    <link rel="stylesheet" href="/css/style.css">
 </head>

<body>
<div class="container">
    <c:out value="${user.username}"/><br/>
    <c:out value="${user.fullName}"/><br/>
    <c:out value="${user.phone}"/><br/>
    <c:out value="${user.email}"/><br/>
    <a id="reg-child-open" class="nav-link" href="#">Зареєструвати дитину</a>
</div>

<div id="reg-child" class="container">
    <div class="row">
        <div class="col-lg-6">
            <div id="reg-child-content" class="jumbotron">
                <p><a id="reg-child-close" href="#">Закрити</a></p>
                <div id="reg-child-body">
                    <form id="f_add-child" role="form" method="post">
                        <div class="form-group ">
                            <input type="text" name="fullname" class="form-control" required
                                   placeholder="ПІБ дитини">
                        </div>

                        <div class="form-group">
                            <input type="date" name="birthday" class="form-control" required/>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-info btn-sm">Додати</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>




