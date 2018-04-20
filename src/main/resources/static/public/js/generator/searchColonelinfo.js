$(function () {
    $("#jqGrid").jqGrid({
        url: '../colonelinfo/searchList',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden:true },
			{ label: '所属小组T1-TN', name: 'belongGroup', index: 'belong_group', width: 80 }, 			
			{ label: '开团名称', name: 'nickName', index: 'nick_name', width: 80 }, 			
			{ label: '冒险团名称', name: 'groupName', index: 'group_name', width: 80,formatter:function (value, options, row) {
				var groupNames= value.split("|");
				var html='';
				for (var i=0;i<groupNames.length;i++){
					html+='<li>'+groupNames[i]+'</li>';
				}
				return '<ol>'+html+"</ol>";
            } },
			{ label: '冒险团日期', name: 'groupDate', index: 'group_date', width: 80,formatter:function (value, options, row) {
                var groupDates= value.split("|");
                var html='';
                for (var i=0;i<groupDates.length;i++){
                    html+='<li>'+groupDates[i]+'</li>';
                }
                return '<ol>'+html+"</ol>";
            } },
			{ label: '是否删除 ', name: 'delflag', index: 'delflag', width: 80,formatter: function(value, options, row){
				if(value===0){
					return'<span class="label label-success">正常</span>';
				}else{
					return'<span class="label label-danger">开除</span>';
				}
			}}			
        ],
        sortable: false,
        viewrecords: true,
        height: 680,
        rowNum: 12,
        rowList : [12,20,30],
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
var i=1;
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			name: null,
			status:''
		},
		showList: true,
		title: null,
		colonelInfo: {},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				 postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});