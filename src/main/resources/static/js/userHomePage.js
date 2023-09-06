$(() => {
    $("body").show(1500);

    $(".settings").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr("href"),
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $(".data").html(data[1]);
                listenerSettings()
            }
        })
    })

    const listenerSettings = () => {
        $(".form-settings").on("submit", function (event) {
            event.preventDefault();
            $(".response").text("")
            $(".validate").text("")
            const firstName = $(".first-name").val().trim()
            const lastName = $(".last-name").val().trim()
            const dayBirthday = $(".day-birthday").val()
            const email = $(".email").val().trim()
            const username = $(".username").val().trim()
            const password = $(".password").val().trim()
            const repeatPassword = $(".repeat-password").val().trim()
            if (!firstName && !lastName && dayBirthday === "" && !email && !username && !password && !repeatPassword) {
                return
            }
            if (validationSettings(firstName, lastName, dayBirthday, email, username, password, repeatPassword)) {
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
                    if (response.startsWith("username")) {
                        $(".validate-username").text(response)
                    } else if (response.startsWith("email")) {
                        $(".validate-email").text(response)
                    } else {
                        $(".restart").val("")
                        $(".response").text(response)
                    }
                }
            })
        })
    }

    const validationSettings = (firstName, lastName, dayBirthday, email, username, password, repeatPassword) => {
        let validate = false
        if ((firstName.length > 0 && firstName.length < 3) || firstName.length > 20) {
            validate = true
            $(".validate-first-name").text("First Name length must be from 3 to 20")
        }
        if ((lastName.length > 0 && lastName.length < 3) || lastName.length > 20) {
            validate = true
            $(".validate-last-name").text("Last Name length must be from 3 to 20")
        }
        if (email.length > 0) {
            if (!validateEmail(email)) {
                validate = true
                $(".validate-email").text("Email is invalid")
            }
        }
        if ((username.length > 0 && username.length < 3) || username.length > 20) {
            validate = true
            $(".validate-username").text("username length must be from 3 to 20")
        }
        const date = new Date()
        const dateBirthday = new Date(dayBirthday)
        if (dayBirthday !== "") {
            if (dateBirthday >= date) {
                validate = true
                $(".validate-day-birthday").text("birthday must be in past time")
            }
        }
        if (password.length > 0 || repeatPassword.length > 0) {
            if ((password.length > 0 && password.length < 3) || password.length > 20 ||
                (repeatPassword.length > 0 && repeatPassword.length < 3) || repeatPassword.length > 20) {
                validate = true
                $(".validate-password").text("Password length must be from 3 to 20")
            } else if (password !== repeatPassword) {
                validate = true
                $(".validate-password").text("Password mismatch")
            }
        }
        return validate
    }

    const validateEmail = (email) => {
        return email.match(
            /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    };
})