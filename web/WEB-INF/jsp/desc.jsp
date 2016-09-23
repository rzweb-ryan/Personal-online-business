<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>商品详情</title>
    <meta name="author" content="Mango Team">
    <meta name="copyright" content="Mango">
    <meta name="keywords" content="京华亿家--大冬瓜">
    <meta name="description" content="京华亿家--大冬瓜">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container header">
    <%@ include file="header.jsp" %>
    <div class="container productContent">
        <%@ include file="side.jsp" %>
        <div class="span18 last">
            <form action="${pageContext.request.contextPath}/cart_add.action" method="post">
                <input type="hidden" name="pid" value="<s:property value="model.pid"/>">
            <div class="productImage">
                <a title="" style="outline-style: none; text-decoration: none;" id="zoom"
                   href="<s:property value="model.image"/> "
                   rel="gallery">
                    <div class="zoomPad"><img style="opacity: 1;" title="" class="medium"
                                              src="<s:property value="model.image"/>">
                        <div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
                             class="zoomPup"></div>
                        <div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
                             class="zoomWindow">
                            <div style="width: 368px;" class="zoomWrapper">
                                <div style="width: 100%; position: absolute; display: none;"
                                     class="zoomWrapperTitle"></div>
                                <div style="width: 0%; height: 0px;" class="zoomWrapperImage"><img
                                        src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg"
                                        style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;">
                                </div>
                            </div>
                        </div>
                        <div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;"
                             class="zoomPreload">Loading zoom
                        </div>
                    </div>
                </a>

            </div>
            <div class="name"><s:property value="model.pname"/></div>
            <div class="sn">
                <div>编号：<s:property value="model.pid"/></div>
            </div>
            <div class="info">
                <dl>
                    <dt>本站价:</dt>
                    <dd>
                        <strong>￥：<s:property value="model.shop_price"/>元/份</strong>
                        参 考 价：
                        <del>￥：<s:property value="model.market_price"/>元/份</del>
                    </dd>
                </dl>
                <dl>
                    <dt>促销:</dt>
                    <dd>
                        <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
                    </dd>
                </dl>
                <dl>
                    <dt></dt>
                    <dd>
                        <span>    </span>
                    </dd>
                </dl>
            </div>
            <div class="action">
                <script type="text/javascript">
                    function countNum() {
                        var c = document.getElementById("count");
                        if(c.value<'1') c.value=1;
                    }
                    function incNum() {
                        document.getElementById("count").value++;
                    }
                    function decNum() {
                        var c = document.getElementById("count");
                        if(c.value!='1')
                        c.value -=1;
                    }
                </script>
                <dl class="quantity">
                    <dt>购买数量:</dt>
                    <dd>
                        <input id="count" name="count" value="1" maxlength="4" onpaste="return false;"
                               type="text" onblur="countNum()">
                        <div>
                            <span id="increase" class="increase" onclick="incNum()">&nbsp;</span>
                            <span id="decrease" class="decrease" onclick="decNum()">&nbsp;</span>
                        </div>
                    </dd>
                    <dd>
                        件
                    </dd>
                </dl>
                <div class="buy">
                    <input id="addCart" class="addCart" value="加入购物车" type="submit">

                </div>
            </div>
            <div id="bar" class="bar">
                <ul>
                    <li id="introductionTab">
                        <a href="#introduction">商品介绍</a>
                    </li>

                </ul>
            </div>

            <div id="introduction" name="introduction" class="introduction">
                <div class="title">
                    <strong>商品介绍</strong>
                </div>
                <div>
                    <img src="<s:property value="model.image"/>">
                </div>
                <s:property value="model.pdesc"/>
            </div>

        </form>

        </div>
    </div>
    <%@ include file="foot.jsp" %>
</body>
</html>