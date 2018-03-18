<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>MOM</title></head>
<body><h1>Nice to meet you</h1></body>
<div>${code}</div>
<c:forEach items="${lists}" var="i" varStatus="vs">
    <c:if test="${empty i}">

    </c:if>
    <th>${i}</th>
</c:forEach>
<a href="${pageContext.request.contextPath}/user/getAllUsers">HHHH
</a>
<iframe src="${pageContext.request.contextPath}/WEB-INF/jsp/admin/home.jsp" style="width: 50%;height: 40%;border-style: none"></iframe>
<img src="${pageContext.request.contextPath}/images/bank_img/bc.bmp">
</html>