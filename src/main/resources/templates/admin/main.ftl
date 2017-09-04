<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/badminton/css/main.css"/>
    <title>Document</title>
</head>

<body>
<div class="container-fluid" id="publicTpl">
    <div class="row clearfix ">
        <div class="col-md-12 column" id="publicHeader">
            <nav class="navbar navbar-default headerContainer" role="navigation">
                <div class="navbar-header ">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
                    <a class="navbar-brand navLeftLogo" href="#"><span class="glyphicon glyphicon-tasks"></span>Admin</a>
                </div>

                <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="javascript:;"><span style="color: #2cb7ca;" class="glyphicon glyphicon-user"></span>欢迎，XXXX回来</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">设置<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">退出</a>
                                </li>
                                <li>
                                    <a href="#">切换账号</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-2 column" id="leftNav">
            <div class="panel-group" id="panel-29368">
                <div class="panel panel-default ">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-29368" href="#panel-element-670670"><span class="glyphicon glyphicon-th-list"></span>系统管理</a>
                    </div>
                    <div id="panel-element-670670" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="/badminton/admin/group/list" class="list-group-item">群组管理</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-29368" href="#panel-element-90452"><span class="glyphicon glyphicon-th-list"></span>会员管理</a>
                    </div>
                    <div id="panel-element-90452" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="/badminton/admin/group/list" class="list-group-item">会员管理</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-29368" href="#panel-element-90453"><span class="glyphicon glyphicon-th-list"></span>活动管理</a>
                    </div>
                    <div id="panel-element-90453" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="/badminton/admin/group/list" class="list-group-item">活动管理</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-29368" href="#panel-element-90454"><span class="glyphicon glyphicon-th-list"></span>报表查询</a>
                    </div>
                    <div id="panel-element-90454" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="list-group">
                                <a href="/badminton/admin/group/list" class="list-group-item">报表查询</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10 column" style="background-color: #FFFFFF;">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        编号
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