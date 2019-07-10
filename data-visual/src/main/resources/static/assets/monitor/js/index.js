 //创建地图
    var map = new AMap.Map('main', {
        cursor: 'default',
        zoom: 8,
        center: [113.937209,34.105203],
        mapStyle: 'amap://styles/eaa3ec8faf0979d854c8e22637144b7a'
    });
    

      // 构造官方卫星、路网图层
    var satelliteLayer = new AMap.TileLayer.Satellite();
    var roadNetLayer =  new AMap.TileLayer.RoadNet();

    //批量添加图层
    map.add([satelliteLayer, roadNetLayer]);
    // 自动适配到合适视野范围
    // map.setFitView();

    // 异步加载plugins，加载后执行回调函数
    AMap.plugin(['AMap.ToolBar','AMap.Scale'],function(){//异步同时加载多个插件
        console.log("success load in plugins");
        var toolbar = new AMap.ToolBar();
        map.plugin(toolbar);
        var scale = new AMap.Scale();
        map.plugin(scale);
    });



    //map.plugin(new AMap.OverView);
    //map.plugin(new AMap.CitySearch);
    // 
    function initPage(DistrictCluster, $) {

            var distCluster = new DistrictCluster({
                map: map, //所属的地图实例
                zIndex:11,
                topAdcodes:[410000],
                //排除3个省
                //excludedAdcodes:[130000, 610000, 340000],
                autoSetFitView: false,
                getPosition: function(item) {
                    if (!item) {
                        return null;
                    }
                    var parts = item.split(',');
                    //返回经纬度
                    return [parseFloat(parts[0]), parseFloat(parts[1])];
                },
                renderOptions:{
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
            $.get('https://a.amap.com/amap-ui/static/data/10w.txt', function(csv) {

                $('#loadingTip').remove();

                var data = csv.split('\n');

                distCluster.setData(data);
            });
    }

    //加载相关组件
    AMapUI.load(['ui/geo/DistrictCluster', 'lib/$'], function(DistrictCluster, $) {

        window.DistrictCluster = DistrictCluster;

        //启动页面
        initPage(DistrictCluster, $);
    });

    var a = {
  "Stations": {
    "Station": [
      {
        "Index": "22239",
        "Name": "许昌长葛1号监测站",
        "Province": "河南省",
        "Longitude": "34.248983",
        "Latitude": "113.804696"
      },
      {
        "Index": "22240",
        "Name": "许昌长葛3号监测站",
        "Province": "河南省",
        "Longitude": "34.238588",
        "Latitude": "113.839672"
      },
      {
        "Index": "22241",
        "Name": "许昌长葛2号监测站",
        "Province": "河南省",
        "Longitude": "34.219219",
        "Latitude": "113.813316"
      },
      {
        "Index": "22308",
        "Name": "许昌长葛5号监测站",
        "Province": "河南省",
        "Longitude": "34.217578",
        "Latitude": "113.817168"
      },
      {
        "Index": "22309",
        "Name": "许昌长葛4号监测站",
        "Province": "河南省",
        "Longitude": "34.222244",
        "Latitude": "113.837724"
      },
      {
        "Index": "22319",
        "Name": "许昌长葛6号监测站",
        "Province": "河南省",
        "Longitude": "34.243533",
        "Latitude": "113.84635"
      },
      {
        "Index": "22477",
        "Name": "许昌长葛9号监测站",
        "Province": "河南省",
        "Longitude": "34.238488",
        "Latitude": "113.786655"
      },
      {
        "Index": "22478",
        "Name": "许昌长葛8号监测站",
        "Province": "河南省",
        "Longitude": "34.2149462304",
        "Latitude": "113.7825159271"
      },
      {
        "Index": "22479",
        "Name": "许昌长葛7号监测站",
        "Province": "河南省",
        "Longitude": "34.1149462304",
        "Latitude": "113.7825159271"
      },
      {
        "Index": "22772",
        "Name": "原阳小吴庄农业物联网监控站",
        "Province": "河南省",
        "Longitude": "34.9928541445837",
        "Latitude": "113.937209"
      },
      {
        "Index": "22790",
        "Name": "河南安阳内黄农业物联网网监控站",
        "Province": "河南省",
        "Longitude": "35.964401",
        "Latitude": "114.867503"
      },
      {
        "Index": "21015",
        "Name": "河南省汤阴县农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.84",
        "Latitude": "114.35107777778"
      },
      {
        "Index": "22370",
        "Name": "河南滑县小铺物联网监控站",
        "Province": "河南省",
        "Longitude": "35.5370973177",
        "Latitude": "114.4954919729"
      },
      {
        "Index": "22371",
        "Name": "河南滑县小铺物联网监控站_图像2",
        "Province": "河南省",
        "Longitude": "35.531379",
        "Latitude": "114.489022"
      },
      {
        "Index": "21361",
        "Name": "滑县留固小营农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.5899375876",
        "Latitude": "114.6677959041"
      },
      {
        "Index": "21380",
        "Name": "滑县白马坡农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.619836",
        "Latitude": "114.737096"
      },
      {
        "Index": "21386",
        "Name": "河南许昌绰韩村物联网监控站",
        "Province": "河南省",
        "Longitude": "34.13362",
        "Latitude": "113.955161"
      },
      {
        "Index": "21388",
        "Name": "河南方城县席庄村物联网监测站",
        "Province": "河南省",
        "Longitude": "33.260318",
        "Latitude": "113.019912"
      },
      {
        "Index": "21390",
        "Name": "河南省尉氏县物联网监控站",
        "Province": "河南省",
        "Longitude": "34.4173376",
        "Latitude": "114.199531"
      },
      {
        "Index": "22218",
        "Name": "河南浚县农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.6761271206",
        "Latitude": "114.5508016973"
      },
      {
        "Index": "22249",
        "Name": "河南南阳新野县农业物联网监测站",
        "Province": "河南省",
        "Longitude": "32.518026",
        "Latitude": "112.329642"
      },
      {
        "Index": "20567",
        "Name": "安阳市滑县白马坡作物物联网监控站",
        "Province": "河南省",
        "Longitude": "35.5756405083",
        "Latitude": "114.5136086236"
      },
      {
        "Index": "20730",
        "Name": "洛阳市汝阳县小麦监控站",
        "Province": "河南省",
        "Longitude": "34.15981542673",
        "Latitude": "112.47964938323"
      },
      {
        "Index": "20736",
        "Name": "安阳市滑县老店农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.4433500528",
        "Latitude": "114.563892285"
      },
      {
        "Index": "20741",
        "Name": "安阳市滑县上官镇农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.4430226822",
        "Latitude": "114.6336050138"
      },
      {
        "Index": "20747",
        "Name": "安阳市滑县大寨乡农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.4967464213",
        "Latitude": "114.9200000138"
      },
      {
        "Index": "20788",
        "Name": "安阳市滑县东留固农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.4517480866768",
        "Latitude": "114.688487"
      },
      {
        "Index": "20794",
        "Name": "安阳市滑县八里营农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.524514872",
        "Latitude": "114.7971424457"
      },
      {
        "Index": "20912",
        "Name": "许昌市农技站长葛农科所监控站",
        "Province": "河南省",
        "Longitude": "34.216688",
        "Latitude": "113.76833"
      },
      {
        "Index": "20913",
        "Name": "许昌市农技站五女店小麦监控站",
        "Province": "河南省",
        "Longitude": "34.068661",
        "Latitude": "114.012573"
      },
      {
        "Index": "20980",
        "Name": "滑县高平镇门头村农业物联网监控站",
        "Province": "河南省",
        "Longitude": "35.37346",
        "Latitude": "114.777966"
      },
      {
        "Index": "21263",
        "Name": "河南安阳县瓦店2农业物联网监控站",
        "Province": "河南省",
        "Longitude": "36.034905",
        "Latitude": "114.55652"
      },
      {
        "Index": "21346",
        "Name": "商丘柘城申桥乡物联网监控站",
        "Province": "河南省",
        "Longitude": "34.4155351343",
        "Latitude": "115.6508714715"
      },
      {
        "Index": "22359",
        "Name": "商丘柘城牛城监测站",
        "Province": "河南省",
        "Longitude": "34.14055",
        "Latitude": "115.31435"
      },
      {
        "Index": "22360",
        "Name": "周口西华县黄楼村监测站",
        "Province": "河南省",
        "Longitude": "33.621331",
        "Latitude": "114.567596"
      },
      {
        "Index": "22361",
        "Name": "河南西平县二郎乡监测站",
        "Province": "河南省",
        "Longitude": "33.303965",
        "Latitude": "114.010816"
      },
      {
        "Index": "22369",
        "Name": "河南方城席庄物联网监控站1",
        "Province": "河南省",
        "Longitude": "33.120448",
        "Latitude": "112.814726"
      },
      {
        "Index": "22482",
        "Name": "郑州毛庄农业物联网监测站",
        "Province": "河南省",
        "Longitude": "34.870144",
        "Latitude": "113.624627"
      },
      {
        "Index": "22487",
        "Name": "河南省兰考县爪营乡朱场村物联网监控站",
        "Province": "河南省",
        "Longitude": "34.906328",
        "Latitude": "114.866269"
      }
    ]
  }
};


    var infoWindow,title,content;
    content = [];
    
    content.push("<img src='../assets/monitor/images/wheat.gif'><ul><li>土壤湿度：43.2%</li>");
    content.push("<li>土壤湿度：43.2%</li>");
    content.push("<li>土壤温度：7℃</li>");
    content.push("<li>空气湿度：36.8%</li>");
    content.push("<li>空气温度：28℃</li></ul>");
    //content.push("<a href='https://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");
    

    // 添加marker
     AMapUI.loadUI(['overlay/AwesomeMarker'], function(AwesomeMarker) {
        var points = a["Stations"]["Station"];
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
                    "index": points[i]["Index"],
                    iconStyle: 'orange',
                    map: map,
                    position: new AMap.LngLat(points[i]["Latitude"], points[i]["Longitude"]),
                    title: points[i]["Name"]
            });
            
            AMap.event.addListener(marker, 'click', function(e){
                console.log("nice");
                var marker = e.target;
                infoWindow = new AMap.InfoWindow({
                    isCustom: true,  //使用自定义窗体
                    content: createInfoWindow(marker["B"]["title"], marker["B"]["index"], content.join("")),
                    offset: new AMap.Pixel(16, -45)
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
        info.appendChild(middle);

        // 定义底部内容
        var bottom = document.createElement("div");
        bottom.className = "info-bottom";
        bottom.style.position = 'relative';
        bottom.style.top = '0px';
        bottom.style.margin = '0px auto';
        bottom.style.opacity=0.7;
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