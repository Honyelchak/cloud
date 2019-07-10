
var myChart3 = echarts.init(document.getElementById('mychart3'));
var timedata=[];
var Temdata=[];

var option = {
    xAxis: {
        type: 'category',

        splitLine: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            interval: 'auto',
            color: '#fff',
            fontSize: 6,
        },
    },
    yAxis: {
        type: 'value',
        splitLine: {
            show: false
        },
        axisLine: {
            show: false
        },
        axisTick: {
            show: false
        },
        axisLabel: {
            show: false
        },
    },
    series: [{
        name: '成绩',
        type: 'bar',
        label: {
            normal: {
                show: true,
                position: 'top',
                color: '#fff',
                fontSize: 8,
                fontWeight: 700
            }
        },
        itemStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                    offset: 0,
                    color: "#2A82CF" // 0% 处的颜色
                }, {
                    offset: 1,
                    color: "#01FDCC" // 100% 处的颜色
                }], false)
            }
        },
        barWidth: '40%',
        barGap: '-100%',

    }, {
        name: '背景',
        type: 'bar',
        data:[40, 40, 40, 40, 40, 40,40 , 40],
        barWidth: '40%',
        itemStyle: {
            normal: {
                color: 'rgba(102, 102, 102,0.5)'
            }
        },
        zlevel: -1
    }, ]
};
// 使用刚指定的配置项和数据显示图表。
myChart3.setOption(option);


$.ajax({
    // 请求类型
    type:"GET",
    // 请求url
    url:"/hourtem",
    // 请求的数据类型
    dataType:"json",
    // 响应成功之后执行的回调函数
    success: function(data){
        var data = data["data"];
        var len = 8;
        Temdata=[];
        timedata=[];
        for(var i =len-1; i >= 0; i--) {
            Temdata.push(parseInt(data[i][0]));
            var date= new Date(parseInt(data[i][1]));
            var hour =date.getHours();
            timedata.push(hour+"时");
        }

        $("#kqqw1").text(data[7][0]);
        myChart3.setOption({
            xAxis: {
                data: timedata
            },
            series: [{
                    data: Temdata
                } ]

        })
    },
// 响应失败之后执行的回调函数
    error:function(jqXHR){
        alert("发生错误："+ jqXHR.status);
    }
});
