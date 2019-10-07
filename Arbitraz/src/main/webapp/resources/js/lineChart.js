document.addEventListener('DOMContentLoaded', function () {
    var exchange = document.getElementById("exchangeSelected");
    exchange.addEventListener("change", function (ev) {
        var exchangeSelected = exchange.options[exchange.selectedIndex].text;
        // two chart on change

        $.ajax({
            url: "http://localhost:8080/Arbitraz/restchart/" + exchangeSelected
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
            if(window.bar!= null){
                window.bar.destroy;
            }
            window.bar = new Chart(ctx, {
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
                            fill: false
                        },
                        {
                            label: result.nameSecond,
                            backgroundColor: 'rgb(100, 99, 132)',
                            borderColor: 'rgb(120, 120, 120)',
                            data: result.chartSecond,
                            fill: false
                        }]
                },
                //Configuration options go here
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                min: minDefault,
                                max: maxDefault
                            }
                        }]
                    }
                }
            });
            dataFirst.sort();
            dataSecond.sort();
            var minDefault = Math.min(dataFirst[0],dataFirst[0]);
            var maxDefault = Math.max(dataFirst[dataFirst.length-1],dataSecond[dataSecond.length-1]);



            // difference chart
            var ctx2 = document.getElementById('myChartDifference').getContext('2d');
            var dataDifference = result.chartDifference;
            var chart2 = new Chart(ctx2, {
                // The type of chart we want to create
                type: 'line',
                // The data for our dataset
                data: {
                    labels: result.date,
                    datasets: [
                        {
                            label: "Difference [ % ]   Min success is 2 [ % ]",
                            backgroundColor: 'rgb(120, 120, 120)',
                            borderColor: 'rgb(120, 200, 120)',
                            data: result.chartDifference,
                            fill: false
                        }]
                },
                //Configuration options go here
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                min: minDifferenceDefault,
                                max: maifferenceDefault
                            }
                        }]
                    }
                }
            });
            dataDifference.sort();
            var minDifferenceDefault = dataDifference[0];
            var maifferenceDefault = dataDifference[dataDifference.length-1];
        }).fail(function (xhr, status, err) {
        }).always(function (xhr, status) {
        });
    });

    // chart when load dashboard
    $.ajax({
        url: "http://localhost:8080/Arbitraz/restchart"
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
                        fill: false
                    },
                    {
                    label: result.nameSecond,
                    backgroundColor: 'rgb(100, 99, 132)',
                    borderColor: 'rgb(120, 120, 120)',
                    data: result.chartSecond,
                    fill: false
                }]
            },
            //Configuration options go here
            // options: {
            //     scales: {
            //         yAxes: [{
            //             ticks: {
            //                 min: result.minDefault,
            //                 max: 32610
            //             }
            //         }]
            //     }
            // }
        });

        // difference chart
        var ctx2 = document.getElementById('myChartDifference').getContext('2d');
        var dataDifference = result.chartDifference;
        var chart2 = new Chart(ctx2, {
            // The type of chart we want to create
            type: 'line',
            // The data for our dataset
            data: {
                labels: result.date,
                datasets: [
                    {
                        label: "Difference [ % ]   Min success is 2 [ % ]",
                        backgroundColor: 'rgb(120, 120, 120)',
                        borderColor: 'rgb(120, 200, 120)',
                        data: result.chartDifference,
                        fill: false
                    }]
            },
            //Configuration options go here
            // options: {
            //     scales: {
            //         yAxes: [{
            //             ticks: {
            //                 min: result.minDifference,
            //                 max: result.maxDifference
            //             }
            //         }]
            //     }
            // }
        });
        dataDifference.sort();
        var minDifferenceDefault = dataDifference[0];
        var maifferenceDefault = dataDifference[dataDifference.length-1];
    }).fail(function (xhr, status, err) {
    }).always(function (xhr, status) {
    });
});
















