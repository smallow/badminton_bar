<!doctype html>
<html lang="en">
<#include "../../common/header.ftl">
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/badminton.css"/>
<link rel="stylesheet" type="text/css" href="${request.contextPath}/css/mask.css"/>
<body>
<div class="container-fluid" id="publicTpl">
    <div class="row clearfix ">
    <#include "../../common/bannner.ftl">
    </div>
    <div class="row clearfix">
    <#include "../../common/nav.ftl">
        <div class="col-md-10 column" style="background-color: #FFFFFF;">
            <h2 style="padding: 10px 0 10px;">群组管理查询</h2>
            <table class="gridtable" style="width:100%;">
                <tr>
                    <th>群组名称：</th>
                    <td><input type="text" name="groupName" id="groupName"
                               value=""/></td>
                    <th>群管理员手机号：</th>
                    <td><input type="text" name="groupManagerPhone" id="groupManagerPhone"
                               value=""/></td>
                    <td rowspan="2">
                        <button type="button" class="btn btn-sm btn-info" onclick="go()">查询</button>
                    </td>
                </tr>
                <tr>
                    <th>群管理员姓名：</th>
                    <td><input type="text" name="groupManagerName" id="groupManagerName" value=""/></td>
                    <th>群状态：</th>
                    <td>
                        <select id="groupStatus" name="groupStatus" style="width: 200px;">
                            <option value="0">申请中</option>
                            <option value="1">已开通</option>
                            <option value="2">已注销</option>
                        </select>
                    </td>
                </tr>
            </table>
            <table class="table table-bordered table-hover" id="group"></table>
            <!--<table class="table table-bordered table-hover" id="group">
                <thead>
                <tr>
                    <th>
                        群组名称
                    </th>
                    <th>
                        所在省份
                    </th>
                    <th>
                        所在城市(及地区)
                    </th>
                    <th>
                        规模(人)
                    </th>
                    <th>
                        群管理员名称
                    </th>
                    <th>
                        管理员证件号码
                    </th>
                    <th>
                        管理员手机号
                    </th>

                    <th>
                        群状态
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody id="groupBody">
                </tbody>
            </table>-->
            <div class="col-md-12 column">
                <a href="${request.contextPath}/admin/group/add" data-toggle="modal" data-target="#myModal"
                   class="btn  btn-warning pull-left" style="margin:20px 0;display: inline-block;">新建群组</a>
                <ul class="pagination pull-right" id="groupPagination">
                </ul>
            </div>
        </div>
    </div>
</div>
<#include "../../common/mask.ftl"/>
<div id="myModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
    </div>
</div>
<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/groupManage.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/sui.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/sui-cascade.js"></script>
<script>
    var table1 = null;
    var _context="";
    $(function () {
        _context='${request.contextPath}';
        $("#myModal").on("hidden.bs.modal", function () {
            $(this).removeData("bs.modal");
            if(webSocket){
                webSocket.close();
                clearInterval(scanFlag);
            }

        });
        groupGridInit();
    });

    function groupGridInit() {
        var obj = {
            url: _context+"/admin/group/list",
            columns: [
                {field: "groupName", title: "群组名称", width: "", height: "", align: "left"},
                {field: "province", title: "省份", width: "", height: "", align: "left"},
                {field: "city", title: "城市", width: "", height: "", align: "left"},
                {field: "area", title: "地区", width: "", height: "", align: "left"},
                {field: "groupScale", title: "规模(人)", width: "", height: "", align: "left"},

                {field: "groupManagerName", title: "群管理员名称", width: "", height: "", align: "left"},
                {field: "groupManagerIdNumber", title: "管理员证件号码", width: "", height: "", align: "left"},
                {field: "groupManagerPhone", title: "管理员手机号", width: "", height: "", align: "left"},
                {field: "groupStatus", title: "状态", width: "", height: "", align: "left"},
                {field: "createTime", title: "创建时间", width: "", height: "", align: "left"},
                {
                    field: "operation",
                    title: "操作",
                    width: "",
                    height: "",
                    align: "left",
                    formatter: function (value, row, index) {
                        return "<a href='javascript:void(0);' onclick=\"modify('" + row.groupId + "')\">修改</a>&nbsp;|&nbsp;"
                                + "<a href='javascript:void(0);' onclick=\"del('" + row.groupId + "')\">删除</a>";
                    }
                }
            ],
            pageable:true
        };
        table1 = new DataGrid('group', obj.columns, obj.url,obj.pageable);
        table1.datagrid();
    }
    function go() {
        var params = getQueryParams();
        params.page = 1;
        params.pageSize = 10;
        table1.load(params);
    }
    function getQueryParams() {
        var groupName = $("#groupName").val();
        var groupManagerName = $("#groupManagerName").val();
        var groupManagerPhone = $("#groupManagerPhone").val();
        var groupStatus = $("#groupStatus").val();
        var param = {
            groupName: groupName,
            groupManagerPhone: groupManagerPhone,
            groupManagerName: groupManagerName,
            groupStatus: groupStatus
        };
        return param;
    }

    function pagingImpl(page, size) {
        var params = getQueryParams();
        params.page = page;
        params.pageSize = size;
        table1.load(params);
    }

    function modify(groupId) {
        $("#myModal").modal({
            remote: "${request.contextPath}/admin/group/add?groupId=" + groupId
        });
    }

    function del(groupId) {

    }
</script>
</body>
</html>