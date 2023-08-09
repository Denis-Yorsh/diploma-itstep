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

  $(".prev").click(() => {showSlides(--slideIndex)});
  $(".next").click(() => {showSlides(++slideIndex)});

  $("section .show-more").click(function (event) {
    event.preventDefault();
    $("body").hide(1500, () => {
      $.ajax({
        type: "POST",
        url: `${$(this).attr("href")}`,
        data: {_csrf: $(".csrf").val()},
        dataType: "text",
        success: function (response) {
          $("main").html(response);
          $("body").show(1500);
        },
      });
    });
  });
});