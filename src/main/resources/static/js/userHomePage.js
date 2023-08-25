$(() => {
    $("body").show(1500);

    $(".settings").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerSettings()
            }
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

    $(".all-posts").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerDeletePost()
            }
        })
    })

    const listenerSettings = () => {
        $(".form-settings").on("submit", function (event) {
            event.preventDefault();
            const validate = $(".validate")
            validate.text("")
            const firstName = $(".first-name").val().trim()
            const lastName = $(".last-name").val().trim()
            const dayBirthday = $(".day-birthday").val()
            const email = $(".email").val().trim()
            const username = $(".username").val().trim()
            const password = $(".password").val().trim()
            const repeatPassword = $(".repeat-password").val().trim()
            if (!firstName && !lastName && !dayBirthday && !email && !username && !password && !repeatPassword) {
                return
            }
            $.ajax({
                type: "POST",
                url: $(this).attr("action"),
                data: {
                    firstName: firstName,
                    lastName: lastName,
                    dayBirthday: dayBirthday,
                    email: email,
                    username: username,
                    password: password,
                    repeatPassword: repeatPassword,
                    _csrf: $(".csrf").val()
                },
                dataType: "text",
                success: function (response) {
                    // const responseArray = response.split("-")
                    // $(responseArray).each(function (i, val) {
                    //     validate.append(val)
                    //     validate.append("<br>")
                    // })
                    validate.text(response)
                }
            })
        })
    }

    const listenerDeletePost = () => {
        $(".delete-post").on("click", function (event) {
            event.preventDefault();
            $.ajax({
                type: "DELETE",
                url: "/post/deletePost",
                data: {
                    id: `${$(this).attr("href")}`,
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