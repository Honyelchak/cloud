/*************** 笛卡尔坐标系上的热力图 **************/
    //初始化echarts实例
const coordChart = echarts.init(document.getElementById("coordChart"));
//数据
const hours = ['0a', '1a', '2a', '3a', '4a', '5a', '6a', '7a', '8a', '9a', '10a', '11a', '12p',
    '1p', '2p', '3p', '4p', '5p', '6p', '7p', '8p', '9p', '10p', '11p'];

//配置
const coordOpt = {
    tooltip: {
        position: 'top'
    },
    grid: {
        top: 0,
        bottom: 25,
        left: 100,
        right: 10,
    },
    xAxis: {
        type: 'category',
        data: hours,
        splitArea: {
            show: true
        }
    },
    yAxis: {
        type: 'category',
        splitArea: {
            show: true
        }
    },
    axisLabel: {
        color: '#fff'
    },
    visualMap: {
        min: 0,
        max: 35,
        calculable: true,
        left: 'left',
        bottom: 20,
        itemWidth: 10,
        itemHeight: 80,
        textStyle: {
            color: '#fff'
        }
    },
    series: [{
        name: 'Coord Heatmap',
        type: 'heatmap',
        label: {
            normal: {
                show: true
            }
        },
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        }
    }]
};
//渲染图表
coordChart.setOption(coordOpt)
$.ajax({
    // 请求类型
    type:"POST",
    async : true,
    // 请求url
    url:"/weather24d/week",
    data:"101180101",
    // 请求的数据类型
    dataType:"json",
    // 响应成功之后执行的回调函数
    success: function(data){
        const days = data.y;
        var data2 = data.temperature;
        //console.log("data2:",data2)
        let coordData = data2.map(function (item) {
            return [item[1], item[0], item[2] || '-'];
        });
        coordChart.setOption({
            yAxis: {
                data: days
            },
            series: [{
                name: 'Coord Heatmap',
                data: coordData
            }]
        });
    },
    // 响应失败之后执行的回调函数
    error:function(jqXHR){
        //alert("coordChart数据请求发生错误："+ jqXHR.status);
    }
});
// $.getJSON('../assets/monitor/data/coord.json').done(function (data) {
//     let coordData = data.map(function (item) {
//         return [item[1], item[0], item[2] || '-'];
//     });
//     coordChart.setOption({
//         series: [{
//             name: 'Coord Heatmap',
//             data: coordData
//         }]
//     });
// });



/******************* 热力图 **********************/
    //初始化echarts实例
const heatmapChart = echarts.init(document.getElementById("heatmapChart"));
let hours1 = ['0a', '1a', '2a', '3a', '4a', '5a', '6a',
    '7a', '8a', '9a', '10a', '11a',
    '12p', '1p', '2p', '3p', '4p', '5p',
    '6p', '7p', '8p', '9p', '10p', '11p'];


