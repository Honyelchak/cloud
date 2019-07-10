
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
var fivedata = "[";
//     value: 23.3,
//     name: '1基地'
// }, {
//     value: 22.5,
//     name: '2基地'
// }, {
//     value: 24.1,
//     name: '3基地'
// }, {
//     value: 23.7,
//     name: '4基地'
// }, {
//     value: 23.9,
//     name: '5基地'
// }];

$.ajax({
    // 请求类型
    type: "POST",
    // 请求url
    url: "/fivehourtem",
    data:"101180101",
    // 请求的数据类型
    dataType: "json",
    // 响应成功之后执行的回调函数
    success: function (data) {
        var maxtem = data["maxtem"];
        var mintem = data["mintem"];
        var data = data["data"];
        var len = 5;

        for (var i = len - 1; i >= 0; i--) {
            var tem = parseInt(data[i][0]);
            var date = new Date(parseInt(data[i][1]));
            var hour = date.getHours();
            fivedata += "{ value:" + tem + ",name:'" + hour + "时'},";
        }
        fivedata += "]";
        console.log("maxtem");
        console.log(data["mintem"]);

        DrawPieArea(domchart, eval('(' + fivedata + ')'), mycolor, myindex);
        $("#maxtem").text(maxtem);
        $("#mintem").text(mintem);
    },
// 响应失败之后执行的回调函数
    error: function (jqXHR) {
       // alert("发生错误：" + jqXHR.status);
    }
});


//土壤温度变化
var myChart2 = echarts.init(document.getElementById('mychart2'));
var timedata = [];
var Temdata = [];
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
            fontSize: 15,
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
        data: [40, 40, 40, 40, 40, 40, 40, 40],
        barWidth: '40%',
        itemStyle: {
            normal: {
                color: 'rgba(102, 102, 102,0.5)'
            }
        },
        zlevel: -1
    },]
};
// 使用刚指定的配置项和数据显示图表。
myChart2.setOption(option);

$.ajax({
    // 请求类型
    type: "POST",
    // 请求url
    url: "/hourHum",
    data:"22240",
    // 请求的数据类型
    dataType: "json",
    // 响应成功之后执行的回调函数
    success: function (data) {
        var data = data["data"];

        var len = 8;
        Temdata = [];
        timedata = [];
        for (var i = (len - 1) * 6; i >= 0; i -= 6) {
            Temdata.push(parseInt(data[i][0]));
            var date = new Date(parseInt(data[i][1]));
            var hour = date.getHours();
            timedata.push(hour + "时");
        }
        $("#trqw1").text(data[0][0].toString().substring(0,5));
        //$("#trqw1").text(23);
        myChart2.setOption({
            xAxis: {
                data: timedata
            },
            series: [{
                data: Temdata
            }]

        })
    },
// 响应失败之后执行的回调函数
    error: function (jqXHR) {
      //  alert("发生错误：" + jqXHR.status);
    }
});


//空气温度变化

var myChart3 = echarts.init(document.getElementById('mychart3'));
var timedata = [];
var Temdata = [];

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
            fontSize: 15,
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
        data: [40, 40, 40, 40, 40, 40, 40, 40],
        barWidth: '40%',
        itemStyle: {
            normal: {
                color: 'rgba(102, 102, 102,0.5)'
            }
        },
        zlevel: -1
    },]
};
// 使用刚指定的配置项和数据显示图表。
myChart3.setOption(option);


$.ajax({
    // 请求类型
    type: "POST",
    // 请求url
    url: "/hourtem",
    data:"101180101",
    // 请求的数据类型
    dataType: "json",
    // 响应成功之后执行的回调函数
    success: function (data) {
        var cityname=data["cityname"];
        var data = data["data"];
        var len = 8;
        Temdata = [];
        timedata = [];
        for (var i = len - 1; i >= 0; i--) {
            Temdata.push(parseInt(data[i][0]));
            var date = new Date(parseInt(data[i][1]));
            var hour = date.getHours();
            timedata.push(hour + "时");
        }
        $("#kqqw1").text(data[0][0]);
        $("#location1").text(cityname);
        $("#location2").text(cityname);

        console.log(Temdata);
        console.log(timedata);
        myChart3.setOption({
            xAxis: {
                data: timedata
            },
            series: [{
                data: Temdata
            }]

        })
    },
// 响应失败之后执行的回调函数
    error: function (jqXHR) {
       // alert("发生错误：" + jqXHR.status);
    }
});
//创建地图
var map = new AMap.Map('main', {
    cursor: 'default',
    resizeEnable: true,
    zoom: 8,
    center: [113.937209, 34.105203],
    mapStyle: 'amap://styles/eaa3ec8faf0979d854c8e22637144b7a'
});


