<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>

<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/users/update"
      method="post">
    <input type="hidden" name="uid" value="${model.uid}"/>
    <input type="hidden" name="state" value="${model.state}"/>
    <input type="hidden" name="code" value="${model.code}"/>
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee"
           style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26">
                <strong><STRONG>编辑用户</STRONG>
                </strong>
            </td>
        </tr>

        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                用户名称：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="username" value="${model.username}"
                       id="userAction_save_do_logonName" class="bg"/>
            </td>
            <input type="hidden" name="user_category" value="${model.user_category}">
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                密码：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="password" value="${model.password}"
                       id="userAction_save_do_logonName" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                真实姓名：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="name" value="${model.name}"
                       id="userAction_save_do_logonName" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                邮箱：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="email" value="${model.email}"
                       id="userAction_save_do_logonName" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                电话：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="telephone" value="${model.telephone}"
                       id="userAction_save_do_logonName" class="bg"/>
            </td>
            <input type="hidden" name="num" value="${num}"
            <%--<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">--%>
                <%--地址：--%>
            <%--</td>--%>
            <%--<td class="ta_01" bgColor="#ffffff">--%>
                <%--<input type="datetime-local" name="birthday" value="${model.birthday}"--%>
                       <%--id="userAction_save_do_logonName" class="bg"/>--%>
            <%--</td>--%>
        </tr>

        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center"
                bgColor="#f5fafe" colSpan="4">
                <button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
                    &#30830;&#23450;
                </button>

                <%--<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>--%>
                <%--<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>--%>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</HTML>