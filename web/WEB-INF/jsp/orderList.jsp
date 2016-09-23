<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: RZ
  Date: 6/9/16
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>订单列表</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
    <%@ include file="header.jsp" %>

    <div class="container cart">

        <div class="span24">

            <div class="step step1">
                <ul>

                    <li class="current"></li>
                    <li>您的订单列表</li>
                </ul>
            </div>

            <table>
                <tbody>
                <tr>
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <s:iterator value="orderList">
                    <tr>
                        <td colspan="5">
                            订单编号:<s:property value="oid"/>&nbsp;&nbsp;
                            总金额:<font color="#8b0000"> <s:property value="total"/></font>&nbsp;&nbsp;
                            订单状态:
                            <s:if test="state==1">
                                <font color="#8b0000">
                                    <a href="${pageContext.request.contextPath}/order_showOrder.action?oid=<s:property value="oid"/>"><font
                                            color="#8b0000">未付款</font></a>
                                </font>
                            </s:if>
                            <s:if test="state==2">
                                已付款
                            </s:if>
                            <s:if test="state==3">
                                已发货 <a href="${pageContext.request.contextPath}/order_updateState.action?oid=<s:property value="oid"/>"><font color="#dc143c">确认收货</font></a>
                            </s:if>
                            <s:if test="state==4">
                                已完成
                            </s:if>
                                <%--<s:property value="state"/>--%>
                        </td>
                    </tr>
                    <s:iterator value="orderItem">
                        <tr>
                            <td width="60">
                                <input type="hidden" name="pid" value="22"/>
                                <img src="${pageContext.request.contextPath}/<s:property value="product.image"/>"/>
                            </td>
                            <td>
                                <a target="${pageContext.request.contextPath}/product_desc.action?pid="<s:property
                                        value="product.pid"/>><s:property value="product.pname"/></a>
                            </td>
                            <td>
                                <s:property value="product.shop_price"/>
                            </td>
                            <td class="quantity" width="60">
                                <s:property value="count"/>

                            </td>
                            <td width="140">
                                <span class="subtotal">￥<s:property value="subTotal"/></span>
                            </td>
                        </tr>
                    </s:iterator>
                </s:iterator>
                </tbody>
            </table>
        </div>

    </div>
    <%@ include file="foot.jsp" %>
</body>
</html>