<%--
  Created by IntelliJ IDEA.
  User: 77558
  Date: 2017/9/30
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><c:choose>
        <c:when test="${empty title}">
            404 - 对不起，您查找的页面不存在！
        </c:when>
        <c:otherwise>
            500 - 对不起，一些不知情的錯誤發生了！
        </c:otherwise>
    </c:choose>
    </title>
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
</head>
<body>
<div id="wrapper"><a class="logo" href="/"></a>
    <div id="main">
        <header id="header">
            <h1><span class="icon">!</span><c:choose>
                <c:when test="${empty title}">
                    404
                </c:when>
                <c:otherwise>
                    500
                </c:otherwise>
            </c:choose><span class="sub"><c:choose>
                <c:when test="${empty title}">
                    page not found
                </c:when>
                <c:otherwise>
                    ${message}
                </c:otherwise>
            </c:choose></span></h1>
        </header>
        <div id="content">
            <%--<h2>您打开的这个的页面不存在！</h2>--%>
            <%--<p>--%>
                <%--当您看到这个页面,表示您的访问出错,这个错误是您打开的页面不存在,请确认您输入的地址是正确的,如果是在本站点击后出现这个页面,请联系站长进行处理!</p>--%>
            <div class="utilities">
                <form name="formsearch" action="" id="formkeyword">
                    <div class="input-container">
                        <input type="text" class="left" name="q" size="24" value="在这里搜索..."
                               onfocus="if(this.value=='在这里搜索...'){this.value='';}"
                               onblur="if(this.value==''){this.value='在这里搜索...';}" id="inputString"
                               onkeyup="lookup(this.value);" onblur="fill();" placeholder="搜索..."/>
                        <button id="search"></button>
                    </div>
                </form>
                <a class="button right" href="#" onClick="history.go(-1);return true;">返回...</a><a class="button right"
                                                                                                   href="">联系站长</a>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
