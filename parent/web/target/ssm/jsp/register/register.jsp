<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="../../css/bootstrap.min.css" type="text/css"/>
<script src="../../js/jquery-1.11.3.min.js" type="text/javascript"></script>
<!-- 引入表单校验jquery插件 -->
<script src="../../js/jquery.validate.min.js" type="text/javascript"></script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="../../css/style.css" type="text/css"/>

<style>
    body {
        margin-top: 20px;
        margin: 0 auto;
    }

    .carousel-inner .item img {
        width: 100%;
        height: 300px;
    }

    font {
        color: #3164af;
        font-size: 18px;
        font-weight: normal;
        padding: 0 10px;
    }

    .error {
        color: red
    }
</style>


<script type="text/javascript">

    //自定义校验规则
    $.validator.addMethod(
        //规则的名称
        "checkUsername",
        //校验的函数
        function (value, element, params) {

            //定义一个标志
            var flag = false;

            //value:输入的内容
            //element:被校验的元素对象
            //params：规则对应的参数值
            //目的：对输入的username进行ajax校验
            $.ajax({
                "async": false,
                "url": "${pageContext.request.contextPath}/user/checkUserName?s=" + new Date().getTime(),
                "data": {"userName": value},
                "dataType": "json",
                "success": function (data) {
                    if (data.code == 'true') {
                        flag = true;
                    }
                }
            });
            //返回false代表该校验器不通过
            return !flag;
        }
    );
    $.validator.addMethod("checkPhone", function (value, element) {
        var length = value.length;

        var mobile = /^1[3|4|5|8][0-9]\d{8}$/;
        var tel = /^d{3,4}-?d{7,9}$/;
        return this.optional(element) || (tel.test(value) || mobile.test(value));
    }, "请输入正确的电话号码");


    $(function () {
        $("#register").validate({
            rules: {
                "username": {
                    "required": true,
                    "checkUsername": true
                },
                "password": {
                    "required": true,
                    "rangelength": [6, 12]
                },
                "repassword": {
                    "required": true,
                    "rangelength": [6, 12],
                    "equalTo": "#password"
                },
                "email": {
                    "required": true,
                    "email": true
                },

                "sex": {
                    "required": true
                },
                "telephone": {
                    "required": true,
                    "checkPhone": true
                }
            },
            messages: {
                "username": {
                    "required": "用户名不能为空",
                    "checkUsername": "用户名已存在"
                },
                "password": {
                    "required": "密码不能为空",
                    "rangelength": "密码长度6-12位"
                },
                "repassword": {
                    "required": "密码不能为空",
                    "rangelength": "密码长度6-12位",
                    "equalTo": "两次密码不一致"
                },
                "email": {
                    "required": "邮箱不能为空",
                    "email": "邮箱格式不正确"
                },
                "telephone": {
                    "required": "电话不能为空",
                    "telephone": "邮箱格式不正确"
                }
            }
        });
    });

</script>

</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="/jsp/common/header.jsp"></jsp:include>

<div class="container"
     style="width: 100%; background: url('../../image/regist_bg.jpg');">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8"
             style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
            <font>会员注册</font>
            <form id="register" class="form-horizontal" action="${pageContext.request.contextPath }/user/register"
                  method="post" style="margin-top: 5px;">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="confirmpwd" name="repassword"
                               placeholder="请输入确认密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-6">
                        <input type="email" class="form-control" id="inputEmail3" name="email"
                               placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">Telephone</label>
                    <div class="col-sm-6">
                        <input type="tel" class="form-control" id="phone" name="telephone"
                               placeholder="Telephone">
                    </div>
                </div>

                <div class="form-group">
                    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="usercaption" name="name"
                               placeholder="请输入姓名">
                    </div>
                </div>
                <div class="form-group opt">
                    <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-6">
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="sex1" value="male">男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="sex2" value="female">女
                        </label>
                        <label class="error" for="sex" style="display:none ">您没有第三种选择</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="date" class="col-sm-2 control-label">出生日期</label>
                    <div class="col-sm-6">
                        <input type="date" class="form-control" name="birthday">
                    </div>
                </div>

                <div class="form-group">
                    <label for="date" class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="checkCode">

                    </div>
                    <div class="col-sm-2">
                        <a href="javascript:void(0);" onclick="myReload()"><img
                                src="${pageContext.request.contextPath}/index/validateCode"
                                id="CreateCheckCode"
                                align="middle">
                        </a>
                    </div>

                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <p class="col-lg-10" style="color: #44ffb8">${cause}</p>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit" class="btn btn-success col-lg-7"></button>
                    </div>
                    <br><br>
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="reset" class="btn btn-success col-lg-7"></input>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
<script type="text/javascript">
    function myReload() {
        $.ajax({
            url: "${pageContext.request.contextPath}/index/validateCode?noCache=" + new Date().getTime(),
            success: function () {
                $("#CreateCheckCode").attr("src", "${pageContext.request.contextPath}/index/validateCode?noCache=" + new Date().getTime());
            }
        });
    }


</script>
</body>
</html>




