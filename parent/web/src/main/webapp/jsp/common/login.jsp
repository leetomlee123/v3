<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css"/>
    <script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="../../css/style.css" type="text/css"/>

    <script type="text/javascript">
        function myReload() {
            $.ajax({
                url: "${pageContext.request.contextPath}/index/validateCode?noCache=" + new Date().getTime(),
                success: function () {
                    $("#CreateCheckCode").attr("src", "${pageContext.request.contextPath}/index/validateCode?noCache=" + new Date().getTime());
                }
            });
        }

        function submit() {
            $("#login").submit();
        }
    </script>
    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }

        .container .row div {
            /* position:relative;
                         float:left; */

        }

        font {
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right: 17px;
        }
    </style>
</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="/jsp/common/header.jsp"></jsp:include>


<div class="container"
     style="width: 100%; height: 460px; background: #FF2C4C no-repeat;">
    <div class="row">
        <div class="col-md-7">
            <!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
        </div>

        <div class="col-md-5">
            <div
                    style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
                <font>会员登录</font>
                <div>&nbsp;</div>
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath }/user/login"
                      id="login">

                    <input type="hidden" name="method" value="login">

                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="username" name="username"
                                   value="${user.username}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="password" name="password"
                                   value="${user.password}"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="validateCode" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="validateCode" name="validateCode"
                                   placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-4">
                            <a href="javascript:void(0);" onclick="myReload()"><img
                                    src="${pageContext.request.contextPath}/index/validateCode"
                                    id="CreateCheckCode"
                                    align="middle">
                            </a>
                        </div>
                    </div>
                    <input type="hidden" name="backUrl" id="backUrl">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <p class="col-lg-10" style="color: #44ffb8">${cause}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-success col-lg-10" onclick="submit()">登陆</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
</html>