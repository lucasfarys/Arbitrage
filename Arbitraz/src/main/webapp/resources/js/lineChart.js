document.addEventListener('DOMContentLoaded', function () {
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
        var data = result.btcPln.sort();
        var min = data[0];
        var max = data[data.length-1];
        console.log(min);
        console.log(max);
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',
            // The data for our dataset
            data: {
                labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'May', 'May', 'May', 'May', 'May', 'May', 'May', 'May', 'May'],
                datasets: [{
                    label: 'BTCPLN',
                    backgroundColor: 'rgb(255, 99, 132)',
                    borderColor: 'rgb(0, 99, 132)',
                    data: result.btcPln,
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
    }).fail(function (xhr, status, err) {
        console.log("nie dziala");
    }).always(function (xhr, status) {
    })
});














