<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>纽约华人商城</title>
    <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">

    <%@ include file="header.jsp" %>


    <div class="container index">


        <div class="span24">
            <div id="hotProduct" class="hotProduct clearfix">
                <div class="title">
                    <strong>热门商品</strong>
                    <!-- <a  target="_blank"></a> -->
                </div>
                <ul class="tab">
                    <li class="current">
                        <a href="category.jsp?tagIds=1" target="_blank"></a>
                    </li>
                    <li>
                        <a target="_blank"></a>
                    </li>
                    <li>
                        <a target="_blank"></a>
                    </li>
                </ul>
                <!-- 					<div class="hotProductAd">
			<img src="${pageContext.request.contextPath}/image/a.jpg" width="260" height="343" alt="热门商品" title="热门商品">
</div> -->
                <ul class="tabContent" style="display: block;">
                    <s:iterator value="hotList">
                        <li>
                            <a href="${pageContext.request.contextPath}/product_desc.action?pid=<s:property value="pid"/>"><img
                                    src="${pageContext.request.contextPath}/<s:property value="image"/> "
                                    style="display: block;"></a>
                        </li>
                    </s:iterator>
                </ul>

                <div class="span24">
                    <div id="newProduct" class="newProduct clearfix">
                        <div class="title">
                            <strong>最新商品</strong>
                            <a target="_blank"></a>
                        </div>
                        <ul class="tab">
                            <li class="current">
                                <a href="category.jsp?tagIds=2" target="_blank"></a>
                            </li>
                            <li>
                                <a target="_blank"></a>
                            </li>
                            <li>
                                <a target="_blank"></a>
                            </li>
                        </ul>
                        <!-- 					<div class="newProductAd">
									<img src="${pageContext.request.contextPath}/image/q.jpg" width="260" height="343" alt="最新商品" title="最新商品">
						</div>
						 -->
                        <ul class="tabContent" style="display: block;">
                            <s:iterator value="newList">
                                <li>
                                    <a href="${pageContext.request.contextPath}/product_desc.action?pid=<s:property value="pid"/>"><img
                                            src="${pageContext.request.contextPath}/<s:property value="image"/>"
                                            style="display: block;"></a>
                                </li>
                            </s:iterator>

                        </ul>

                    </div>
                </div>
                <div class="span24">
                    <div class="friendLink">
                        <dl>
                            <dt>新手指南</dt>
                            <dd>
                                <a target="_blank">支付方式</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">配送方式</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">售后服务</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">购物帮助</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">蔬菜卡</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">礼品卡</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">银联卡</a>
                                |
                            </dd>
                            <dd>
                                <a target="_blank">亿家卡</a>
                                |
                            </dd>

                            <dd class="more">
                                <a>更多</a>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>

        <%@ include file="foot.jsp" %>
        </div>  </div>
</body>
</html>