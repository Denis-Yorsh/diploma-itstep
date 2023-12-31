$(() => {
    $(".add-post").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr("href"),
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerAddPost()
            },
        })
    })

    $(".all-posts").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr("href"),
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerDeletePost()
            }
        })
    })

    const listenerDeletePost = () => {
        $(".delete-post").on("click", function (event) {
            event.preventDefault();
            $.ajax({
                type: "DELETE",
                url: "/post/deletePost",
                data: {
                    id: $(this).attr("href"),
                    _csrf: $(".csrf").val()
                },
                dataType: "text",
                success: function (response) {
                    let data = response.split("<!--------------------------------------------------->")
                    $(".data").html(data[1]);
                    listenerDeletePost()
                },
            })
        })
    }

    const listenerAddPost = () => {
        $("#dk-my-form").on("submit", function (event) {
            event.preventDefault();
            const textOfPost = $(".textOfPost").val()
            const imageFile = $(".imageFile")
            if (!$(".postTitle").val() || !$(imageFile).val() || !textOfPost) {
                $(".validate").text("all filed must be no empty")
                return
            }
            if ($(imageFile)[0].files[0].size >= 1048576) {
                $(".validate").text("file is too big max size img files 1 048 576 bytes")
                return
            }
            if (textOfPost.length > 10_000) {
                $(".validate").text("text for the post is too big, max 10_000 characters")
                return
            }
            let thatForm = $(this)
            let formData = new FormData(thatForm.get(0));
            $.ajax({
                type: "POST",
                url: $(this).attr("action"),
                contentType: false,
                processData: false,
                data: formData,
                success: function (response) {
                    $(".validate").text(response)
                    $(".postTitle").val("")
                    $(".imageFile").val(null)
                    $(".textOfPost").val("")
                }
            })
        })
    }
})