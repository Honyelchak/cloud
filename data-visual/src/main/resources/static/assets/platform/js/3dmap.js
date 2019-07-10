 let geoCoordMap = {
        /*"三门峡市":  [111.194099, 34.777338],
        "信阳市":  [114.075031, 32.123274],
        "南阳市":  [112.540918, 32.999082],
        "周口市":  [114.649653, 33.620357],
        "商丘市":  [115.650497, 34.437054],
        "安阳市":  [114.352482, 36.103442],
        "平顶山市":  [113.307718, 33.735241],
        "开封市":  [114.341447, 34.797049],
        "新乡市":  [113.883991, 35.302616],
        "洛阳市":  [112.434468, 34.663041],
        "济源市":  [112.590047, 35.090378],
        "漯河市":  [114.026405, 33.575855],
        "濮阳市":  [115.041299, 35.768234],
        "焦作市":  [113.238266, 35.23904],
        "许昌市":  [113.826063, 34.022956],
        "郑州市":  [113.665412, 34.757975],
        "驻马店市":  [114.024736, 32.980169],
        "鹤壁市":  [114.295444, 35.748236],
        "宁陵县":[115.320055,34.449299],
		 "柘城县":[115.307433,34.075277],
		 "梁园区": [115.65459,34.436553],
		"睢阳区":[115.653813,34.390536],
		 "夏邑县":[116.13989,34.240894],
		 "永城市":[116.449672,33.931318],
		 "民权县":[115.148146,34.648455],
		 "睢县":[115.070109,34.428433],
		 "虞城县":[115.863811,34.399634],
		 "西峡县":[111.485772,33.302981],
		 "卧龙区":[112.528789,32.989877],
		 "南召县":[112.435583,33.488617],
		 "淅川县":[111.489026,33.136106],
		 "社旗县":[112.938279,33.056126],
		 "桐柏县":[113.406059,32.367153],
		 "唐河县":[112.838492,32.687892],
		 "内乡县":[111.843801,33.046358],
		 "宛城区":[112.544591,32.994857],
		 "镇平县":[112.232722,33.036651],
		 "新野县":[112.365624,32.524006],
		 "方城县":[113.010933,33.255138],
		 "邓州市":[112.092716,32.681642],*/
		 "南乐县": [115.204336,36.075204],
		 "台前县": [115.855681,35.996474],
		 "清丰县": [115.107287,35.902413],
		 "范县": [115.504212,35.851977],
		 "濮阳县": [115.023844,35.710349],
		 "华龙区": [115.03184,35.760473],
		 "新安县": [112.141403,34.728679],
		 "栾川县": [111.618386,33.783195],
		 "汝阳县": [112.473789,34.15323],
		 "涧西区": [112.399243,34.654251],
		 "伊川县": [112.429384,34.423416],
		 "宜阳县": [112.179989,34.516478],
		 "吉利区": [112.584796,34.899093],
		 "洛宁县": [111.655399,34.387179],
		 "偃师市": [112.787739,34.723042],
		 "瀍河回族区": [112.491625,34.684738],
		 "洛龙区": [112.456634,34.618557],
		 "老城区": [112.477298,34.682945],
		 "西工区": [112.443232,34.667847],
		 "嵩县": [112.087765,34.131563],
		 "孟津县": [112.443892,34.826485],
		 "义马市": [111.869417,34.746868],
		 "湖滨区": [111.19487,34.77812],
		 "渑池县": [111.762992,34.763487],
		 "卢氏县": [111.052649,34.053995],
		 "灵宝市": [110.88577,34.521264],
		 "陕州区": [111.103851,34.720244],
		 "临颍县": [113.938891,33.80609],
		 "源汇区": [114.017948,33.565441],
		 "舞阳县": [113.610565,33.436278],
		 "召陵区": [114.051686,33.567555],
		 "郾城区": [114.016813,33.588897],
		 "襄城县": [113.493166,33.855943],
		 "建安区": [113.842898,34.005018],
		 "魏都区": [113.828307,34.02711],
		 "长葛市": [113.768912,34.219257],
		 "禹州市": [113.471316,34.154403],
		 "鄢陵县": [114.188507,34.100502],
		 "西峡县": [111.485772,33.302981],
		 "卧龙区": [112.528789,32.989877],
		 "南召县": [112.435583,33.488617],
		 "淅川县": [111.489026,33.136106],
		 "社旗县": [112.938279,33.056126],
		 "桐柏县": [113.406059,32.367153],
		 "唐河县": [112.838492,32.687892],
		 "内乡县": [111.843801,33.046358],
		 "宛城区": [112.544591,32.994857],
		 "镇平县": [112.232722,33.036651],
		 "新野县": [112.365624,32.524006],
		 "方城县": [113.010933,33.255138],
		 "邓州市": [112.092716,32.681642],
		 "淮滨县": [115.415451,32.452639],
		 "潢川县": [115.050123,32.134024],
		 "光山县": [114.903577,32.010398],
		 "罗山县": [114.533414,32.203206],
		 "商城县": [115.406297,31.799982],
		 "固始县": [115.667328,32.183074],
		 "浉河区": [114.075031,32.123274],
		 "新县": [114.87705,31.63515],
		 "息县": [114.740713,32.344744],
		 "平桥区": [114.126027,32.098395],
		 "济源市坡头镇": [112.538,34.9308],
		 "济源市梨林镇": [112.753,35.0882],
		 "济源市思礼镇": [112.388,35.2103],
		 "济源市大峪镇": [112.338,34.9398],
		 "济源市五龙口镇": [112.761,35.1956],
		 "济源市王屋镇": [112.169,35.1244],
		 "济源市玉泉街道": [112.624,35.1189],
		 "济源市轵城镇": [112.622,35.0625],
		 "济源市济水街道": [112.595,35.0944],
		 "济源市沁园街道": [112.593,35.0822],
		 "济源市下冶镇": [112.212,35.1131],
		 "济源市克井镇": [112.635,35.255],
		 "济源市天坛街道": [112.567,35.1229],
		 "济源市邵原镇": [112.113,35.2704],
		 "济源市北海街道": [112.569,35.1116],
		 "济源市承留镇": [112.509,35.0243],
		 "上蔡县": [114.266892,33.264719],
		 "西平县": [114.026864,33.382315],
		 "新蔡县": [114.975246,32.749948],
		 "平舆县": [114.637105,32.955626],
		 "汝南县": [114.359495,33.004535],
		 "泌阳县": [113.32605,32.725129],
		 "遂平县": [114.00371,33.14698],
		 "驿城区": [114.029149,32.977559],
		 "确山县": [114.026679,32.801538],
		 "正阳县": [114.38948,32.601826],
		 "马村区": [113.321703,35.265453],
		 "解放区": [113.226126,35.241353],
		 "沁阳市": [112.934538,35.08901],
		 "温县": [113.079118,34.941233],
		 "孟州市": [112.78708,34.90963],
		 "博爱县": [113.069313,35.170351],
		 "山阳区": [113.26766,35.21476],
		 "武陟县": [113.408334,35.09885],
		 "中站区": [113.175485,35.236145],
		 "修武县": [113.447465,35.229923],
		 "殷都区": [114.300098,36.108974],
		 "内黄县": [114.904582,35.953702],
		 "汤阴县": [114.362357,35.922349],
		 "滑县": [114.524,35.574628],
		 "文峰区": [114.352562,36.098101],
		 "北关区": [114.352646,36.10978],
		 "林州市": [113.823767,36.063403],
		 "安阳县": [114.130207,36.130585],
		 "龙安区": [114.323522,36.095568],
		 "鹤山区": [114.166551,35.936128],
		 "淇县": [114.200379,35.609478],
		 "山城区": [114.184202,35.896058],
		 "浚县": [114.550162,35.671282],
		 "淇滨区": [114.293917,35.748382],
		 "辉县市": [113.802518,35.461318],
		 "凤泉区": [113.906712,35.379855],
		 "获嘉县": [113.657249,35.261685],
		 "延津县": [114.200982,35.149515],
		 "封丘县": [114.423405,35.04057],
		 "长垣县": [114.673807,35.19615],
		 "原阳县": [113.965966,35.054001],
		 "卫辉市": [114.065855,35.404295],
		 "牧野区": [113.89716,35.312974],
		 "新乡县": [113.806186,35.190021],
		 "卫滨区": [113.866065,35.304905],
		 "红旗区": [113.878158,35.302684],
		 "兰考县": [114.820572,34.829899],
		 "祥符区": [114.437622,34.756476],
		 "顺河回族区": [114.364875,34.800459],
		 "禹王台区": [114.350246,34.779727],
		 "尉氏县": [114.193927,34.412256],
		 "通许县": [114.467734,34.477302],
		 "鼓楼区": [114.3485,34.792383],
		 "龙亭区": [114.353348,34.799833],
		 "杞县": [114.770472,34.554585],
		 "郏县": [113.220451,33.971993],
		 "石龙区": [112.889885,33.901538],
		 "湛河区": [113.320873,33.725681],
		 "卫东区": [113.310327,33.739285],
		 "鲁山县": [112.906703,33.740325],
		 "叶县": [113.358298,33.621252],
		 "舞钢市": [113.52625,33.302082],
		 "新华区": [113.299061,33.737579],
		 "宝丰县": [113.066812,33.866359],
		 "汝州市": [112.845336,34.167408],
		 "巩义市": [112.98283,34.75218],
		 "登封市": [113.037768,34.459939],
		 "金水区": [113.686037,34.775838],
		 "上街区": [113.298282,34.808689],
		 "惠济区": [113.61836,34.828591],
		 "荥阳市": [113.391523,34.789077],
		 "中原区": [113.611576,34.748286],
		 "二七区": [113.645422,34.730936],
		 "新密市": [113.380616,34.537846],
		 "中牟县": [114.022521,34.721976],
		 "管城回族区": [113.685313,34.746453],
		 "新郑市": [113.73967,34.394219],
		 "鹿邑县": [115.486386,33.861067],
		 "沈丘县": [115.078375,33.395514],
		 "郸城县": [115.189,33.643852],
		 "扶沟县": [114.392008,34.054061],
		 "淮阳县": [114.870166,33.732547],
		 "项城市": [114.899521,33.443085],
		 "太康县": [114.853834,34.065312],
		 "商水县": [114.60927,33.543845],
		 "川汇区": [114.652136,33.614836],
		 "西华县": [114.530067,33.784378],
		 "宁陵县": [115.320055,34.449299],
		 "柘城县": [115.307433,34.075277],
		 "梁园区": [115.65459,34.436553],
		 "睢阳区": [115.653813,34.390536],
		 "夏邑县": [116.13989,34.240894],
		 "永城市": [116.449672,33.931318],
		 "民权县": [115.148146,34.648455],
		 "睢县": [115.070109,34.428433],
		 "虞城县": [115.863811,34.399634],
       };
       $.get("assets/platform/data/henan.json", function(data){
          console.log(data);
          echarts.registerMap('henan', data);
          /*
			 * var geoCoordMap = [],features = data["features"]; for (var i = 0;
			 * i < features.length; i++) { var name =
			 * features[i]["properties"]["name"]; var value =
			 * features[i]["properties"]["cp"]; geoCoordMap[name] = value; /* if
			 * (name) { res.push({ name: name, value: geoCoord.concat(value) }); }
			 */
         
          /* console.log(geoCoordMap); */
          var data1 = [];
          
          for(var key in  geoCoordMap){
        	  var geoCoord = geoCoordMap[key];
        	  data1.push({
        		  name: key,
        		  value: geoCoord.concat(((Math.random() * 1000) + 50).toFixed(2))
        	  });
          }
        
       var convertData = function(data) {
           var res = [];
           for (var i = 0; i < data.length; i++) {
               var geoCoord = geoCoordMap[data[i].name];
               if (geoCoord) {
                   res.push({
                       name: data[i].name,
                       value: geoCoord.concat(data[i].value)
                   });
               }
           }
           console.log(res)
           return res;
       };

       option = {
           title: {
               text: '',
               x: 'left',
               top: "10",
               textStyle: {
                   color: '#000',
                   fontSize: 14
               }
           },
           tooltip: {
               show: true,
         // formatter:(params)=>{
         // let data = "测试1:"+params.name + "<br/>"+"值:"+
			// params.value[2]+"<br/>"+"地理坐标:[" +
			// params.value[0]+","+params.value[1] +"]";
         // return data;
         // },
     },
     visualMap: [{
       type: 'continuous',
       seriesIndex: 0,
       text: ['bar3D'],
       calculable: true,
       max: 80,
       inRange: {
           color: ['#87aa66', '#eba438', '#d94d4c']
       }
   }],
   geo3D: {
       map: 'henan',
       boxHeight:30,
       roam: true,
       //environment:'#2185FF',
       itemStyle: {
           color: '#243E93',
           opacity: 1,
           borderWidth: 0.4,
           borderColor: '#000',
        
       },
       label: {
           show: true,
           textStyle: {
                 color: '#fff', //地图初始化区域字体颜色
                 fontSize: 8,
                 opacity: 1,
                 backgroundColor: 'rgba(0,23,11,0)'
             },
         },
         emphasis: { //当鼠标放上去  地区区域是否显示名称
           label: {
               show: true,
               textStyle: {
                   color: '#fff',
                   fontSize: 3,
                   backgroundColor: 'rgba(0,23,11,0)'
               },
               shadowBlur: 10,
               shadowColor: 'rgba(0, 0, 0, 0.5)',
               shadowOffsetX: 0,
               shadowOffsetY: 0
           }
       },
         //shading: 'lambert',
         light: { //光照阴影
           main: {
                 color: '#fff', //光照颜色
                 intensity: 1.2, //光照强度
                 //shadowQuality: 'high', //阴影亮度
                 shadow: false, //是否显示阴影
                 alpha:55,
                 beta:10

             },
             ambient: {
               intensity: 0.3
           }
       }
   },
	   series: [{
	       name: 'bar3D',
	       type: "bar3D",
	       coordinateSystem: 'geo3D',
	         barSize: 2, //柱子粗细
	         shading: 'lambert',
	         opacity: 1,
	         bevelSize:0.3,
	         label: {
	           show: false,
	           formatter: '{b}'
	       },
	data:  convertData([{"name":"中牟县","value":"7.23"},
			{"name":"巩义市","value":"8.63"},
			{"name":"荥阳市","value":"17.5"},
			{"name":"新密市","value":"11.5"},
			{"name":"新郑市","value":"13.4"},
			{"name":"登封市","value":"9.54"},
			{"name":"杞县  ","value":"41.5"},
			{"name":"通许县","value":"25.6"},
			{"name":"尉氏县","value":"40.4"},
			{"name":"兰考县","value":"35.2"},
			{"name":"孟津县","value":"13.9"},
			{"name":"新安县","value":"10.7"},
			{"name":"栾川县","value":"1.53"},
			{"name":"嵩县  ","value":"9.82"},
			{"name":"汝阳县","value":"8.37"},
			{"name":"宜阳县","value":"19.5"},
			{"name":"洛宁县","value":"14.2"},
			{"name":"伊川县","value":"19.5"},
			{"name":"偃师市","value":"12.9"},
			{"name":"宝丰县","value":"14.6"},
			{"name":"叶县  ","value":"33.9"},
			{"name":"鲁山县","value":"11.4"},
			{"name":"郏县  ","value":"17.5"},
			{"name":"舞钢市","value":"8.96"},
			{"name":"汝州市","value":"25.3"},
			{"name":"安阳县","value":"17.3"},
			{"name":"汤阴县","value":"24.4"},
			{"name":"滑县  ","value":"90.7"},
			{"name":"内黄县","value":"37.4"},
			{"name":"林州市","value":"13.7"},
			{"name":"浚县  ","value":"41.8"},
			{"name":"淇县  ","value":"15.1"},
			{"name":"新乡县","value":"14.6"},
			{"name":"获嘉县","value":"18.4"},
			{"name":"原阳县","value":"45.4"},
			{"name":"延津县","value":"38.0"},
			{"name":"封丘县","value":"48.7"},
			{"name":"长垣县","value":"41.3"},
			{"name":"卫辉市","value":"22.2"},
			{"name":"辉县市","value":"31.7"},
			{"name":"修武县","value":"10.8"},
			{"name":"博爱县","value":"10.7"},
			{"name":"武陟县","value":"29.4"},
			{"name":"温县  ","value":"17.9"},
			{"name":"沁阳市","value":"18.0"},
			{"name":"孟州市","value":"17.0"},
			{"name":"清丰县","value":"36.7"},
			{"name":"南乐县","value":"30.3"},
			{"name":"范县  ","value":"18.6"},
			{"name":"台前县","value":"14.0"},
			{"name":"濮阳县","value":"55.6"},
			{"name":"鄢陵县","value":"33.1"},
			{"name":"襄城县","value":"33.1"},
			{"name":"禹州市","value":"29.1"},
			{"name":"长葛市","value":"30.1"},
			{"name":"舞阳县","value":"29.8"},
			{"name":"临颍县","value":"31.5"},
			{"name":"渑池县","value":"9.79"},
			{"name":"卢氏县","value":"5.96"},
			{"name":"义马市","value":"0.43"},
			{"name":"灵宝市","value":"11.5"},
			{"name":"南召县","value":"3.75"},
			{"name":"方城县","value":"39.0"},
			{"name":"西峡县","value":"3.77"},
			{"name":"镇平县","value":"28.9"},
			{"name":"内乡县","value":"19.2"},
			{"name":"淅川县","value":"15.8"},
			{"name":"社旗县","value":"30.4"},
			{"name":"唐河县","value":"91.3"},
			{"name":"新野县","value":"38.8"},
			{"name":"桐柏县","value":"7.24"},
			{"name":"邓州市","value":"81.7"},
			{"name":"民权县","value":"50.1"},
			{"name":"睢县  ","value":"42.9"},
			{"name":"宁陵县","value":"34.4"},
			{"name":"柘城县","value":"48.4"},
			{"name":"虞城县","value":"54.5"},
			{"name":"夏邑县","value":"60.2"},
			{"name":"永城市","value":"82.0"},
			{"name":"罗山县","value":"11.5"},
			{"name":"光山县","value":"6.98"},
			{"name":"新县  ","value":"0.39"},
			{"name":"商城县","value":"4.02"},
			{"name":"固始县","value":"17.0"},
			{"name":"潢川县","value":"15.7"},
			{"name":"淮滨县","value":"27.6"},
			{"name":"息县  ","value":"48.5"},	
			{"name":"扶沟县","value":"48.6"},
			{"name":"西华县","value":"54.9"},
			{"name":"商水县","value":"59.4"},
			{"name":"沈丘县","value":"54.5"},
			{"name":"郸城县","value":"64.9"},
			{"name":"淮阳县","value":"65.9"},
			{"name":"太康县","value":"81.1"},
			{"name":"鹿邑县","value":"53.8"},
			{"name":"项城市","value":"56.1"},	
			{"name":"西平县","value":"52.8"},
			{"name":"上蔡县","value":"70.1"},
			{"name":"平舆县","value":"57.0"},
			{"name":"正阳县","value":"71.9"},
			{"name":"确山县","value":"34.6"},
			{"name":"泌阳县","value":"42.6"},
			{"name":"汝南县","value":"57.9"},
			{"name":"遂平县","value":"36.4"},
			{"name":"新蔡县","value":"55.7"}]),
	}, {
	   name: 'scatter3D',
	   type: "scatter3D",
	   coordinateSystem: 'geo3D',
	   symbol: 'pin',
	   symbolSize: 30,
	   opacity: 1,
	   label: {
	       show: false,
	       formatter: '{b}'
	   },
	   itemStyle: {
	       borderWidth: 0.5,
	       borderColor: '#fff'
	   },
	   data: convertData([{
	       name: "南阳市",
	       value: ((Math.random() * 10000) + 50).toFixed(2)
	   }, {
	       name: "郑州市",
	       value: ((Math.random() * 10000) + 50).toFixed(2)
	   }, {
	       name: "信阳市",
	       value: ((Math.random() * 10000) + 50).toFixed(2)
	   }])
	}]
	}
       echarts.init(document.getElementById('container')).setOption(option);
       
       });