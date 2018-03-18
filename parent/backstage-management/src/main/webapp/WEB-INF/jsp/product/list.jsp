<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
    <script type="text/javascript">
        function addProduct() {
            window.location.href = "${pageContext.request.contextPath}/product/to_add";
        }
    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>商品列表</strong>
            </TD>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
                    &#28155;&#21152;
                </button>

            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr
                            style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

                        <td align="center" width="18%">
                            序号
                        </td>
                        <td align="center" width="17%">
                            商品图片
                        </td>
                        <td align="center" width="17%">
                            商品名称
                        </td>
                        <td align="center" width="17%">
                            商品价格
                        </td>
                        <td align="center" width="17%">
                            是否热门
                        </td>
                        <td width="7%" align="center">
                            编辑
                        </td>
                        <td width="7%" align="center">
                            删除
                        </td>
                    </tr>
                    <c:forEach items="${pageBean.content}" var="v" varStatus="vs">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">
                                    ${vs.count}
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                <img width="40" height="45" src="${ pageContext.request.contextPath }/${v.pimage}">
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                    ${v.pname}
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">
                                    ${v.shop_price}
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="17%">

                                <c:choose>
                                    <c:when test="${v.is_hot==1}">是</c:when>

                                    <c:otherwise>否</c:otherwise>
                                </c:choose>

                            </td>
                            <td align="center" style="HEIGHT: 22px">
                                <a href="${ pageContext.request.contextPath }/product/edit/${v.pid}/${pageBean.number}">
                                    <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0"
                                         style="CURSOR: hand">
                                </a>
                            </td>


                            <td align="center" style="HEIGHT: 22px">
                                <a href="${ pageContext.request.contextPath }/product/delete/${v.pid}">
                                    <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16"
                                         height="16" border="0" style="CURSOR: hand">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr align="center">
            <td colspan="7">
                第${pageBean.number+1}/${pageBean.totalPages}页
                <c:if test="${pageBean.hasPrevious()==true}">
                    <a href="${ pageContext.request.contextPath }/product/0">首页</a>|
                    <a href="${ pageContext.request.contextPath }/product/${pageBean.previousPageable().pageNumber}">上一页</a>|
                </c:if>
                <c:if test="${pageBean.hasNext()==true}">
                    <a href="${ pageContext.request.contextPath }/product/${pageBean.nextPageable().pageNumber}">下一页</a>|
                    <a href="${ pageContext.request.contextPath }/product/${pageBean.totalPages-1}">尾页</a>|
                </c:if>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>

