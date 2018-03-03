function DataGrid(id, columns, url,pageable) {
    this.id = id;
    this.columns = columns;
    this.url = url;
    this.loadMsg = "正在加载数据...";
    this.pageable = pageable;
    this.getId = function () {
        return this.id;
    }
}
DataGrid.prototype.datagrid = function () {
    var obj=this;
    var table = $("#" + obj.getId());
    var tableThead = $("<thead><tr></tr></thead>");
    var thead = tableThead.children("tr");
    var columns = obj.columns;
    if (columns) {
        $.each(columns, function (index, val) {
            var tr = $("<th>" + val.title + "</th>");
            thead.append(tr);
        });
    }
    table.append(tableThead);
    table.append($("<tbody ></tbody>"));
    ajaxPost(obj.url, {}, obj.loadMsg, function (data) {
        loadData(table, columns, data.data.content);
        if (obj.pageable) {
            genPagination("groupPagination", data.data.currentPage, data.data.pageSize, data.data.totalPage, data.data.totalElements);
        }
    });
};

DataGrid.prototype.load = function (params) {
    var obj=this;
    var table = $("#" + this.getId());
    var tbody = table.children("tbody");
    var columns = obj.columns;
    tbody.html("");
    ajaxPost(obj.url, params, obj.loadMsg, function (data) {
        loadData(table, columns, data.data.content);
        if (obj.pageable) {
            genPagination("groupPagination", data.data.currentPage, data.data.pageSize, data.data.totalPage, data.data.totalElements);
        }
    });

};

function ajaxPost(url, params, loadMsg, callback) {
    startAjax(loadMsg);
    $.post(url, params, function (data) {
        endAjax();
        callback(data);
    }, 'json');
}
function loadData(table, columns, data) {
    var tbody = table.children("tbody");
    if (columns) {
        $.each(data, function (index, val) {
            var tr = $("<tr></tr>");
            $.each(columns, function (index1, val1) {
                var fieldValue = val[val1.field];
                var tmp = "";
                if (val1.formatter) {
                    tmp = val1.formatter(fieldValue, val, index);
                } else {
                    tmp = (fieldValue == undefined) ? "" : fieldValue;
                }
                var td = $("<td align='" + val1.align + "'>" + tmp + "</td>");
                tr.append(td);
            });
            tbody.append(tr);
        });
    }
}

function genPagination(parentid, currentPage, pageSize, totalPage, totalElements) {
    $("#" + parentid).html("");
    var prePage = null;
    if (currentPage <= 1) {
        prePage = $("<li class='disabled'><a href='#'>上一页</a></li>");
    } else {
        prePage = $("<li><a href='javascript:void(0);' onclick=\"pagingImpl('" + (currentPage - 1) + "','" + pageSize + "')\">上一页</a></li>");
    }
    $("#" + parentid).append(prePage);
    for (var i = 1; i <= totalPage; i++) {
        var li = null;
        if (currentPage == i) {
            li = ("<li class='disabled'><a href='#'>" + i + "</a></li>");
        } else {
            li = ("<li><a href='javascript:void(0);' onclick=\"pagingImpl('" + i + "','" + pageSize + "')\">" + i + "</a></li>");
        }
        $("#" + parentid).append(li);
    }
    var nextPage = null;
    if (currentPage >= totalPage) {
        nextPage = $("<li class='disabled'><a href='#'>下一页</a></li>");
    } else {
        nextPage = $("<li><a href='javascript:void(0);' onclick=\"pagingImpl('" + (currentPage + 1) + "','" + pageSize + "')\">下一页</a></li>");
    }
    $("#" + parentid).append(nextPage);
}

