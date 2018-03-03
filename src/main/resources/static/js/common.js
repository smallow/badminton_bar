function httpFormPost(url, args) {
    var body = $(document.body),
        form = $("<form method='post'></form>"),
        input;
    form.attr({"action": url});
    $.each(args, function (key, value) {
        input = $("<input type='hidden'>");
        input.attr({"name": key});
        input.val(value);
        form.append(input);
    });

    form.appendTo(document.body);
    form.submit();
    document.body.removeChild(form[0]);
}

function startAjax(msg) {
    $("#mask").show();
    $("#maskMsg").html(msg);
}

function endAjax() {
    $("#mask").hide();
}




/*function createTable(tableId,tableOptions,param,pageable) {
 var table = $("#" + tableId);
 var tableThead = $("<thead><tr></tr></thead>");
 var thead=tableThead.children("tr");
 var columns = tableOptions.columns;
 if (columns) {
 $.each(columns, function (index, val) {
 var tr = $("<th>" + val.title + "</th>");
 thead.append(tr);
 });
 }
 table.append(tableThead);
 table.append($("<tbody ></tbody>"));
 var ajaxMessage = (tableOptions.ajaxMessage == undefined) ? "正在加载数据..." : tableOptions.ajaxMessage;
 startAjax(ajaxMessage);
 $.post(tableOptions.url, param, function (data) {
 endAjax();
 loadData(table,columns,data.data.content);
 if(pageable){
 genPagination("groupPagination", data.data.currentPage, data.data.pageSize, data.data.totalPage, data.data.totalElements);
 }
 }, 'json');
 }

 function loadData(table,columns,data) {
 var tbody=table.children("tbody");
 if (columns) {
 $.each(data, function (index, val) {
 var tr=$("<tr></tr>");
 $.each(columns,function (index1, val1) {
 var fieldValue=val[val1.field];
 var tmp="";
 if(val1.formatter){
 tmp=val1.formatter(fieldValue,val,index);
 }else{
 tmp=(fieldValue==undefined)?"":fieldValue;
 }
 var td=$("<td align='"+val1.align+"'>"+tmp+"</td>");
 tr.append(td);
 });
 tbody.append(tr);
 });
 }
 }*/

