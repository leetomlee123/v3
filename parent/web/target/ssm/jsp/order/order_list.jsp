<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
    <script type="text/javascript">
        function httpPost(URL, PARAMS) {
            var temp = document.createElement("form");
            temp.action = URL;
            temp.method = "post";
            temp.style.display = "none";

            for (var x in PARAMS) {
                var opt = document.createElement("textarea");
                opt.name = x;
                opt.value = PARAMS[x];
                temp.appendChild(opt);
            }

            document.body.appendChild(temp);
            temp.submit();

            return temp;
        }

        function payOrder() {

            var params = {
                "oid": $("#oid").text()
            }
            httpPost("${pageContext.request.contextPath }/order/payOrder", params);
        }
    </script>
</head>

<body>


<!-- 引入header.jsp -->
<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div style="margin: 0 auto; margin-top: 10px; width: 950px;">
            <strong>我的订单</strong>
            <table class="table table-bordered" style="text-align: center;vertical-align: middle">

                <c:forEach items="${orderList }" var="order">

                    <tbody>
                    <tr class="success">
                        <th colspan="5">

                            订单编号:<span
                                id="oid">${order.oid }</span>&nbsp;&nbsp;${order.state==0?"<a onClick='payOrder()'>去付款</a>":"已付款" }
                        </th>
                    </tr>
                    <a href="order_list.jsp"></a>
                    <tr class="warning">
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>

                        <th>数量</th>
                        <th>小计</th>
                    </tr>

                    <c:forEach items="${order.orderItems }" var="orderItem">

                        <tr class="active">
                            <td width="60" width="40%">
                                <img src="${pageContext.request.contextPath }/${orderItem.product.pimage}" width="70"
                                     height="60">
                            </td>
                            <td width="30%"><a target="_blank">${orderItem.product.pname}</a></td>
                            <td width="20%">￥${orderItem.product.shop_price}</td>
                            <td width="10%">${orderItem.count}</td>
                            <td width="15%"><span class="subtotal">￥${orderItem.subtotal}</span></td>
                            </td>
                        </tr>

                    </c:forEach>


                    </tbody>

                </c:forEach>

            </table>
        </div>
    </div>

</div>

<!-- 引入footer.jsp -->
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>

</html>