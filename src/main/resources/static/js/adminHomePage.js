$(() => {
    $("body").show(1500);

    $(".contact-messages").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".script").remove();
                $("head").append(data[1]);
                $(".data").html(data[3]);
            },
        })
    })

    $(".delete").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "DELETE",
            url: "/delete",
            data: {
                id: `${$(this).attr("href")}`,
                _csrf: $(".csrf").val()
            },
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".script").remove();
                $("head").append(data[1]);
                $(".data").html(data[3]);
            },
        })
    })

    $(".users").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
            },
        })
    })
});