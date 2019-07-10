var domchart = document.getElementById("mychart");
var myindex = 0;
var mycolor = ['#005EFF', '#FFFFFF', '#24FEB4', '#23539B', '#3C9DE4'];

function DrawPieArea(drawdom, piedata, color, curIndex) {
    option = {
        color: color,
        series: [{
            type: 'pie',
            hoverOffset:4,
            selectedOffset:4,
            radius: ['70%', '80%'],
            avoidLabelOverlap: false,
            silent: true,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    formatter: "{B|{c}}\n{S|{b}}",
                    textStyle: {
                        color: 'color[curIndex]',
                        rich: {
                            B: {
                                fontSize: 20,
                                fontWeight: 'bolder',
                                lineHeight: 50,
                            },
                            S: {
                                fontSize: 10,
                            }
                        },
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: piedata
        }]
    };
    var chart_pie = echarts.init(drawdom);
    chart_pie.setOption(option, true);
    chart_pie.dispatchAction({
        type: 'highlight',
        seriesIndex: 0,
        dataIndex: curIndex
    });
    var dt = setInterval(function() {
        var dataLen = piedata.length;
        // 取消高亮
        chart_pie.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: curIndex
        });
        curIndex = (curIndex + 1) % dataLen;
        //设置高亮
        chart_pie.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: curIndex
        });
    }, 3000);
}
var data = [{
    value: 23.3,
    name: '1基地'
}, {
    value: 22.5,
    name: '2基地'
}, {
    value: 24.1,
    name: '3基地'
}, {
    value: 23.7,
    name: '4基地'
}, {
    value: 23.9,
    name: '5基地'
}];
DrawPieArea(domchart, data, mycolor, myindex);