document.addEventListener('DOMContentLoaded', function () {
    var exchangeFirst = document.getElementById("exchangeFirst");
    var exchangeSecond = document.getElementById("exchangeSecond");
    var course = document.getElementById("course");
    exchangeFirst.addEventListener("change", function (ev) {
        var exchangeFirstSelected = exchangeFirst.options[exchangeFirst.selectedIndex].text;
        var exchangeSecondSelected = exchangeSecond.options[exchangeSecond.selectedIndex].text;
        console.log(exchangeFirstSelected);
        // two chart on change
        $.ajax({
            url: "http://localhost:8080/Arbitraz/restchart/" + exchangeFirstSelected + "/" + exchangeSecondSelected
            ,
            data: {},
            type: "GET"
            ,
            dataType: "json"
        }).done(function (result) {
            console.log("test");
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        })
    })
})















