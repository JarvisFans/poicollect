<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="Java" contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>高德POI查询</title>
</head>
<body>
<table width="100%" align="center">
    <tr>
        <th colspan="6" align="left">合计<s:property value="ampf.count"/></th>
        <th align="right"><s:form name="poiDownload" action="aMapPoiDownload" method="post">
            <s:hidden name="ampf.filename"/>
            <input type="submit" value="Excel下载"/>
        </s:form></th>
    </tr>
    <tr>
        <th>所属省份</th>
        <th>所属城市</th>
        <th>所属区县</th>
        <th>POI名称</th>
        <th>类别</th>
        <th>地址</th>
        <th>经度</th>
        <th>纬度</th>
    </tr>
    <s:iterator value="ampf.responseList" id="resp">
        <tr>
            <td><s:property value="#resp.pname"/></td>
            <td><s:property value="#resp.cityname"/></td>
            <td><s:property value="#resp.adname"/></td>
            <td><s:property value="#resp.name"/></td>
            <td><s:property value="#resp.type"/></td>
            <td><s:property value="#resp.address"/></td>
            <td><s:property value="#resp.lng"/></td>
            <td><s:property value="#resp.lat"/></td>
        </tr>
    </s:iterator>

</table>
</body>
</html>