var myChart = echarts.init(document.getElementById('mychart3'));
var option = {
    xAxis: {
        type: 'category',
        data: ['14时', '15时', '16时', '17时', '18时', '19时', '20时', '21时'],
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
        data: [29, 27, 23, 20, 18, 14, 11, 9]
    }, {
        name: '背景',
        type: 'bar',
        data: [29, 29, 29, 29, 29, 29, 29, 29],
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
myChart.setOption(option);