$(function () {
    $("#jqGrid").jqGrid({
        url: '../blacklist/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden:true },
			{ label: '角色名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '冒险团名称', name: 'groupName', index: 'group_name', width: 80 }, 			
			{ label: '证据图片', name: 'imgUrl', width: 150, formatter: function(value, options, row){
				if(value!=null&&value!=''){
					var imgs=value.split(',');
					var imgHtml='<ul class="nav navbar-nav">';
					for(var i=0;i<imgs.length;i++){
						imgHtml+='<li class="" style="margin: 5px 5px;">';
						imgHtml+='<img style="width: 40px;height:40px;" src="http://conqueror-1251711161.cos.ap-chengdu.myqcloud.com/'+imgs[i]+'"/></li>';
					}
					return 	imgHtml+"</ul>";
				}
				return '<span class="label label-danger">暂无图片</span>';
			}},
			{ label: '严重程度', name: 'severity', width: 80, formatter: function(value, options, row){
				if(value===1){
					return'<span class="label label-default" style="background-color:#f0ae2e">一般</span>';
				}else if(value===2){
					return'<span class="label label-default" style="background-color:#e57c28">较重</span>';
				}else if(value===3){
					return'<span class="label label-default" style="background-color:#e55928">严重</span>';
				}else if(value===4){
					return'<span class="label label-default" style="background-color:#ee0e0e">特别严重</span>';
				}else{
					return'<span class="label label-default">未知</span>';
				}
			}},
			{ label: '大区名称', name: 'regionName', index: 'region_name', width: 80 }, 			
			{ label: '备注', name: 'remarks', index: 'remarks', width: 180,sortable: false,formatter:function(value, options, row){
				if(value){
					return '<div style="max-height:80px;overflow-y: scroll;width:100%;word-break: break-all;white-space: normal;">'+value+'</div>';
				}else{
					return '';
				}
			} },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">删除</span>' :
					'<span class="label label-success">正常</span>';
			}},
			
			{ label: '创建人', name: 'createUserName', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }			
        ],
		viewrecords: true,
        height: 700,
        rowNum: 8,
		rowList : [8,16,24],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
    
});



var Bucket = 'conqueror-1251711161';
var Region = 'ap-chengdu';
// 初始化实例
var cos = new COS({
    getAuthorization: function (options, callback) {
        // 异步获取签名
        $.get('http://api.dnfzhengfuzhe.com/app/auth/get', {
            method: (options.Method || 'get').toLowerCase(),
            pathname: '/' + (options.Key || '')
        }, function (authorization) {
            callback(authorization);
        }, 'text');
    }
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			status:'1'
		},
		showList: true,
		title: null,
		blackList: {},
		regionNames:[
			 { text: '重庆1区', value: '重庆1区' },
			 { text: '重庆2区', value: '重庆2区' },
			 { text: '西南1区', value: '西南1区' },
			 { text: '西南2区', value: '西南2区' },
			 { text: '西南3区', value: '西南3区' },
			 { text: '陕西1区', value: '陕西1区' },
			 { text: '陕西2/3区', value: '陕西2/3区' },
			 { text: '云南1区', value: '云南1区' },
			 { text: '贵州1区', value: '贵州1区' },
			 { text: '云贵1区', value: '云贵1区' },
			 { text: '新疆1区', value: '新疆1区' }
			 
		]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.blackList = {severity:1,regionName:'重庆1区',};
			$("#imgUrl").val("");
			$("#addImgId").show();
			$("#updateImgId").hide();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
            $("#addImgId").hide();
			$("#updateImgId").show();
		},
		saveOrUpdate: function (event) {
			var url = vm.blackList.id == null ? "../blacklist/save" : "../blacklist/update";
			vm.blackList.imgUrl=$("#imgUrl").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.blackList),
			    success: function(r){
			    	if(r.code === 0){
			    		$("#ctlBtn_x").show();
			    		$(".diyUploadHover").remove();
			    		$tgaUpload = $('#goodsUpload').diyUpload({
			    			buttonText : '',
			    			accept: {
			    				title: "Images",
			    				extensions: 'gif,jpg,jpeg,bmp,png'
			    			},
			    			thumb:{
			    				width:120,
			    				height:90,
			    				// 图片质量，只有type为`image/jpeg`的时候才有效。
			    				quality:100,
			    				// 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
			    				allowMagnify:false,
			    				// 是否允许裁剪。
			    				crop:true,
			    				// 如果发现压缩后文件大小比原来还大，则使用原来图片
			    			    // 此属性可能会影响图片自动纠正功能
			    				noCompressIfLarger: false,
			    				compress:false,
			    				type:"image/jpeg"
			    			},
			    			fileNumLimit:10
			    		}); 
						alert('操作成功', function(index){
							// vm.reload();
                            window.location.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				var k=0;
				for(var j=0;j<ids.length;j++){
					$.get("../blacklist/info/"+ids[j], function(r){
						if(r.blackList&&r.blackList.imgUrl){
							 var imgUrls=r.blackList.imgUrl.split(',');
							 for(var i=0;i<imgUrls.length;i++){
								 var params = {
										  Bucket : Bucket,                        /* 必须 */
										  Region : Region,                        /* 必须 */
										  Key : imgUrls[i]                            /* 必须 */
										};
								 cos.deleteObject(params, function(err, data) {
									  if(err) {
									    console.log(err);
									  } else {
									    console.log(data);
									  }
									  
									});
							 }
							}
						if(++k==ids.length){
							  $.ajax({
									type: "POST",
								    url: "../blacklist/delete",
								    data: JSON.stringify(ids),
								    success: function(r){
										if(r.code == 0){
											alert('操作成功', function(index){
												// $("#jqGrid").trigger("reloadGrid");
                                                window.location.reload();
											});
										}else{
											alert(r.msg);
										}
									}
								});
						  }
		            });
				}
				
			});
		},
		getInfo: function(id){
			$.get("../blacklist/info/"+id, function(r){
                vm.blackList = r.blackList;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				   postData:{'name': vm.q.name,'status':vm.q.status},
                page:page
            }).trigger("reloadGrid");
		}
	}
});

$("#jqGrid").on('click','img',function(){
	$('.album').fadeIn(300);
	$("#showImg").attr("src",$(this).attr("src"));
//	var imgs=$(this).parent().parent().find('img');
});
$('.album').on('click', function (){
	$('.album').fadeOut(300);
});


