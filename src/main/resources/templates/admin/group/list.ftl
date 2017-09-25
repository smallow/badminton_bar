<!doctype html>
<html lang="en">
<#include "../../common/header.ftl">
<link rel="stylesheet" type="text/css" href="/badminton/css/badminton.css"/>
<link rel="stylesheet" type="text/css" href="/badminton/css/mask.css"/>
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
                        <td rowspan="2"><button type="button" class="btn btn-sm btn-info" onclick="go()" >查询</button></td>
                    </tr>
                    <tr>
                        <th>群管理员姓名：</th>
                        <td><input type="text" name="groupManagerName" id="groupManagerName" value=""/></td>
                        <th>群状态：</th>
                        <td>
                            <select id="groupStatus"  name="groupStatus" style="width: 200px;">
                                <option value="0">申请中</option>
                                <option value="1">已开通</option>
                                <option value="2">已注销</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <table class="table table-bordered table-hover" id="group">
                    <thead>
                    <tr>
                        <th>
                            群组名称
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
                </table>
                <div class="col-md-12 column">
                    <a href="/badminton/admin/group/add" data-toggle="modal" data-target="#myModal" class="btn  btn-warning pull-left" style="margin:20px 0;display: inline-block;" >新建群组</a>
                    <ul class="pagination pull-right" id="groupPagination">
                    </ul>
                </div>
        </div>
    </div>
</div>
<#include "../../common/mask.ftl"/>
<div id="myModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

        </div>
    </div>
</div>
<script type="text/javascript" src="/badminton/js/common.js"></script>
<script>
    $(function () {
        query(getQueryParams(),1,10);
        $("#myModal").on("hidden.bs.modal", function() {
            $(this).removeData("bs.modal");
        });
    });

    function go() {
        query(getQueryParams(),reset(),10);
    }
    function query(param,page,size) {
        param.page=page;
        param.pageSize=size;
        startAjax("正在加载数据....");
        $.post("/badminton/admin/group/list",param,function (data) {
            endAjax();
            loadData(data);
        },'json');
    }
    function loadData(data) {
        if(data.code==0){
            $.each(data.data.content,function (index, item) {
                var tr=$("<tr><td>"+item.groupName+"</td><td>"+item.groupManagerName+"</td><td>"+item.groupManagerIdNumber+"</td><td>"+item.groupManagerPhone+"</td><td>"+item.groupStatus+"</td><td>"+item.createTime+"</td><td> <a href='javascript:void(0);' onclick=\"modify('"+item.groupId+"')\">修改</a> | <a href='javascript:void(0);' onclick=\"del('"+item.groupId+"')\">删除</a></td></tr>")
                $("#groupBody").append(tr);
            });
            genPagination("groupPagination",data.data.currentPage,data.data.pageSize,data.data.totalPage,data.data.totalElements);
        }
    }

    function getQueryParams() {
        var groupName=$("#groupName").val();
        var groupManagerName=$("#groupManagerName").val();
        var groupManagerPhone=$("#groupManagerPhone").val();
        var groupStatus=$("#groupStatus").val();
        var param={
            groupName:groupName,
            groupManagerPhone:groupManagerPhone,
            groupManagerName:groupManagerName,
            groupStatus:groupStatus
        };
        return param;
    }
    function reset() {
        $("#groupBody").html("");
        return 1;
    }
    function pagingImpl(page, size) {
        query(getQueryParams(),page,size);
    }

    function modify(groupId) {
        $("#myModal").modal({
            remote: "/badminton/admin/group/add?groupId="+groupId
        });
    }

    function del(groupId) {

    }
</script>
</body>
</html>