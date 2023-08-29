$(() => {
    $("body").show(1500);
    $(".full-post").on("click", function (event) {
        event.preventDefault()
        $.ajax({
            type: "POST",
            url: "/blog/post",
            data: {
                id: $(this).attr("href"),
                _csrf: $(".csrf").val()
            },
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                const responseContainer = $(".dk-response")
                responseContainer.html(data[1])
                $(".dk-main-content").fadeOut(1500, function () {
                    responseContainer.fadeIn(1500)
                })
            }
        })
    })
});