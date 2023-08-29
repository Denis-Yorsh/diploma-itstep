$(() => {
    const slides = $(".slide");
    let slideIndex = 1;

    function showSlides(number) {
        if (number > slides.length) {
            slideIndex = 1;
        }
        if (number < 1) {
            slideIndex = slides.length;
        }
        slides.hide(1500);
        $(slides[slideIndex - 1]).show(1500);
    }

    showSlides(slideIndex);

    $(".prev").click(() => {
        showSlides(--slideIndex)
    });
    $(".next").click(() => {
        showSlides(++slideIndex)
    });

    $("section .show-more").click(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: `${$(this).attr("href")}`,
            data: {_csrf: $(".csrf").val()},
            dataType: "text",
            success: function (response) {
                let data = response.split("<!--------------------------------------------------->")
                $("head").append(data[1])
                $("body").hide(1500, () => {
                    $("main").html(data[3]);
                    $("body").show(1500);
                })
            },
        });
    });
});