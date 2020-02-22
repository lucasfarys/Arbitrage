document.addEventListener('DOMContentLoaded', function () {
   var addButton = document.getElementById("addButton");
   var selectExchange = document.getElementById("exchange");
   var newCoin = document.getElementById("newCoin");
   var message = document.getElementById("message");
   var coins = document.getElementById("coins");
   var addCoin = document.getElementById("addCoin");
   var addNewCoin = document.getElementById("addNewCoin");
    selectExchange.addEventListener("change", function (ev) {
        var a = $.ajax({
            url: addButton.value + "restfavourite/getCoins",
            method: "GET",
            type: 'application/json',
            dataType: 'json',
            data: {id: selectExchange.options[selectExchange.selectedIndex].value}
        }).done(function(response){
            message.innerText = "";
            var coinTable = document.getElementById("coinsList");
            while(coinTable.rows.length!=0){
                coinTable.deleteRow(0);
            }
                        for (var i = 0; i < response.coinsName.length; i++) {
                var row = coinTable.insertRow(i);
                var cell1 = row.insertCell(0);
                cell1.innerHTML = response.coinsName[i];
            }
            var row = coinTable.insertRow(0);
            var cell1 = row.insertCell(0);
            cell1.innerHTML = 'Lista aktualnych coinów dla wybranej giełdy';
        })

    })
   addButton.addEventListener("click", function (ev) {
        var a = $.ajax({
            url: addButton.value + "restfavourite/addNewUniqueCoin",
            method: "POST",
            type: 'application/json',
            dataType: 'text',
            data: {coinName: newCoin.value,
                    exchangeId: selectExchange.options[selectExchange.selectedIndex].value,
                    coinId: coins.options[coins.selectedIndex].value}
        }).done(function(response){
            console.log(response.coinName);
            if(response=="false"){
                message.innerText = "Zły kod kursu, podaj właściwy";
            }else{
                message.innerText = "Poprawnie dodano coina";
                var coinsList = document.getElementById("coinsList");
                var row = coinsList.insertRow(coinsList.length);
                var cell1 = row.insertCell(0);
                cell1.innerHTML = response;
            }

        })
    })
    addCoin.addEventListener("click",function (ev) {
        var a = $.ajax({
            url: addButton.value + "restfavourite/addNewCoin",
            method: "POST",
            type: 'application/json',
            dataType: 'text',
            data: {name: addNewCoin.value}
        }).done(function(response){
            window.location.reload();
        })
    })
});















