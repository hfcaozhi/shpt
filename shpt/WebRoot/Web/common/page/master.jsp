<!-- 基础页面 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="<%=request.getContextPath()%>" />
<!-- 图片路径 -->
<c:set var="ctx_member" value='<%=request.getContextPath()+"/images/member"%>' />

<script type="text/javascript">
 var ctx = "${ctx}"; //js中存放当前页面的root路径方便调用
</script>

