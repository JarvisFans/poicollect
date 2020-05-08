<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="Java" contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>POI查询</title>
</head>
<body>
<h1>地点检索</h1>
<s:form action="placeSearchInit" theme="simple">
    <table>
        <tr>
            <td><s:label>检索关键字</s:label></td>
            <td><s:textfield id="query" name="psf.query"/>*</td>
            <td>详见附录的偏好图</td>
        </tr>
        <tr>
            <td><s:label>检索偏好</s:label></td>
            <td><s:textfield id="tag" name="psf.tag"/></td>
            <td>详见附录的偏好图</td>
        </tr>
        <tr>
            <td><s:label>地名</s:label></td>
            <td><s:textfield id="region" name="psf.region"/>*</td>
            <td>例：北京，海淀区，全国，等</td>
        </tr>
        <tr>
            <td colspan="3"><s:submit value="提交" />
            <s:reset value="重填" /></td>
        </tr>
    </table>
    <img src="${pageContext.request.contextPath}/images/tagtable.PNG"  alt=""/>

</s:form>
</body>
</html>