// let data = [[0, 0, 0], [0, 1, 0], [0, 2, 0], [0, 3, 0], [0, 4, 0], [0, 5, 0], [0, 6, 0], [0, 7, 0], [0, 8, 0], [0, 9, 0], [0, 10, 0], [0, 11, 0], [0, 12, 0], [0, 13, 0], [0, 14, 0], [0, 15, 0], [0, 16, 0.2], [0, 17, 1.8], [0, 18, 1.9], [0, 19, 0.8], [0, 20, 2.0], [0, 21, 1.4], [0, 22, 0.1], [0, 23, 0],
//     [1, 0, 0], [1, 1, 0], [1, 2, 0], [1, 3, 0], [1, 4, 0], [1, 5, 0], [1, 6, 0], [1, 7, 0], [1, 8, 0], [1, 9, 0], [1, 10, 0], [1, 11, 0], [1, 12, 0], [1, 13, 0], [1, 14, 1.7], [1, 15, 2.3], [1, 16, 0.5], [1, 17, 0], [1, 18, 0.5], [1, 19, 1.2], [1, 20, 2.2], [1, 21, 0.6], [1, 22, 0.3], [1, 23, 0],
//     [2, 0, 0], [2, 1, 0], [2, 2, 0], [2, 3, 0.6], [2, 4, 1.5], [2, 5, 1.2], [2, 6, 0.3], [2, 7, 0], [2, 8, 0], [2, 9, 0], [2, 10, 0.1], [2, 11, 0], [2, 12, 0.1], [2, 13, 0], [2, 14, 0], [2, 15, 0], [2, 16, 0], [2, 17, 0], [2, 18, 0], [2, 19, 0], [2, 20, 0], [2, 21, 0], [2, 22, 0], [2, 23, 0],
//     [3, 0, 0.5], [3, 1, 0.9], [3, 2, 0], [3, 3, 1.3], [3, 4, 0.9], [3, 5, 0.6], [3, 6, 0.9], [3, 7, 1.6], [3, 8, 0], [3, 9, 0.5], [3, 10, 0], [3, 11, 0], [3, 12, 0], [3, 13, 0], [3, 14, 0], [3, 15, 0], [3, 16, 0], [3, 17, 0], [3, 18, 0], [3, 19, 0], [3, 20, 0], [3, 21, 0], [3, 22, 0], [3, 23, 0],
//     [4, 0, 0], [4, 1, 0], [4, 2, 0], [4, 3, 0], [4, 4, 0], [4, 5, 0], [4, 6, 0], [4, 7, 0], [4, 8, 0], [4, 9, 0], [4, 10, 0], [4, 11, 0], [4, 12, 0], [4, 13, 0.6], [4, 14, 1], [4, 15, 1.6], [4, 16, 0.5], [4, 17, 0.8], [4, 18, 0.1], [4, 19, 0], [4, 20, 0], [4, 21, 0], [4, 22, 0], [4, 23, 0],
//     [5, 0, 0], [5, 1, 0], [5, 2, 0], [5, 3, 0], [5, 4, 0], [5, 5, 0], [5, 6, 0], [5, 7, 0.1], [5, 8, 0], [5, 9, 0.1], [5, 10, 0.2], [5, 11, 0.4], [5, 12, 0.6], [5, 13, 0.4], [5, 14, 0.1], [5, 15, 1.8], [5, 16, 1.7], [5, 17, 0], [5, 18, 0], [5, 19, 0], [5, 20, 0], [5, 21, 0], [5, 22, 0], [5, 23, 0],
//     [6, 0, 0], [6, 1, 0], [6, 2, 0], [6, 3, 0], [6, 4, 0], [6, 5, 0], [6, 6, 0], [6, 7, 0], [6, 8, 0], [6, 9, 0], [6, 10, 0], [6, 11, 0], [6, 12, 0], [6, 13, 0], [6, 14, 0.2], [6, 15, 0.4], [6, 16, 0.8], [6, 17, 1.7], [6, 18, 0.6], [6, 19, 0.1], [6, 20, 0], [6, 21, 0], [6, 22, 0], [6, 23, 0]];
var  data1 = [];
var days2 = [];

$.ajax({
    // 请求类型
    type:"POST",
    async : true,
    // 请求url
    url:"/weather24d/rain",
    data:"101180101",
    // 请求的数据类型
    dataType:"json",
    // 响应成功之后执行的回调函数
    success: function(result) {
        days2 = result.y;
        data1 = result.rain;
        option = {
            tooltip: {
                position: 'top'
            },
            title: [],
            singleAxis: [],
            series: [],
            textStyle: {
                color: '#fff'
            }
        };
        var c = days2.length;
        //console.log("data1:",data1);
        //console.log("data1.y:",days2);
        echarts.util.each(days2, function (day, idx) {
            option.title.push({
                textBaseline: 'middle',
                top: (idx + 0.5) * 100 / c + '%',
                text: day,
                textStyle: {
                    color: '#1D81B6',
                    fontSize: 14
                },

            });
            option.singleAxis.push({
                left: 75,
                type: 'category',
                boundaryGap: false,
                data: hours1,
                bottom: (idx * 100 / c + 5) + '%',
                height: (100 / c - 10) + '%',
                axisLabel: {
                    interval: 1,
                },
                /* lineStyle{
                    color:'#fff'
                }  */
            });

            option.series.push({
                singleAxisIndex: idx,
                coordinateSystem: 'singleAxis',
                type: 'scatter',
                data: [],
                symbolSize: function (dataItem) {
                    return dataItem[1] * 1;
                }
            });
        });

        echarts.util.each(data1, function (dataItem) {
            option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
        });


        heatmapChart.setOption(option);
    },
    // 响应失败之后执行的回调函数
    error:function(jqXHR){
        //alert ("heatmapData数据请求发生错误："+ jqXHR.status);
    }
});






