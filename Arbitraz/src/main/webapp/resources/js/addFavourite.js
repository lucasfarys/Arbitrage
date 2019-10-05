document.addEventListener('DOMContentLoaded', function () {
    var saveButton = document.getElementById("saveButton");
    var exchangeFirst = document.getElementById("exchangeFirst");
    var exchangeSecond = document.getElementById("exchangeSecond");
    var course = document.getElementById("course");
    exchangeFirst.addEventListener("change", function (ev) {
        $.ajax({
            url: saveButton.value + "restfavourite/select/" + exchangeFirst.options[exchangeFirst.selectedIndex].text +
                    "/" + exchangeSecond.options[exchangeSecond.selectedIndex].text,
            method: "GET",
            type: 'application/json',
            dataType: 'json',
        }).done(function (result) {
            if(result.coinsName.length != 0){
                while(course.options.length){
                    course.options.remove(0);
                }
                console.log(result.coinsName.length);
                for (var i = 0; i<result.coinsName.length; i++){
                    course.options.add( new Option(result.coinsName[i], i));
                }
            }
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        })
    })
    exchangeSecond.addEventListener("change", function (ev) {
        $.ajax({
            url: saveButton.value + "restfavourite/select/" + exchangeFirst.options[exchangeFirst.selectedIndex].text +
                "/" + exchangeSecond.options[exchangeSecond.selectedIndex].text,
            method: "GET",
            type: 'application/json',
            dataType: 'json',
        }).done(function (result) {
            if(result.coinsName.length != 0){
                console.log(course.options);
                while(course.options.length){
                    course.options.remove(0);
                }
                var size = result.coinsName.length;
                console.log(size + "dupa");
                for (var i = 0; i<result.coinsName.length; i++){
                    course.options.add( new Option(result.coinsName[i], i));
                }
                console.log(result.coinsName.length);
            }
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        })
    })
    saveButton.addEventListener("click", function (ev) {
        var data = {
            exchangeFirst: exchangeFirst.options[exchangeFirst.selectedIndex].text,
            exchangeSecond: exchangeSecond.options[exchangeSecond.selectedIndex].text,
            coin: course.options[course.selectedIndex].text
        }
        $.ajax({
            url: saveButton.value + "restfavourite/add",
            method: "POST",
            type: 'application/json',
            dataType: 'json',
            data: data,
        })
    })
});















