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
                $(".data").html(data[1]);
                listenerDeleteContactMessage()
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

    $(".add-delete-role").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerAddRole()
                listenerDeleteRole()
            },
        })
    })

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
                    $(".imageFile").val(null)
                    $(".textOfPost").val("")
                }
            })
        })
    }

    const listenerAddRole = () => {
        $(".addRole").on("click", function (event) {
            event.preventDefault()
            $.ajax({
                type: "POST",
                url: `${$(this).attr("href")}`,
                data: {
                    username: $(".addRole-input").val(),
                    role: $(".addRoleSelect").val(),
                    _csrf: $(".csrf").val()
                },
                dataType: "text",
                success: function (response) {
                    $(".dk-response-add, .dk-response-delete").text("")
                    if (response === "role is added") {
                        $(".addRole-input").val("")
                    }
                    $(".dk-response-add").text(response)
                }
            })
        })
    }
    const listenerDeleteRole = () => {
        $(".deleteRole").on("click", function (event) {
            event.preventDefault()
            $.ajax({
                type: "DELETE",
                url: `${$(this).attr("href")}`,
                data: {
                    username: $(".deleteRole-input").val(),
                    role: $(".deleteRoleSelect").val(),
                    _csrf: $(".csrf").val()
                },
                dataType: "text",
                success: function (response) {
                    $(".dk-response-add, .dk-response-delete").text("")
                    if (response === "role is deleted") {
                        $(".deleteRole-input").val("")
                    }
                    $(".dk-response-delete").text(response)
                }
            })
        })
    }

    const listenerDeleteContactMessage = () => {
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
                    $(".data").html(data[1]);
                    listenerDeleteContactMessage()
                },
            })
        })
    }
});