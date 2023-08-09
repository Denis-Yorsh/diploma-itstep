$(() => {
    $("body").show(1500);

    $(".send-massage").click(function (event) {
        const name = $(".name").val().trim()
        const email = $(".email").val().trim()
        const message = $(".message").val().trim()
        $(".massage").text("")

        if (!name || !email || !message) {
            event.preventDefault();
            if (!name) {
                $(".massageName").text("field must be no blank")
            }
            if (!email) {
                $(".massageEmail").text("field must be no blank")
            }
            if (!message) {
                $(".massageMassage").text("field must be no blank")
            }
        }
        if (!validateEmail(email)) {
            event.preventDefault();
            $(".validateEmail").text(email + " email is invalid")
        }
        if (name.length > 50) {
            event.preventDefault();
            $(".massageMassage").text("name must not exceed 50 characters")
        }
        if (message.length > 1000) {
            event.preventDefault();
            $(".massageMassage").text("message must not exceed 1000 characters")
        }

        $(this).attr("href", `/info/massage/${name}/${email}/${message}`)
    })

    const validateEmail = (email) => {
        return email.match(
            /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    };
});