// map.setFitView();
// 构造官方卫星、路网图层
var satelliteLayer = new AMap.TileLayer.Satellite();
var roadNetLayer = new AMap.TileLayer.RoadNet();

//批量添加图层
map.add([satelliteLayer, roadNetLayer]);
// 自动适配到合适视野范围

// 异步加载plugins，加载后执行回调函数
AMap.plugin(['AMap.ToolBar', 'AMap.Scale'], function () {//异步同时加载多个插件
    console.log("success load in plugins");
    var toolbar = new AMap.ToolBar();
    map.plugin(toolbar);
    var scale = new AMap.Scale();
    map.plugin(scale);
});


//map.plugin(new AMap.OverView);
//map.plugin(new AMap.CitySearch);
function initPage(DistrictCluster, $) {

    var distCluster = new DistrictCluster({
        map: map, //所属的地图实例
        zIndex: 11,
        topAdcodes: [410000],
        //排除3个省
        //excludedAdcodes:[130000, 610000, 340000],
        autoSetFitView: false,
        getPosition: function (item) {
            if (!item) {
                return null;
            }
            var parts = item.split(',');
            //返回经纬度
            return [parseFloat(parts[0]), parseFloat(parts[1])];
        },
        renderOptions: {
            featureStyle: {
                fillStyle: 'rgba(13, 66, 120, 0.8)',
                lineWidth: 1,
                strokeStyle: 'rgba(f, f, f, 0.8)',//未选中边界线
                hoverOptions: {
                    fillStyle: 'rgba(33, 55, 90, 0.6)',

                    //fillStyle: 'rgba(0, 0, 0, 0.5)',
                    lineWidth: 2,
                    //strokeStyle: 'rgba(255, 0, 0, 0.8)'
                    strokeStyle: 'rgba(f, f, f, 0.8)',//选中边界线
                }
            },
            featureStyleByLevel: {
                country: {
                    fillStyle: 'rgba(13, 66, 120,0.6)'
                },
                province: {
                    fillStyle: 'rgba(5, 75, 152,0.8)'
                },
                city: {
                    fillStyle: 'rgba(28, 49, 129, 0.7)'
                },
                district: {
                    fillStyle: 'rgba(21, 121, 242,0.6)'
                    // fillStyle: 'rgba(97, 160, 168,1)'
                }
            }
        }
    });

    window.distCluster = distCluster;

    $('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);
    $.get('https://a.amap.com/amap-ui/static/data/10w.txt', function (csv) {

        $('#loadingTip').remove();

        var data = csv.split('\n');

        distCluster.setData(data);
    });
}

//加载相关组件
AMapUI.load(['ui/geo/DistrictCluster', 'lib/$'], function (DistrictCluster, $) {

    window.DistrictCluster = DistrictCluster;

    //启动页面
    initPage(DistrictCluster, $);
});

/*var city = {
    "Stations": [
        {
            "name":"郑州市",
            "Longitude": "113.683462",
            "Latitude": "34.822446"
        },
        {
            "name":"安阳市",
            "Longitude": "114.352482",
            "Latitude": "36.103442"
        },
        {
            "name":"新乡市",
            "Longitude": "113.883991",
            "Latitude": "35.302616"
        },
        {
            "name":"许昌市",
            "Longitude": "113.826063",
            "Latitude": "34.022956"
        },
        {
            "name":"平顶山",
            "Longitude": "113.041396",
            "Latitude": "34.627459"
        },
        {
            "name":"信阳市",
            "Longitude": "114.075031",
            "Latitude": "32.123274"
        },
        {
            "name":"南阳市",
            "Longitude": "112.540918",
            "Latitude": "32.999082"
        },
        {
            "name":"开封市",
            "Longitude": "114.341447",
            "Latitude": "34.797049"
        },
        {
            "name":"洛阳市",
            "Longitude": "112.434468",
            "Latitude": "34.663041"
        },
        {
            "name":"商丘市",
            "Longitude": "115.650497",
            "Latitude": "34.437054"
        },
        {
            "name":"焦作市",
            "Longitude": "113.238266",
            "Latitude": "35.23904"
        },
        {
            "name":"鹤壁市",
            "Longitude": "114.295444",
            "Latitude": "35.748236"
        },
        {
            "name":"濮阳市",
            "Longitude": "115.041299",
            "Latitude": "35.768234"
        },
        {
            "name":"周口市",
            "Longitude": "114.649653",
            "Latitude": "33.620357"
        },
        {
            "name":"漯河市",
            "Longitude": "114.026405",
            "Latitude": "33.575855"
        },
        {
            "name":"驻马店市",
            "Longitude": "114.024736",
            "Latitude": "32.980169"
        },
        {
            "name":"三门峡市",
            "Longitude": "111.194099",
            "Latitude": "34.777338"
        },
        {
            "name":"济源市",
            "Longitude": "112.601919",
            "Latitude": "35.067243"
        },
    ]
};*/

var city = {
        "Stations": [
            {
                "cityId":"101180401",
                "Index": "22239",
                "Name": "许昌长葛1号监测站",
                "Province": "河南省",
                "Longitude": "34.248983",
                "Latitude": "113.804696"
            },
            {
                "cityId":"101180401",
                "Index": "22240",
                "Name": "许昌长葛3号监测站",
                "Province": "河南省",
                "Longitude": "34.238588",
                "Latitude": "113.839672"
            },
            {
                "cityId":"101180401",
                "Index": "22241",
                "Name": "许昌长葛2号监测站",
                "Province": "河南省",
                "Longitude": "34.219219",
                "Latitude": "113.813316"
            },
            {
                "cityId":"101180401",
                "Index": "22308",
                "Name": "许昌长葛5号监测站",
                "Province": "河南省",
                "Longitude": "34.217578",
                "Latitude": "113.817168"
            },
            {
                "cityId":"101180401",
                "Index": "22309",
                "Name": "许昌长葛4号监测站",
                "Province": "河南省",
                "Longitude": "34.222244",
                "Latitude": "113.837724"
            },
            {
                "cityId":"101180401",
                "Index": "22319",
                "Name": "许昌长葛6号监测站",
                "Province": "河南省",
                "Longitude": "34.243533",
                "Latitude": "113.84635"
            },
            {
                "cityId":"101180401",
                "Index": "22477",
                "Name": "许昌长葛9号监测站",
                "Province": "河南省",
                "Longitude": "34.238488",
                "Latitude": "113.786655"
            },
            {
                "cityId":"101180401",
                "Index": "22478",
                "Name": "许昌长葛8号监测站",
                "Province": "河南省",
                "Longitude": "34.2149462304",
                "Latitude": "113.7825159271"
            },
            {
                "cityId":"101180401",
                "Index": "22479",
                "Name": "许昌长葛7号监测站",
                "Province": "河南省",
                "Longitude": "34.1149462304",
                "Latitude": "113.7825159271"
            },
            {
                "cityId":"101180301",
                "Index": "22772",
                "Name": "原阳小吴庄农业物联网监控站",
                "Province": "河南省",
                "Longitude": "34.9928541445837",
                "Latitude": "113.937209"
            },
            {
                "cityId":"101180201",
                "Index": "22790",
                "Name": "河南安阳内黄农业物联网网监控站",
                "Province": "河南省",
                "Longitude": "35.964401",
                "Latitude": "114.867503"
            },
            {
                "cityId":"101180202",
                "Index": "21015",
                "Name": "河南省汤阴县农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.84",
                "Latitude": "114.35107777778"
            },
            {
                "cityId":"101180201",
                "Index": "22370",
                "Name": "河南滑县小铺物联网监控站",
                "Province": "河南省",
                "Longitude": "35.5370973177",
                "Latitude": "114.4954919729"
            },
            {
                "cityId":"101180201",
                "Index": "22371",
                "Name": "河南滑县小铺物联网监控站_图像2",
                "Province": "河南省",
                "Longitude": "35.531379",
                "Latitude": "114.489022"
            },
            {
                "cityId":"101180201",
                "Index": "21361",
                "Name": "滑县留固小营农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.5899375876",
                "Latitude": "114.6677959041"
            },
            {
                "cityId":"101180201",
                "Index": "21380",
                "Name": "滑县白马坡农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.619836",
                "Latitude": "114.737096"
            },
            {
                "cityId":"101180401",
                "Index": "21386",
                "Name": "河南许昌绰韩村物联网监控站",
                "Province": "河南省",
                "Longitude": "34.13362",
                "Latitude": "113.955161"
            },
            {
                "cityId":"101180701",
                "Index": "21388",
                "Name": "河南方城县席庄村物联网监测站",
                "Province": "河南省",
                "Longitude": "33.260318",
                "Latitude": "113.019912"
            },
            {
                "cityId":"101180801",
                "Index": "21390",
                "Name": "河南省尉氏县物联网监控站",
                "Province": "河南省",
                "Longitude": "34.4173376",
                "Latitude": "114.199531"
            },
            {
                "cityId":"101181201",
                "Index": "22218",
                "Name": "河南浚县农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.6761271206",
                "Latitude": "114.5508016973"
            },
            {
                "cityId":"101180701",
                "Index": "22249",
                "Name": "河南南阳新野县农业物联网监测站",
                "Province": "河南省",
                "Longitude": "32.518026",
                "Latitude": "112.329642"
            },
            {
                "cityId":"101180201",
                "Index": "20567",
                "Name": "安阳市滑县白马坡作物物联网监控站",
                "Province": "河南省",
                "Longitude": "35.5756405083",
                "Latitude": "114.5136086236"
            },
            {
                "cityId":"101180901",
                "Index": "20730",
                "Name": "洛阳市汝阳县小麦监控站",
                "Province": "河南省",
                "Longitude": "34.15981542673",
                "Latitude": "112.47964938323"
            },
            {
                "cityId":"101180201",
                "Index": "20736",
                "Name": "安阳市滑县老店农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.4433500528",
                "Latitude": "114.563892285"
            },
            {
                "cityId":"101180201",
                "Index": "20741",
                "Name": "安阳市滑县上官镇农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.4430226822",
                "Latitude": "114.6336050138"
            },
            {
                "cityId":"101180201",
                "Index": "20747",
                "Name": "安阳市滑县大寨乡农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.4967464213",
                "Latitude": "114.9200000138"
            },
            {
                "cityId":"101180201",
                "Index": "20788",
                "Name": "安阳市滑县东留固农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.4517480866768",
                "Latitude": "114.688487"
            },
            {
                "cityId":"101180201",
                "Index": "20794",
                "Name": "安阳市滑县八里营农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.524514872",
                "Latitude": "114.7971424457"
            },
            {
                "cityId":"101180401",
                "Index": "20912",
                "Name": "许昌市农技站长葛农科所监控站",
                "Province": "河南省",
                "Longitude": "34.216688",
                "Latitude": "113.76833"
            },
            {
                "cityId":"101180401",
                "Index": "20913",
                "Name": "许昌市农技站五女店小麦监控站",
                "Province": "河南省",
                "Longitude": "34.068661",
                "Latitude": "114.012573"
            },
            {
                "cityId":"101180201",
                "Index": "20980",
                "Name": "滑县高平镇门头村农业物联网监控站",
                "Province": "河南省",
                "Longitude": "35.37346",
                "Latitude": "114.777966"
            },
            {
                "cityId":"101180201",
                "Index": "21263",
                "Name": "河南安阳县瓦店2农业物联网监控站",
                "Province": "河南省",
                "Longitude": "36.034905",
                "Latitude": "114.55652"
            },
            {
                "cityId":"101181001",
                "Index": "21346",
                "Name": "商丘柘城申桥乡物联网监控站",
                "Province": "河南省",
                "Longitude": "34.4155351343",
                "Latitude": "115.6508714715"
            },
            {
                "cityId":"101181001",
                "Index": "22359",
                "Name": "商丘柘城牛城监测站",
                "Province": "河南省",
                "Longitude": "34.14055",
                "Latitude": "115.31435"
            },
            {
                "cityId":"101181401",
                "Index": "22360",
                "Name": "周口西华县黄楼村监测站",
                "Province": "河南省",
                "Longitude": "33.621331",
                "Latitude": "114.567596"
            },
            {
                "cityId":"101181401",
                "Index": "22361",
                "Name": "河南西平县二郎乡监测站",
                "Province": "河南省",
                "Longitude": "33.303965",
                "Latitude": "114.010816"
            },
            {
                "cityId":"101180701",
                "Index": "22369",
                "Name": "河南方城席庄物联网监控站1",
                "Province": "河南省",
                "Longitude": "33.120448",
                "Latitude": "112.814726"
            },
            {
                "cityId":"101180101",
                "Index": "22482",
                "Name": "郑州毛庄农业物联网监测站",
                "Province": "河南省",
                "Longitude": "34.870144",
                "Latitude": "113.624627"
            },
            {
                "cityId":"101180801",
                "Index": "22487",
                "Name": "河南省兰考县爪营乡朱场村物联网监控站",
                "Province": "河南省",
                "Longitude": "34.906328",
                "Latitude": "114.866269"
            }
        ]
};


var infoWindow, title, content;
content = [], markers = [], markers2 = [];
var myDate = new Date();
var aaa = myDate.getHours();
if(aaa<7){
    aaa=7;
}
if(aaa>17){
    aaa=17;
}
content.push("<img width='220px' height='240px' src='../assets/monitor/images/station/"+aaa+".jpg'><ul><li>土壤湿度：<span id='trsd2'></span>%</li>");
content.push("<li>土壤温度：<span id='trwd'></span>℃</li>");
content.push("<li>空气湿度：<span id='kqsd2'></span></li>");
content.push("<li>空气温度：<span id='kqwd'></span>℃</li>");
//content.push("<a href='https://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");



// 添加marker
AMapUI.loadUI(['overlay/AwesomeMarker'], function (AwesomeMarker) {
    var points = city["Stations"];
    // console.log(points);
    for (var i = 0, len = points.length; i < len; i++) {

        var marker = new AwesomeMarker({
            //设置awesomeIcon
            awesomeIcon: "adjust",
            iconLabel: {
                style: {
                    color: '#333',
                    fontSize: '14px'
                }
            },
            /*"index": points[i]["Index"],*/
            iconStyle: 'orange',
            map: map,
            position: new AMap.LngLat(points[i]["Latitude"], points[i]["Longitude"]),
            title: points[i]["Name"],
            location:points[i]["Index"],
            cityId:points[i]["cityId"]
        });
        markers.push(marker);
        AMap.event.addListener(marker, 'click', function (e) {
            //今日实时温度

                var marker = e.target;
                var city = marker["B"]["cityId"];
                var location = marker["B"]["location"];
                console.log(location);
            console.log(city);

            $.ajax({
                // 请求类型
                type: "POST",
                async : true,
                // 请求url
                url: "/fivehourtem",
                // 请求的数据类型
                data:city,
                dataType: "json",
                // 响应成功之后执行的回调函数
                success: function (data) {
                    var maxtem = data["maxtem"];
                    var mintem = data["mintem"];
                    var data = data["data"];
                    var len = 5;

                    for (var i = len - 1; i >= 0; i--) {
                        var tem = parseInt(data[i][0]);
                        var date = new Date(parseInt(data[i][1]));
                        var hour = date.getHours();
                        fivedata += "{ value:" + tem + ",name:'" + hour + "时'},";
                    }
                    fivedata += "]";

                    DrawPieArea(domchart, eval('(' + fivedata + ')'), mycolor, myindex);
                    $("#maxtem").text(maxtem);
                    $("#mintem").text(mintem);
                },
// 响应失败之后执行的回调函数
                error: function (jqXHR) {
                    //alert("发生错误：" + jqXHR.status);
                }
            });
            $.ajax({
                // 请求类型
                type: "POST",
                async : true,
                // 请求url
                url: "/hourHum",
                // 请求的数据类型
                data:city,
                dataType: "json",
                // 响应成功之后执行的回调函数
                success: function (data) {
                    var data = data["data"];
                    var len = 8;
                    Temdata = [];
                    timedata = [];
                    for (var i = (len - 1) * 6; i >= 0; i -= 6) {
                        Temdata.push(parseInt(data[i][0]));
                        var date = new Date(parseInt(data[i][1]));
                        var hour = date.getHours();
                        timedata.push(hour + "时");
                    }
                    if (data[0][0]==null||data[0][0]==0) {
                        data[0][0]=15;
                    }
                    $("#trwd").text(data[0][0].substring(0,4));
                    // console.log(data[0][0]);
                    $("#trqw1").text(data[0][0].substring(0,4));
                    myChart2.setOption({
                        xAxis: {
                            data: timedata
                        },
                        series: [{
                            data: Temdata
                        }]

                    })
                },
// 响应失败之后执行的回调函数
                error: function (jqXHR) {
                    //alert("发生错误：" + jqXHR.status);
                }
            });


            $.ajax({
                // 请求类型
                type: "POST",
                async : true,
                // 请求url
                url: "/hourtem",
                // 请求的数据类型
                data:city,
                dataType: "json",
                // 响应成功之后执行的回调函数
                success: function (data) {
                    var cityname=data["cityname"];
                    var data = data["data"];
                    var len = 8;
                    Temdata = [];
                    timedata = [];
                    for (var i = len - 1; i >= 0; i--) {
                        Temdata.push(parseInt(data[i][0]));
                        var date = new Date(parseInt(data[i][1]));
                        var hour = date.getHours();
                        timedata.push(hour + "时");
                    }
                    $

                    if (data[0][0]==null||data[0][0]==0) {
                        data[0][0]=15;
                    }
                    $("#kqwd").text(data[0][0]);
                    $("#kqqw1").text(data[0][0]);
                    $("#location1").text(cityname);
                    $("#location2").text(cityname);
                    myChart3.setOption({
                        xAxis: {
                            data: timedata
                        },
                        series: [{
                            data: Temdata
                        }]

                    })
                },
// 响应失败之后执行的回调函数
                error: function (jqXHR) {
                    //alert("发生错误：" + jqXHR.status);
                }
            });

            // 右下方的空气、土壤湿度
            $.ajax({
                // 请求类型
                type:"GET",
                // 请求url
                url:"/gethumidity",
                // 请求的数据类型
                data:{cityid:city,locationid:location},
                dataType:"json",
                // 响应成功之后执行的回调函数
                success: function(data){
                    var airhum = data["airhum"];
                    var soilhum = data["soilhum"];


                    if (airhum==null||airhum==0) {
                        airhum=15;
                    }
                    if (soilhum==null||soilhum==0) {
                        soilhum=15;
                    }
                    $("#kqsd2").text(airhum);
                    $("#trsd2").text(soilhum.substring(0,4));


                    $("#kqsd").text(airhum);
                    $("#trsd").text(soilhum.substring(0,4)+"%");

                    $("#kq").text(airhum);
                    $("#tr").text(soilhum.substring(0,4)+"%");

                },
                // 响应失败之后执行的回调函数
                error:function(jqXHR){
                  //  alert("发生错误："+ jqXHR.status);
                }
            });

            infoWindow = new AMap.InfoWindow({
                isCustom: true,  //使用自定义窗体
                content: createInfoWindow(marker["B"]["title"], marker["B"]["index"], content.join("")),
                offset: new AMap.Pixel(16, -45),

            });
            infoWindow.open(map, e.target.getPosition());
        });
        //marker.emit('click',{target:marker});
    }
});

map.setFitView();

//构建自定义信息窗体(监测站标题， 监测站索引， 监测站内容)
function createInfoWindow(title, index, content) {
    closeInfoWindow();
    var info = document.createElement("div");
    info.className = "custom-info input-card content-window-card";
    //可以通过下面的方式修改自定义窗体的宽高
    info.style.width = "400px";
    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    closeX.src = "https://webapi.amap.com/images/close2.gif";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(closeX);
    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    // middle.style.backgroundColor = 'white';
    // middle.style.opacity=0.5;
    middle.innerHTML = content;


    // trsd1.innerHTML=kqwd;
    // trwd1.innerHTML=kqwd;
    // kqsd1.innerHTML=kqwd;
    // kqwd1.innerHTML=kqwd;
    info.appendChild(middle);

    // 定义底部内容
    var bottom = document.createElement("div");
    bottom.className = "info-bottom";
    bottom.style.position = 'relative';
    bottom.style.top = '0px';
    bottom.style.margin = '0px auto';
    bottom.style.opacity = 0.7;
    var sharp = document.createElement("img");
    sharp.src = "https://webapi.amap.com/images/sharp.png";
    bottom.appendChild(sharp);
    info.appendChild(bottom);
    return info;
}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}