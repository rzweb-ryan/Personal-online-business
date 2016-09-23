<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: RZ
  Date: 6/9/16
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>购物车</title>
    <meta name="author" content="Mango Team">
    <meta name="copyright" content="Mango">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container header">
    <%@include file="header.jsp" %>
    <div class="container cart">
        <div class="span24">
            <div class="step step1">
                <s:if test="cart.cartItems!=null&&cart.cartItems.size()!=0">
                    购物车详情
                </s:if>
                <s:else>
                    您的购物车为空
                </s:else>
            </div>
            <s:if test="cart.cartItems!=null&&cart.cartItems.size()!=0">
                <table>
                    <tbody>
                    <tr>
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                        <th>操作</th>
                    </tr>
                    <s:iterator value="cart.cartItems" var="c">

                        <tr>
                            <td width="60">
                                <input type="hidden" name="<s:property value="#c.product.pid"/>" value="22">
                                <img src="${pageContext.request.contextPath}/<s:property value="#c.product.image"/>">
                            </td>
                            <td>
                                <a target="${pageContext.request.contextPath}/product_desc.action?pid=<s:property value="#c.product.pid"/>"> <s:property value="#c.product.pname"/></a>
                            </td>
                            <td>
                                ￥<s:property value="#c.product.shop_price"/>
                            </td>
                            <script type="text/javascript">
                                function changeNum(pid) {
                                    var res = document.getElementById("count").value;

                                    window.location.href="${pageContext.request.contextPath}/cart_changeNum.action?pid="+pid+"&count="+res;
                                }
                                function inc(pid) {
                                    var res = document.getElementById("count").value;
                                    var r = parseInt(res)+1;
                                    window.location.href="${pageContext.request.contextPath}/cart_changeNum.action?pid="+pid+"&count="+r;

                                }
                                function dec(pid) {
                                    var res = document.getElementById("count").value;
                                    if(res!=1){
                                        var r = parseInt(res)-1;
                                        window.location.href="${pageContext.request.contextPath}/cart_changeNum.action?pid="+pid+"&count="+r;
                                    }
                                }
                            </script>
                            <td class="quantity" width="60">
                                <input type="text" name="count" id="count" value="<s:property value="#c.count"/>" maxlength="4"
                                       onpaste="return false;" onblur="changeNum(<s:property value="#c.product.pid"/>)">
                                <div>
                                    <span class="increase" onclick="inc(<s:property value="#c.product.pid"/>)">&nbsp;</span>
                                    <span class="decrease" onclick="dec(<s:property value="#c.product.pid"/>)">&nbsp;</span>
                                </div>
                            </td>
                            <td width="140">
                                <span class="subtotal">￥<s:property value="#c.subTotal"/></span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/cart_delete.action?pid=<s:property value="#c.product.pid"/>"
                                   class="delete">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                <dl id="giftItems" class="hidden" style="display: none;">
                </dl>
                <div class="total">
                    <em id="promotion"></em>
                    <em>
                        登录后确认是否享有优惠
                    </em>
                    赠送积分: <em id="effectivePoint"><s:property value="cart.total"/></em>
                    商品金额: <strong id="effectivePrice">￥<s:property value="cart.total"/>元</strong>
                </div>
                <div class="bottom">
                    <a href="${pageContext.request.contextPath}/cart_clear.action" id="clear" class="clear">清空购物车</a>
                    <a href="${pageContext.request.contextPath}/order_place.action" id="submit" class="submit">提交订单</a>
                </div>
            </s:if>
            <s:else>
                <div class="bottom">
                    <a href="${pageContext.request.contextPath}/index.action" id="back" class="clear">返回首页</a>
                </div>
            </s:else>
        </div>
    </div>
    <%@ include file="foot.jsp" %>
</body>
</html>
