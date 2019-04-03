$(document).ready(function () {
    console.log(" firs block =");

    $(".editor-header a").click(function (e) {
        e.preventDefault();

        var _val = $(this).data("role"),
            _sizeValIn = parseInt($(this).data("size-val") + 1),
            _sizeValRe = parseInt($(this).data("size-val") - 1),
            _size = $(this).data("size");
        if (_size == "in-size") {
            document.execCommand(_val, false, _sizeValIn + "px");
        } else {
            document.execCommand(_val, false, _sizeValRe + "px");
        }
    });
});

$(document).ready(function () {
    //returns true if string is Null, UnDefined, Empty or Blank
    var testNullUnDefinedEmptyOrBlank = function (str) {
        var pattern = /^[\s]+$/;
        return (!str || str.size === 0 || pattern.test(str))
    };

    var $text = $("#text"),
        $addCommentButton = $("input[type='submit']"),
        $listComment = $(".list-comments"),
        $loading = $(".loading"),
        _data,
        $totalCom = $(".total-comment");

    $totalCom.text($(".list-comments > div").length);

    $($addCommentButton).off();
    $($addCommentButton).click(function () {
        console.log("$text.html() =" + $text.html() + ".");
        console.log("$text.html().trim() =" + $text.html().trim() + ".");

        if (testNullUnDefinedEmptyOrBlank($text.html())) {
            alert("Empty comment isn't comment ;-)");
            $text.focus();
        } else if ($text.html().length > 5120) {
            alert("Comment can contain not more than 5120 symbols.");
        } else {
            _data = $text.html();

            $.ajax({
                type: 'POST',
                url: window.location.origin + '/rest/add-comment',
                dataType: 'json',
                data: {
                    comment: _data
                },
                success: function (data) {
                    $.ajax({
                        type: 'GET',
                        url: window.location.origin + '/rest/get-comments',
                        dataType: 'json',
                        success: function (data) {
                            var $parent = $("#comment-list");
                            $parent.empty();

                            data.forEach(function (t) {
                                var dateLine = "<div>" + t.date + "</div>";
                                var commentLine = "<div>" + t.comment + "</div>";

                                var item = "<tr><td id=\"" + t.id + "-comment-date\">" +
                                    dateLine + commentLine +
                                    "</td></tr>";

                                $parent.append(item);
                            });

                        }
                    });
                }
            });

            $loading.show().fadeOut(300);
            $listComment.append("<div>" + _data + "</div>");
            $text.html("");
            $totalCom.text($(".list-comments > div").length);

            return false;
        }
    });
});