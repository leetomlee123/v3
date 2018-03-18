<%--
  Created by IntelliJ IDEA.
  User: name
  Date: 2018/3/9
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付成功</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script typet="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <script language="javascript">

        $(document).ready(function () {
            function jump(count) {
                window.setTimeout(function () {
                    count--;
                    if (count > 0) {
                        $('#num').attr('innerHTML', count);
                        jump(count);
                    } else {
                        var temp=location.href.split("/");

                        location.href = temp[0]+"//"+temp[2];
                    }
                }, 1000);
            }

            jump(3);
        });

    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class=".col-md-4"><span style="color:green">支付成功</span><br/></div>
    </div>

</div>
</body>
</html>
