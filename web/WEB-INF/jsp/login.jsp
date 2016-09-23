<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>会员登录</title>

    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">


</head>
<body>

<div class="container header">

    <%@ include file="header.jsp" %>

    <div class="span24">
        <div class="tagWrap">
            <ul class="tag">
                <li class="icon"
                    style="background: url(http://storage.shopxx.net/demo-image/3.0/tag/hot.gif) right no-repeat;">
                    <a>热销</a>
                </li>
                <li class="icon"
                    style="background: url(http://storage.shopxx.net/demo-image/3.0/tag/new.gif) right no-repeat;">
                    <a>最新</a>
                </li>
            </ul>
            <div class="hotSearch">
                热门搜索:
                <a>水蜜桃</a>
                <a>西瓜</a>
                <a>紫薯</a>
                <a>大米</a>
                <a>玉米</a>
                <a>茄子</a>
                <a>辣椒</a>
                <a>圣女果</a>
                <a>鱿鱼丝</a>
            </div>
            <div class="search">

                <form id="productSearchForm" method="get">
                    <input name="keyword" class="keyword" value="商品搜索" maxlength="30">
                    <button type="submit">搜索</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function checkForm() {
        var username = document.getElementById("username").value;
        if(username=='') {
            alert("username can't be empty!");
            return false;
        }
        var password = document.getElementById("password").value;
        if(password=='') {
            alert("password can't be empty!");
            return false;
        }
    }
</script>
<div class="container login">
    <div class="span12">
        <div class="ad">
            <img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录"
                 title="会员登录">
        </div>
    </div>
    <div class="span12 last">
        <div class="wrap">
            <div class="main">
                <div class="title">
                    <strong>会员登录</strong>USER LOGIN
                </div>
                <form id="loginForm" method="post" action="${pageContext.request.contextPath}/user_login.action" novalidate="novalidate" onsubmit="return checkForm();">
                    <table>
                        <tbody>
                        <tr>
                            <th>
                                用户名:
                            </th>
                            <td>
                                <input type="text" id="username" name="username" class="text" maxlength="20">
                                <s:actionerror/>
                                <s:fielderror/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                密&nbsp;&nbsp;码:
                            </th>
                            <td>
                                <input type="password" id="password" name="password" class="text" maxlength="20"
                                       autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <th>
                                验证码:
                            </th>
                            <td>
										<span class="fieldSet">
											<input type="text" id="captcha" name="checkCode" class="text captcha"
                                                   maxlength="4" autocomplete="off"><img id="imgId"
                                                                                         class="captchaImage"
                                                                                         src="${pageContext.request.contextPath}/checkImg.action" onclick="changeImg()"
                                                                                         title="点击更换验证码">
										</span>
                            </td>
                        </tr>
                        <script type="text/javascript">
                            function changeImg() {
                                var img = document.getElementById("imgId");
                                img.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getDate();
                            }
                        </script>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <label>
                                    <input type="checkbox" id="isRememberUsername" name="isRememberUsername"
                                           value="true">记住用户名
                                </label>
                                <label>
                                    &nbsp;&nbsp;<a>找回密码</a>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <input type="submit" class="submit" value="登 录">
                            </td>
                        </tr>
                        <tr class="register">
                            <th>&nbsp;

                            </th>
                            <td>
                                <dl>
                                    <dt>还没有注册账号？</dt>
                                    <dd>
                                        立即注册即可体验在线购物！
                                        <a href="${pageContext.request.contextPath}/user_regPage.action">立即注册</a>
                                    </dd>
                                </dl>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
<%@ include file="foot.jsp"%>
</body>
</html>