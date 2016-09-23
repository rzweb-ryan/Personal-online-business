<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>华人商城 - Powered By Mango Team</title>
    <meta name="author" content="Mango Team">
    <meta name="copyright" content="Mango">
    <meta name="keywords" content="蔬菜">
    <meta name="description" content="蔬菜">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">

</head>
<body>
<script type="text/javascript">

</script>
<div class="container header">

    <%@ include file="header.jsp" %>

    <div class="container productList">
        <%@ include file="side.jsp" %>

        <div class="span18 last">

            <form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm"
                  method="get">
                <input type="hidden" id="brandId" name="brandId" value="">
                <input type="hidden" id="promotionId" name="promotionId" value="">
                <input type="hidden" id="orderType" name="orderType" value="">
                <input type="hidden" id="pageNumber" name="pageNumber" value="1">
                <input type="hidden" id="pageSize" name="pageSize" value="20">

                <div id="result" class="result table clearfix">
                    <ul>
                        <s:iterator value="pageBean.pageContent" var="c">
                        <li>
                            <a href="${pageContext.request.contextPath}/product_desc.action?pid=<s:property value="#c.pid"/>">
                                <img src="${pageContext.request.contextPath}/<s:property value="#c.image"/>"
                                     width="170" height="170" style="display: inline-block;">

											<span style='color:green'>
											 <s:property value="#c.pname"/>
											</span>

											<span class="price">
												本站价： ￥<s:property value="#c.shop_price"/>
											</span>

                            </a>
                        </li>
                        </s:iterator>
                    </ul>
                </div>
                <div class="pagination">
                    第  <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页
                    <s:if test="pageBean.page!=1">
                        <a class="firstPage" href="${pageContext.request.contextPath}/product_show.action?cid=<s:property value="cid"/>&page=1">
                            &nbsp;
                        </a>
                        <a class="previousPage" href="${pageContext.request.contextPath}/product_show.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>">
                            &nbsp;
                        </a>

                    </s:if>
                    <s:iterator begin="1" end="pageBean.totalPage" step="1" var="i">
                        <s:if test="pageBean.page==#i">
                            <span class="currentPage"><s:property value="#i"/> </span>

                        </s:if>
                        <s:else>
                            <a href="${pageContext.request.contextPath}/product_show.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>

                        </s:else>
                    </s:iterator>
                    <s:if test="pageBean.page!=pageBean.totalPage">
                    <a class="nextPage" href="${pageContext.request.contextPath}/product_show.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
                    <a class="lastPage" href="${pageContext.request.contextPath}/product_show.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
                    </s:if>
                </div>
            </form>
        </div>
    </div>
    <%@ include file="foot.jsp"%>
</body>
</html>