document.addEventListener('DOMContentLoaded', function () {
    var saveButton = document.getElementById("saveButton");
    var exchangeFirst = document.getElementById("exchangeFirst");
    var exchangeSecond = document.getElementById("exchangeSecond");
    var course = document.getElementById("course");
    console.log(window.location.pathname);

    exchangeFirst.addEventListener("change", function (ev) {

        $.ajax({
            url: saveButton.value + "restfavourite/select/" + exchangeFirst.options[exchangeFirst.selectedIndex].text +
                    "/" + exchangeSecond.options[exchangeSecond.selectedIndex].text,
            method: "GET",
            type: 'application/json',
            dataType: 'json',
        }).done(function (result) {
            if(result.coinNames != null){
                while(course.options.length){
                    course.options.remove(0);
                }
                for (var i = 0; i<result.coinNames.length; i++){
                    course.options.add( new Option(result.coinNames[i], i));
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
            if(result.coinNames != null){
                while(course.options.length){
                    course.options.remove(0);
                }
                for (var i = 0; i<result.coinNames.length; i++){
                    course.options.add( new Option(result.coinNames[i], i));
                }
            }
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        })
    })
    saveButton.addEventListener("click", function (ev) {
        var data1 = exchangeFirst.options[exchangeFirst.selectedIndex].text;
        var data2 = exchangeSecond.options[exchangeSecond.selectedIndex].text;
        var data3 = course.options[course.selectedIndex].text;
        var data = {
            exchangeFirst: exchangeFirst.options[exchangeFirst.selectedIndex].text,
            exchangeSecond: exchangeSecond.options[exchangeSecond.selectedIndex].text,
            coin: course.options[course.selectedIndex].text
        }
        var a = $.ajax({
            url: saveButton.value + "restfavourite/add",
            method: "POST",
            type: 'application/json',
            dataType: 'json',
            data: data,
        }).done(function(response){
            var table = document.getElementById("favouriteTable");
            var row = table.insertRow(favouriteTable.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            cell1.innerHTML = data1 + ' | ' + data2 + ' | ' + data3;
            cell2.innerHTML = '<a role="button"  class="btn btn-link" id="dasboardButton"\n' +
                '                                   href="'+ saveButton.value +'delfavourite/'+response+'">\n' +
                '                                    <i class="fas fa-trash" style="color: grey"></i>\n' +
                '                                </a>';
        })

    })
});















