document.addEventListener('DOMContentLoaded', function () {
    var exchangeFirst = document.getElementById("exchangeFirst");
    var exchangeSecond = document.getElementById("exchangeSecond");
    var course = document.getElementById("course");
    var exchange01 = document.getElementById("exchange01");
    var exchange02 = document.getElementById("exchange02");
    var coin = document.getElementById("coin");
    exchangeFirst.addEventListener("change", function (ev) {
        exchange01.value = exchangeFirst.options[exchangeFirst.selectedIndex].text;
        console.log();
    })
    exchangeSecond.addEventListener("change", function (ev) {
        exchange02.value = exchangeSecond.options[exchangeSecond.selectedIndex].text;
        // console.log(exchange02.value);
    })
    course.addEventListener("change", function (ev) {
        coin.value = course.options[course.selectedIndex].text;
    })
});















