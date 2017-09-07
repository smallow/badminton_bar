<!doctype html>
<html lang="en">
<#include "../../common/header.ftl">
<link rel="stylesheet" type="text/css" href="/badminton/css/main.css"/>
<link rel="stylesheet" type="text/css" href="/badminton/css/badminton.css"/>
<body>
<div class="container-fluid" id="publicTpl">
    <div class="row clearfix ">
    <#include "../../common/bannner.ftl">
    </div>
    <div class="row clearfix">
    <#include "../../common/nav.ftl">
        <div class="col-md-10 column" style="background-color: #FFFFFF;">
            <h1 style="padding: 50px 0 20px;">国家(地区)列表</h1>

            <form action="" method="post">
                <table class="gridtable" style="width:100%;">
                    <tr>
                        <th>国家(地区)名称：</th>
                        <td><input type="text" name="countryname"
                                   value=""/></td>
                        <th>国家(地区)代码：</th>
                        <td><input type="text" name="countrycode"
                                   value=""/></td>
                        <td rowspan="2"><input type="submit" value="查询"/></td>
                    </tr>
                    <tr>
                        <th>页码：</th>
                        <td><input type="text" name="page" value=""/></td>
                        <th>页面大小：</th>
                        <td><input type="text" name="rows" value=""/></td>
                    </tr>
                </table>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <th>
                        编号111
                    </th>
                    <th>
                        产品
                    </th>
                    <th>
                        交付时间
                    </th>
                    <th>
                        状态
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Default
                    </td>
                </tr>
                <tr class="success">
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Approved
                    </td>
                </tr>
                <tr class="error">
                    <td>
                        2
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        02/04/2012
                    </td>
                    <td>
                        Declined
                    </td>
                </tr>
                <tr class="warning">
                    <td>
                        3
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        03/04/2012
                    </td>
                    <td>
                        Pending
                    </td>
                </tr>
                <tr class="info">
                    <td>
                        4
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        04/04/2012
                    </td>
                    <td>
                        Call in to confirm
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>