<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    pageContext
            .setAttribute("basePath", request.getContextPath() + "/");
%>
<script type="text/javascript">
    function login() {
        window.location.href = "/user/loginUI?backUrl=" + window.location.href;
    }


</script>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="../../img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="../../img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">

            <c:if test="${empty USER_NAME }">
                <li><a href="javascript:" id="loginUrl" onclick="login()">登录</a></li>
                <li><a href="${basePath}jsp/register/register.jsp">注册</a></li>
            </c:if>
            <c:if test="${!empty USER_NAME }">
                <li style="color:red">欢迎您，${USER_NAME.username }</li>
                <li><a href="${pageContext.request.contextPath }/user/logout">退出</a></li>
            </c:if>

            <li><a href="${pageContext.request.contextPath}/jsp/common/cart.jsp">购物车</a></li>
            <li><a href="${pageContext.request.contextPath }/order/orderInfo">我的订单</a></li>
        </ol>
    </div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">首页</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="categoryUl">
                </ul>
                <form class="navbar-form navbar-right" role="search" action="/product/productList">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="商品搜索" name="key">
                        <input type="hidden" name="flage" value="1">
                    </div>
                    <button type="submit" class="btn btn-default">商品搜索</button>
                </form>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                var content = "";
                $.post(
                    "${pageContext.request.contextPath}/index/initCategory",
                    function (data) {

                        for (var i = 0; i < data.length; i++) {
                            content += "<li><a href='${pageContext.request.contextPath}/product/productList?cid=" + data[i].cid + "'>" + data[i].cname + "</a></li>";
                        }
                        $("#categoryUl").html(content);
                    },
                    "json"
                );
            });
        </script>

    </nav>
</div>