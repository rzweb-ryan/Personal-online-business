<%--
  Created by IntelliJ IDEA.
  User: RZ
  Date: 6/9/16
  Time: 12:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<div class="span6">
    <div class="hotProductCategory">
        <s:iterator value="categoryList" var="c">
            <dl>
                <dt>
                    <a href="${pageContext.request.contextPath}/product_show.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/> </a>
                </dt>
                <s:iterator value="#c.categorySecond" var="cs">

                    <dd>
                        <a href="${pageContext.request.contextPath}/product_show2.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname"/></a>
                    </dd>

                </s:iterator>
            </dl>
        </s:iterator>
    </div>
</div>
