$(() => {
    $("body").show(1500);

    $(".add-post").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerAddPost()
            },
        })
    })

    const listenerAddPost = () => {
        $("#dk-my-form").on("submit", function (event) {
            event.preventDefault();
            if (!$(".postTitle").val() || !$(".imageFile").val() || !$(".textOfPost").val()) {
                $(".validate").text("all filed must be no empty")
                return
            }
            if ($(".imageFile")[0].files[0].size >= 1048576) {
                $(".validate").text("file is too big max size img files 1 048 576 bytes")
                return
            }
            let thatForm = $(this)
            let formData = new FormData(thatForm.get(0));
            $.ajax({
                type: "POST",
                url: `${$(this).attr("action")}`,
                contentType: false,
                processData: false,
                data: formData,
                success: function (response) {
                    $(".validate").text(response)
                    $(".postTitle").val("")
                    $(".imageFile").val("")
                    $(".textOfPost").val("")
                }
            })
        })
    }
})