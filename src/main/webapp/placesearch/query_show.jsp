<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="Java" contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>POI查询</title>
</head>
<body>
<table width="80%" align="center">
    <tr>
        <th colspan="6" align="left">合计<s:property value="psf.total"/></th>
        <th align="right"><s:form name="placeSearchDownload" action="placeSearchDownload" method="post">
            <s:hidden name="psf.query"/>
            <s:hidden name="psf.tag"/>
            <s:hidden name="psf.region"/>
            <input type="submit" value="Excel下载"/>
        </s:form></th>
    </tr>
    <tr>
        <th>所属省份</th>
        <th>所属城市</th>
        <th>所属区县</th>
        <th>POI名称</th>
        <th>地址</th>
        <th>经度</th>
        <th>纬度</th>
    </tr>
    <s:iterator value="psf.responseList" id="resp">
        <tr>
            <td><s:property value="#resp.province"/></td>
            <td><s:property value="#resp.city"/></td>
            <td><s:property value="#resp.area"/></td>
            <td><s:property value="#resp.name"/></td>
            <td><s:property value="#resp.address"/></td>
            <td><s:property value="#resp.lng"/></td>
            <td><s:property value="#resp.lat"/></td>
        </tr>
    </s:iterator>

</table>


</body>
</html>