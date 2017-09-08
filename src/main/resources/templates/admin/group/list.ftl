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
                    <#list groupPage.getContent() as group>
                    <tr>
                        <td>${group.groupName}</td>
                        <td>${group.groupManagerName}</td>
                        <td>${group.groupManagerIdNumber}</td>
                        <td>${group.groupManagerPhone}</td>

                        <td>${group.getGroupStatusEnum().msg}</td>
                        <td>${group.createTime}</td>
                        <td>修改 | 删除</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
<#include "../../common/mask.ftl"/>
<script type="text/javascript" src="/badminton/js/common.js"></script>

<script>
    var page=1;
    $(function () {
        //query(getQueryParams(),1);
    });

    function go() {
        page=1;
        query(getQueryParams(),page);
    }
    function query(param,page) {
        param.page=page;
        param.pageSize=10;
        startAjax("正在加载数据....");
        $.post("/badminton/admin/group/list",param,function (data) {
            endAjax();
            loadData(data);
        },'json');
    }
    function loadData(data) {
        if(data.code==0){
            console.log(data.data.content);
            $.each(data.data.content,function (index, item) {
                console.log(item+":"+index);
                var tr=$("<tr><td>"+item.groupName+"</td><td>"+item.groupManagerName+"</td><td>"+item.groupManagerIdNumber+"</td><td>"+item.groupManagerPhone+"</td><td>"+item.getBacmintonGroupCheckEnum().message+"</td><td>"+item.getBacmintonGroupCheckEnum().message+"</td><td>修改 | 删除</td></tr>")
                $("#groupBody").append(tr);
            });

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
</script>
</body>
</html>