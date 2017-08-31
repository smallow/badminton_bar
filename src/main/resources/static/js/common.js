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