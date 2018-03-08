$(function () {
	$("#jqGrid").jqGrid({
        url: '../blacklist/list',
        datatype: "json",
        colModel: [			
			
			{ label: '角色名称', name: 'name', index: 'name', width: 80 ,sortable: false}, 			
			{ label: '冒险团名称', name: 'groupName', index: 'group_name', width: 80,sortable: false }, 			
			{ label: '图片', name: 'imgUrl', width: 150,sortable: false, formatter: function(value, options, row){
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
			{ label: '严重程度', name: 'severity', width: 80,sortable: false, formatter: function(value, options, row){
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
			{ label: '大区名称', name: 'regionName', index: 'region_name', width: 80,sortable: false }, 			
			{ label: '备注', name: 'remarks', index: 'remarks', width: 180,sortable: false,formatter:function(value, options, row){
				return '<textarea class="form-control" style="width: 100%;">'+value+'</textarea>';
			} },
			{ label: '状态', name: 'status', width: 80,sortable: false, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			
			{ label: '创建人', name: 'createUserName', width: 80,sortable: false }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80,sortable: false }			
        ],
        sortable: false,
		viewrecords: true,
        height: 680,
        rowNum: 9,
		rowList : [5,10,30],
        rownumbers: false, 
        rownumWidth: 25, 
        autowidth:true,
//        multiselect: true,
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			status:''
		},
		showList: true,
		title: null,
		blackList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				   postData:{'name': vm.q.name,'status':1},
                page:page
            }).trigger("reloadGrid");
		}
	}
});
$("#jqGrid").on('click','img',function(){
	$('.album').fadeIn(300);
	$("#showImg").attr("src",$(this).attr("src"));
});
$('.album').on('click', function (){
	$('.album').fadeOut(300);
});