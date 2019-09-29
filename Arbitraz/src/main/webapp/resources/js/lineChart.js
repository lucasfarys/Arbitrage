// document.addEventListener('DOMContentLoaded', function(){
    $.ajax({
        // url: "http://localhost:8080/Arbitraz/restchart"
        url: "https://bitbay.net/API/Public/BTCPLN/orderbook.json\n"
        ,
        data: {},
        type: "GET"
        ,
        dataType: "json"
    }).done(function(result) {
        console.log("test");
        console.log(result);
        // var ctx = document.getElementById('myChart').getContext('2d');
        // var chart = new Chart(ctx, {
        //     // The type of chart we want to create
        //     type: 'line',
        //
        //     // The data for our dataset
        //     data: {
        //         labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'May', 'May', 'May', 'May', 'May', 'May', 'May', 'May', 'May'],
        //         datasets: [{
        //             label: 'BTCPLN',
        //             backgroundColor: 'rgb(255, 99, 132)',
        //             borderColor: 'rgb(0, 99, 132)',
        //             data: result,
        //             // data: [0, 10, 5, 2, 20, 30, 10],
        //             fill: false
        //         }]
        //     },

            // Configuration options go here
        });
    }).fail(function(xhr,status,err) {
    }).always(function(xhr,status) {
    })

// });















