<!-- jquery必须引用页 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String root = request.getContextPath(); %>
<script type="text/javascript">
 var root = "<%=root%>"; //js中存放当前页面的root路径方便调用
</script>
<script type="text/javascript" src="<%=root%>/Web/common/js/jquery-1.4.4.min.js"></script>