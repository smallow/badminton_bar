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

function genPagination(parentid,currentPage,pageSize,totalPage,totalElements) {
    $("#"+parentid).html("");
    var prePage=null;
    if(currentPage<=1){
        prePage=$("<li class='disabled'><a href='#'>上一页</a></li>");
    }else{
        prePage=$("<li><a href='javascript:void(0);' onclick=\"pagingImpl('"+(currentPage-1)+"','"+pageSize+"')\">上一页</a></li>");
    }
    $("#"+parentid).append(prePage);
    for(var i=1;i<=totalPage;i++){
        var li=null;
        if(currentPage==i){
            li=("<li class='disabled'><a href='#'>"+i+"</a></li>");
        }else{
            li=("<li><a href='javascript:void(0);' onclick=\"pagingImpl('"+i+"','"+pageSize+"')\">"+i+"</a></li>");
        }
        $("#"+parentid).append(li);
    }
    var nextPage=null;
    if(currentPage>=totalPage){
        nextPage=$("<li class='disabled'><a href='#'>下一页</a></li>");
    }else{
        nextPage=$("<li><a href='javascript:void(0);' onclick=\"pagingImpl('"+(currentPage+1)+"','"+pageSize+"')\">下一页</a></li>");
    }
    $("#"+parentid).append(nextPage);
}