// 新郑
var imageLayer1 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Xinzheng.png',
    bounds: new AMap.Bounds(
        [113.355000,34.11054],   ////左下角。。经度向左移减小。。纬度向上增加
        [114.047302,34.682935]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});

// 长葛
var imageLayer2 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Changge.png',
    bounds: new AMap.Bounds(
        [113.52706,33.913021],   ////左下角。。经度向左移减小。。纬度向上增加
        [114.18823,34.444005]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
// 滑县
var imageLayer3 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Huaxian.png',
    bounds: new AMap.Bounds(
        [114.227076,34.990139],   ////左下角。。经度向左移减小。。纬度向上增加
        [115.122163,35.730184]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
// 临颍
var imageLayer4 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/linying.png',
    bounds: new AMap.Bounds(
        [113.66933,33.590617],   ////左下角。。经度向左移减小。。纬度向下减小
        [114.195769,34.017586]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//夏邑
var imageLayer5 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Xiayi.png',
    bounds: new AMap.Bounds(
        [115.790557,33.830264],   ////左下角。。经度向左移减小。。纬度向下减小
        [116.591125,34.47939]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//西平
var imageLayer6 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Xiping.png',
    bounds: new AMap.Bounds(
        [113.57383,33.04188],   ////左下角。。经度向左移减小。。纬度向上增加
        [114.237767,33.579758]   ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//修武    图片与地图偏差太多
var imageLayer7 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Xiuwu.png',
    bounds: new AMap.Bounds(
        [113.206308,34.991138],   ////左下角。。经度向左移减小。。纬度向上增加
        [113.588645,35.525296]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//息县
var imageLayer8 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Xixian.png',
    bounds: new AMap.Bounds(
        [114.420752,31.958114],   ////左下角。。经度向左移减小。。纬度向上增加
        [115.290246,32.717467]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//祥符   图片差太多
var imageLayer9 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Xiangfu.png',
    bounds: new AMap.Bounds(
        [114.124353,34.345158],   ////左下角。。经度向左移减小。。纬度向上增加
        [114.788401,34.968353]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//郾城
var imageLayer10 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Yancheng.png',
    bounds: new AMap.Bounds(
        [113.686981,33.428248],   ////左下角。。经度向左移减小。。纬度向上增加
        [114.175031,33.797486]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//延津
var imageLayer11 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Yanjin.png',
    bounds: new AMap.Bounds(
        [113.877298,34.96875],   ////左下角。。经度向左移减小。。纬度向上增加
        [114.555833,35.515142]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//伊川
var imageLayer12 = new AMap.ImageLayer({
    url: '../assets/monitor/images/sense/Yichuan.png',
    bounds: new AMap.Bounds(
        [112.165044,34.063879],   ////左下角。。经度向左移减小。。纬度向上增加
        [112.790114,34.588285]    ////右上角
    ),
    zIndex: 9998,
    zooms: [10, 12]
});
//创建地图
var map = new AMap.Map('main', {
    cursor: 'default',
    resizeEnable: true,
    zoom: 8,
    center: [113.937209, 34.105203],
    mapStyle: 'amap://styles/eaa3ec8faf0979d854c8e22637144b7a'
});


// 构造官方卫星、路网图层
var satelliteLayer = new AMap.TileLayer.Satellite();
var roadNetLayer = new AMap.TileLayer.RoadNet();

//批量添加图层
//map.add([satelliteLayer, roadNetLayer, imageLayer]);
map.add([roadNetLayer, imageLayer1, imageLayer2, imageLayer3,imageLayer4, imageLayer5, imageLayer6, imageLayer7,imageLayer8, imageLayer9,imageLayer10, imageLayer11, imageLayer12,]);
//map.add(imageLayer);
// 自动适配到合适视野范围
// map.setFitView();

// 异步加载plugins，加载后执行回调函数
AMap.plugin(['AMap.ToolBar'], function () {//异步同时加载多个插件
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
        zIndex: 12,
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
                    fillStyle: 'rgba(255, 255, 255, 0.1)',//鼠标移到上面显示选中的模糊程度改变最后那个指标

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
            },
            //显示在所辖数据点的平均位置
            getClusterMarkerPosition: DistrictCluster.ClusterMarkerPositionStrategy.AVERAGE_POINTS_POSITION,
            getClusterMarker: function(feature, dataItems, recycledMarker) {

                //label内容
                var content =feature.properties.name;

                var label = {
                    offset: new AMap.Pixel(16, 18), //修改label相对于marker的位置
                    content: content,
                    fillColor: "#ee2200", //填充颜色
                    // fillOpacity: 0.35//填充透明度
                };

                //存在可回收利用的marker
                if (recycledMarker) {
                    //直接更新内容返回
                    recycledMarker.setLabel(label);
                    return recycledMarker;
                }

                //返回一个新的Marker
                return new AMap.Marker({
                    label: label
                });
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

var city = {
    "Stations": [
        {
            "namep":"Xinzheng",
            "name":"郑州市",
            "Longitude": "113.683462",
            "Latitude": "34.822446"
        },
        {
            "namep":"Huaxian",
            "name":"安阳市",
            "Longitude": "114.352482",
            "Latitude": "36.103442"
        },
        {
            "namep":"Yanjin",
            "name":"新乡市",
            "Longitude": "113.883991",
            "Latitude": "35.302616"
        },
        {
            "namep":"Changge",
            "name":"许昌市",
            "Longitude": "113.826063",
            "Latitude": "34.022956"
        },
        {
            "namep":"Yichuan",//无图片临近表示
            "name":"平顶山",
            "Longitude": "113.041396",
            "Latitude": "34.627459"
        },
        {
            "namep":"Xixian",
            "name":"信阳市",
            "Longitude": "114.075031",
            "Latitude": "32.123274"
        },
        {
            "namep":"Xixian",//无图片临近表示
            "name":"南阳市",
            "Longitude": "112.540918",
            "Latitude": "32.999082"
        },
        {
            "namep":"Xiangfu",
            "name":"开封市",
            "Longitude": "114.341447",
            "Latitude": "34.797049"
        },
        {
            "namep":"Yichuan",
            "name":"洛阳市",
            "Longitude": "112.434468",
            "Latitude": "34.663041"
        },
        {
            "namep":"Xiayi",
            "name":"商丘市",
            "Longitude": "115.650497",
            "Latitude": "34.437054"
        },
        {
            "namep":"Xiuwu",
            "name":"焦作市",
            "Longitude": "113.238266",
            "Latitude": "35.23904"
        },
        {
            "namep":"Huaxian",//无图片附近表示
            "name":"鹤壁市",
            "Longitude": "114.295444",
            "Latitude": "35.748236"
        },
        {
            "namep":"Huaxian",//无图片附近表示
            "name":"濮阳市",
            "Longitude": "115.041299",
            "Latitude": "35.768234"
        },
        {
            "namep":"Linying",//无图片附近表示
            "name":"周口市",
            "Longitude": "114.649653",
            "Latitude": "33.620357"
        },
        {
            "namep":"Yancheng",
            "name":"漯河市",
            "Longitude": "114.026405",
            "Latitude": "33.575855"
        },
        {
            "namep":"Xiping",
            "name":"驻马店市",
            "Longitude": "114.024736",
            "Latitude": "32.980169"
        },
        {
            "namep":"Yichuan",//无图片附近表示
            "name":"三门峡市",
            "Longitude": "111.194099",
            "Latitude": "34.777338"
        },
        {
            "namep":"Yichuan",//无图片附近表示
            "name":"济源市",
            "Longitude": "112.601919",
            "Latitude": "35.067243"
        },
        {
            "namep":"Xinzheng",
            "name":"新郑市",
            "Latitude":"34.3960500000",
            "Longitude": "113.7416100000"
        },
        {
            "namep":"Huaxian",
            "name": "滑县",
            "Latitude": "35.5753400000",
            "Longitude": "114.5193000000"
        },
        {
            "namep":"Xiangfu",
            "name":"开封祥符",
            "Latitude":"34.7570000000",
            "Longitude": "114.4413600000"
        },
        {
            "namep":"Changge",
            "name":"长葛",
            "Latitude":"34.2087020000",
            "Longitude": "113.7760620000"
        },
        {
            "namep":"Xiping",
            "name":"西平",
            "Latitude":"40.0452700000",
            "Longitude": "116.5913000000"
        },
        {
            "namep":"Yichuan",
            "name":"伊川",
            "Latitude":"34.42135000000",
            "Longitude": "112.4256800000"
        },
        {
            "namep":"Xiuwu",
            "name":"修武",
            "Latitude":"35.2236100000",
            "Longitude": "113.4477600000"
        },
        {
            "namep":"Linying",
            "name":"临颍县",
            "Latitude":"33.8095800000",
            "Longitude": "113.9344300000"
        },
    ]
};

var infoWindow, title, content,content1,forecast;
content1 = [],content = [], markers = [], markers2 = [],forecast="";

content1.push("<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp对我市小麦生产和收获产生不利影响。为此各级农业部门要高度重视，进一步增强忧患意识，切实加强夏粮后期田间管理，加大防灾减灾力度</p>");
// content.push("<li>土壤湿度：43.2%</li>");
// content.push("<li>土壤温度：7℃</li>");
// content.push("<li>空气湿度：36.8%</li>");
// content.push("<li>空气温度：28℃</li></ul>");
//content.push("<a href='https://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");



// 添加marker
AMapUI.loadUI(['overlay/AwesomeMarker'], function (AwesomeMarker) {
    map.remove(markers);
    var points = city["Stations"];
    //console.log("points：",points);
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
            position: new AMap.LngLat(points[i]["Longitude"], points[i]["Latitude"]),
            title: points[i]["name"],
            namep:points[i]["namep"]
        });
        markers.push(marker);

        AMap.event.addListener(marker, 'click', function (e) {
            var marker = e.target;
            var cc=marker["B"]["namep"];
            // console.log(cc);
            content="<img src=\"../assets/monitor/images/"+cc+".png/\">";
            //console.log(content);
            infoWindow = new AMap.InfoWindow({
                isCustom: true,  //使用自定义窗体
                content: createInfoWindow(marker["B"]["title"], marker["B"]["index"], content,cc),
                offset: new AMap.Pixel(16, -45)
            });

            infoWindow.open(map, e.target.getPosition());
        });
        //marker.emit('click',{target:marker});
    }
    var a = new Date();
    var timeStr = a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate() ;
    // 左侧温度排行异步请求
    $.ajax({
        // 请求类型
        type: "POST",
        // 请求url
        url: "/forecast/getToday",
        // 请求的数据类型
        dataType: "json",
        data: {forecasTime: timeStr},
        // 响应成功之后执行的回调函数
        success: function (data) {
            //  AMapUI.loadUI(['overlay/AwesomeMarker'], function (AwesomeMarker) {
            //console.log("data:",data["data"]);

            //markers2.clear();
            var points = data["data"];
            var p = city["Stations"];
            var deleteMarker = [];
            $(".inform .notice ul").html();
            for(var i = 0; i < points.length; i++) {
                var markerContent, index = points[i][1][6] - '0', hoverString = "";
                // 101180101
                if(points[i][3] == 0)continue;
                if(!(points[i][1][8] == 1 && points[i][1][7] == 0))continue;
                if(points[i][1][5] != 0){
                    index += 10;
                }
                index -= 1;
                var d = new Date(points[i][4]);
                hoverString = "警告["+d.getFullYear() + "-"+(d.getMonth() + 1)+ "-"+d.getDate()+ "-"+d.getHours()+ "-"+d.getMinutes()+ "-"+d.getSeconds()+"]"
                // 根据灾害的级别不同设置不同的样式
                hoverString += "预计<b>" + p[index]["name"] + "</b>未来将会发生";
                // 根据灾害种类不同设置不同的样式
                if (points[i][2] == 2) {
                    if (points[i][3] == 1) {
                        markerContent = "<div id='c'><div class='y'></div></div>";
                        hoverString += "轻度";
                    } else if (points[i][3] == 2) {
                        markerContent = "<div id='c'><div class='s'></div></div>";
                        hoverString += "重度";
                    }
                    hoverString += "热干风";
                } else if (points[i][2] == 1) {
                    if (points[i][3] == 1) {
                        markerContent = "<div id='c'><div class='y'></div></div>";
                        hoverString += "轻度";
                    } else if (points[i][3] == 2) {
                        markerContent = "<div id='c'><div class='s'></div></div>";
                        hoverString += "重度";
                    }
                    hoverString += "晚霜冻害";
                }
                hoverString += "灾害,请注意防范!";
                $(".inform .notice ul").append("<li><span>" + hoverString + "</span></li>");

                // Title=hoverString;
                var marker = new AMap.Marker({
                    map: map,
                    position: new AMap.LngLat(p[index]["Longitude"], p[index]["Latitude"]),
                    title: hoverString,
                    content: markerContent
                });

                markers2.push(marker);
                /*console.log(markers);
                console.log(index);
                console.log(markers[index]);*/
                if(markers[index] != null){
                    markers[index].setMap(null);
                }
            }
            // 添加新的marker
            AMapUI.loadUI(['overlay/AwesomeMarker'], function (AwesomeMarker) {
                for (var i = 0, len = points.length; i < len; i++) {
                    var index = points[i][1][6] - '0', imgSrc;
                    // console.log("点击显示内容"+index);
                    if(points[i][3] == 0)continue;
                    if(!(points[i][1][8] == 1 && points[i][1][7] == 0))continue;
                    if(points[i][1][5] != 0){
                        index += 10;
                    }
                    index -= 1;

                    if(points[i][2] == 1){
                        imgSrc = "../assets/monitor/images/blue.png";
                    } else {
                        imgSrc = "../assets/monitor/images/red.png";
                    }
                    if(points[i][3]==1){
                        forecast="轻度热干风灾害"
                    }else {
                        forecast = "重度热干风灾害"
                    }
                    // console.log(points[i]["name"]);
                    var marker = new AwesomeMarker({
                        iconLabel: {
                            style: {
                                color: '#333',
                                fontSize: '14px',
                            }
                        },
                        iconStyle: {
                            src: imgSrc,
                            style: {
                                width: '37px',
                                height: '43px'
                            }
                        },
                        map: map,
                        position: new AMap.LngLat(p[index]["Longitude"], p[index]["Latitude"]),
                        offset: new AMap.Pixel(-17, -39),
                        title: p[index]["name"] + points[i][1],
                        city:points[i][1],
                        chyj:forecast
                    });
                    markers.push(marker);
                    AMap.event.addListener(marker, 'click', function (e) {
                        var marker = e.target;
                        //console.log(marker["B"]["city"]);
                        var cityID = marker["B"]["city"];
                        $.ajax({
                            url:"/weather24d/week",
                            type:"post",
                            //注意序列化的值一定要放在最前面,并且不需要头部变量,不然获取的值得格式会有问题
                            data:cityID,
                            dataType:"json",
                            success:function (data) {
                                //console.log(data);
                                const days = data.y;
                                // console.log(days);
                                var data2 = data.temperature;
                                //console.log("data2:",data2)
                                let coordData = data2.map(function (item) {
                                    return [item[1], item[0], item[2] || '-'];
                                });
                                coordChart.setOption({
                                    yAxis: {
                                        data: days
                                    },
                                    series: [{
                                        name: 'Coord Heatmap',
                                        data: coordData
                                    }]
                                });
                            }
                        });
                        $.ajax({
                            // 请求类型
                            type:"POST",
                            async : true,
                            // 请求url
                            url:"/weather24d/rain",
                            data:cityID,
                            // 请求的数据类型
                            dataType:"json",
                            // 响应成功之后执行的回调函数
                            success: function(result) {
                                days2 = result.y;
                                data1 = result.rain;
                                option = {
                                    tooltip: {
                                        position: 'top'
                                    },
                                    title: [],
                                    singleAxis: [],
                                    series: [],
                                    textStyle: {
                                        color: '#fff'
                                    }
                                };
                                var c = days2.length;
                                //console.log("data1:",data1);
                                //console.log("data1.y:",days2);
                                echarts.util.each(days2, function (day, idx) {
                                    option.title.push({
                                        textBaseline: 'middle',
                                        top: (idx + 0.5) * 100 / c + '%',
                                        text: day,
                                        textStyle: {
                                            color: '#1D81B6',
                                            fontSize: 14
                                        },

                                    });
                                    option.singleAxis.push({
                                        left: 75,
                                        type: 'category',
                                        boundaryGap: false,
                                        data: hours1,
                                        bottom: (idx * 100 / c + 5) + '%',
                                        height: (100 / c - 10) + '%',
                                        axisLabel: {
                                            interval: 1,
                                        },
                                        /* lineStyle{
                                            color:'#fff'
                                        }  */
                                    });

                                    option.series.push({
                                        singleAxisIndex: idx,
                                        coordinateSystem: 'singleAxis',
                                        type: 'scatter',
                                        data: [],
                                        symbolSize: function (dataItem) {
                                            return dataItem[1] * 1;
                                        }
                                    });
                                });

                                echarts.util.each(data1, function (dataItem) {
                                    option.series[dataItem[0]].data.push([dataItem[1], dataItem[2]]);
                                });


                                heatmapChart.setOption(option);
                            },
                            // 响应失败之后执行的回调函数
                            error:function(jqXHR){
                                //alert("heatmapData数据请求发生错误："+ jqXHR.status);
                            }
                        });
                        //aj();
                        infoWindow = new AMap.InfoWindow({
                            isCustom: true,  //使用自定义窗体
                            content: openwindow(marker["B"]["title"], marker["B"]["index"], content1.join(""), marker["B"]["chyj"]),
                            offset: new AMap.Pixel(16, -45)
                        });
                        infoWindow.open(map, e.target.getPosition());
                    });
                }
            });
        },
        // 响应失败之后执行的回调函数
        error: function (jqXHR) {

            console.log("staring error!");
            //alert("发生错误：" + jqXHR.status);
        }
    });
});

// function aj(){
//
// }




//删除




map.setFitView();

//构建自定义信息窗体(监测站标题， 监测站索引， 监测站内容)
function createInfoWindow(title, index, content,cc) {
    closeInfoWindow();
    var info = document.createElement("div");
    info.className = "custom-info input-card content-window-card";
    //可以通过下面的方式修改自定义窗体的宽高
    info.style.width = "550px";
    info.style.height = "400px";

    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var midtitle = document.createElement("div");
    var rImg = document.createElement("img");
    var lImg = document.createElement("img");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    midtitle.innerHTML=title;


    lImg.src = "../assets/monitor/images/limg.png";
    rImg.src = "../assets/monitor/images/rimg.png";
    lImg.style.width="25px";
    lImg.style.height="25px";
    lImg.style.left="80px";
    lImg.style.top="5px";
    rImg.style.width="25px";
    rImg.style.height="25px";
    rImg.style.right="35px";
    rImg.style.top="5px";
    rImg.onclick=clickr;
    lImg.onclick=clickl;
    //切换图片
    var currentindex=0;
    function clickr(){
        currentindex++;
        change();
    }
    function clickl(){

        currentindex--;
        change();
    }

    // function change(){
    //     var imgsrc2=["../assets/monitor/images/"+namep+".png"];
    //     var midtitlecon=new Array(title);
    //
    //     if(currentindex>1){
    //         currentindex=0;
    //     }
    //     if(currentindex<0){
    //         currentindex=1;
    //     }
    //
    //     middle.firstChild.src=imgsrc2[currentindex];
    //
    //     midtitle.innerHTML=midtitlecon[currentindex];
    //
    // }

    midtitle.style.width="80%";
    midtitle.style.textAlign="center";
    closeX.src = "https://webapi.amap.com/images/close2.gif";
    closeX.style.width="15px";
    closeX.style.height="15px";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(lImg);
    top.appendChild(midtitle);
    top.appendChild(rImg);
    top.appendChild(closeX);

    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    // middle.style.backgroundColor = 'white';
    // middle.style.opacity=0.5;
    // content=("<img src=\"../assets/monitor/images/\"+cc+\".png/\">")
    middle.innerHTML = content;
    middle.style.height="366px";
    middle.firstChild.style.height="350px";
    middle.firstChild.style.width="530px";
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
    // sharp.style.height="400px";
    bottom.appendChild(sharp);
    info.appendChild(bottom);
    return info;


}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}
function openwindow(title, index, content,chyj) {
    closeInfoWindow();
    var info = document.createElement("div");
    info.className = "custom-info input-card content-window-card";
    //可以通过下面的方式修改自定义窗体的宽高
    info.style.width = "275px";
    info.style.height = "200px";

    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var midtitle = document.createElement("div");
    var rImg = document.createElement("img");
    var lImg = document.createElement("img");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    midtitle.innerHTML=chyj;

    midtitle.style.width="80%";
    midtitle.style.textAlign="center";
    closeX.src = "https://webapi.amap.com/images/close2.gif";
    closeX.style.width="15px";
    closeX.style.height="15px";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(lImg);
    top.appendChild(midtitle);
    top.appendChild(rImg);
    top.appendChild(closeX);

    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    middle.style.backgroundColor = 'gray';
    middle.style.fontSize="18px";
    middle.style.lineHeight="24px";
    // middle.style.opacity=0.5;
    middle.innerHTML = content;

    middle.style.height="135px";
    middle.firstChild.style.height="175px";
    middle.firstChild.style.width="265px";
    info.appendChild(middle);

    return info;


}