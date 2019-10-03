document.addEventListener('DOMContentLoaded', function () {
    var exchange = document.getElementById("exchangeSelected");
    exchange.addEventListener("change", function (ev) {
        var exchangeSelected = exchange.options[exchange.selectedIndex].text;
        console.log(exchangeSelected);
        $.ajax({
            url: "http://localhost:8080/Arbitraz/restchart/" + exchangeSelected
            // url: "https://bitbay.net/API/Public/BTCPLN/orderbook.json\n"
            ,
            data: {},
            type: "GET"
            ,
            dataType: "json"
        }).done(function (result) {
            var ctx = document.getElementById('myChart').getContext('2d');
            var dataFirst = result.chartFirst;
            var dataSecond = result.chartSecond;
            var date = [];
            for(var i=0;i<24;i++){
                date.push()
            }
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'line',
                // The data for our dataset
                data: {
                    labels: result.date,
                    datasets: [
                        {
                            label: result.nameFirst,
                            backgroundColor: 'rgb(100, 200, 132)',
                            borderColor: 'rgb(120, 200, 120)',
                            data: result.chartFirst,
                            // data: [0, 10, 5, 2, 20, 30, 10],
                            fill: false
                        },
                        {
                            label: result.nameSecond,
                            backgroundColor: 'rgb(100, 99, 132)',
                            borderColor: 'rgb(120, 120, 120)',
                            data: result.chartSecond,
                            // data: [0, 10, 5, 2, 20, 30, 10],
                            fill: false
                        }]
                },
                //Configuration options go here
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                min: min,
                                max: max
                            }
                        }]
                    }
                }
            });
            dataFirst.sort();
            dataSecond.sort();
            var min = Math.min(dataFirst[0],dataFirst[0]);
            var max = Math.max(dataFirst[dataFirst.length-1],dataSecond[dataSecond.length-1]);
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        })
    })
    $.ajax({
        url: "http://localhost:8080/Arbitraz/restchart"
        // url: "https://bitbay.net/API/Public/BTCPLN/orderbook.json\n"
        ,
        data: {},
        type: "GET"
        ,
        dataType: "json"
    }).done(function (result) {
        var ctx = document.getElementById('myChart').getContext('2d');
        var dataFirst = result.chartFirst;
        var dataSecond = result.chartSecond;
        var date = [];
        for(var i=0;i<24;i++){
            date.push()
        }
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',
            // The data for our dataset
            data: {
                labels: result.date,
                datasets: [
                    {
                        label: result.nameFirst,
                        backgroundColor: 'rgb(100, 200, 132)',
                        borderColor: 'rgb(120, 200, 120)',
                        data: result.chartFirst,
                        // data: [0, 10, 5, 2, 20, 30, 10],
                        fill: false
                    },
                    {
                    label: result.nameSecond,
                    backgroundColor: 'rgb(100, 99, 132)',
                    borderColor: 'rgb(120, 120, 120)',
                    data: result.chartSecond,
                    // data: [0, 10, 5, 2, 20, 30, 10],
                    fill: false
                }]
            },
            //Configuration options go here
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            min: min,
                            max: max
                        }
                    }]
                }
            }
        });
        dataFirst.sort();
        dataSecond.sort();
        var min = Math.min(dataFirst[0],dataFirst[0]);
        var max = Math.max(dataFirst[dataFirst.length-1],dataSecond[dataSecond.length-1]);
    }).fail(function (xhr, status, err) {
    }).always(function (xhr, status) {
    })
});















