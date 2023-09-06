$(() => {
    $("body").show(1500);

    $(".contact-messages").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr("href"),
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
            url: $(this).attr("href"),
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
            url: $(this).attr("href"),
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

    const listenerAddRole = () => {
        $(".addRole").on("click", function (event) {
            event.preventDefault()
            $.ajax({
                type: "POST",
                url: $(this).attr("href"),
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
                url: $(this).attr("href"),
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
        $(".delete-contact-messages").on("click", function (event) {
            event.preventDefault();
            $.ajax({
                type: "DELETE",
                url: "/delete",
                data: {
                    id: $(this).attr("href"),
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