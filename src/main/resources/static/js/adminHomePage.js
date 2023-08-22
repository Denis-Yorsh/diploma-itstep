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

    $(".home-role").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerAddDeleteRole()
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
            let thatForm = $(this)
            let formData = new FormData(thatForm.get(0));
            $.ajax({
                type: "POST",
                url: `${$(this).attr("action")}`,
                contentType: false,
                processData: false,
                data: formData,
                success: function (response) {
                    console.dir(response)
                }
            })
        })
    }

    const listenerAddDeleteRole = () => {
        $(".addRole, .deleteRole").on("click", function (event) {
            event.preventDefault();
            const witchRoleArray = $(this).attr("href").split("/")
            let witchUsername;
            let witchRole;
            let httpMethod;
            if (witchRoleArray[2] === "addRole") {
                witchUsername = $(".addRole-input").val()
                witchRole = $(".addRoleSelect").val()
                httpMethod = "POST"
            } else {
                witchUsername = $(".deleteRole-input").val()
                witchRole = $(".deleteRoleSelect").val()
                httpMethod = "DELETE"
            }
            $.ajax({
                type: httpMethod,
                url: `${$(this).attr("href")}`,
                data: {
                    username: witchUsername,
                    role: witchRole,
                    _csrf: $(".csrf").val()
                },
                dataType: "text",
                success: function (response) {
                    let respArray = response.split("---")
                    $(".dk-response-add, .dk-response-delete").text("")
                    if (respArray[0] === "add") {
                        $(".dk-response-add").text(respArray[1])
                    } else if (respArray[0] === "delete") {
                        $(".dk-response-delete").text(respArray[1])
                    }
                },
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