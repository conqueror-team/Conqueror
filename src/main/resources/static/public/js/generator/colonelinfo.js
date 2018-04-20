$(function () {
    $("#jqGrid").jqGrid({
        url: '../colonelinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden:true },
			{ label: '所属小组T1-TN', name: 'belongGroup', index: 'belong_group', width: 80 }, 			
			{ label: '开团名称', name: 'nickName', index: 'nick_name', width: 80 }, 			
			{ label: '冒险团名称', name: 'groupName', index: 'group_name', width: 80 }, 			
			{ label: '冒险团日期', name: 'groupDate', index: 'group_date', width: 80 }, 			
			{ label: 'QQ号', name: 'contactsQq', index: 'contacts_qq', width: 80 }, 			
			{ label: '创建人', name: 'createUserId', index: 'create_user_id', width: 80 }, 			
			{ label: '创建时间', name: 'createDate', index: 'create_date', width: 80 }, 			
			{ label: '最后修改人', name: 'lastUpdateUserId', index: 'last_update_user_id', width: 80 }, 			
			{ label: '最后修改时间', name: 'lastUpdateDate', index: 'last_update_date', width: 80 }, 			
			{ label: '是否删除 ', name: 'delflag', index: 'delflag', width: 80,formatter: function(value, options, row){
				if(value===0){
					return'<span class="label label-success">正常</span>';
				}else{
					return'<span class="label label-danger">开除</span>';
				}
			}}			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
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
		groups:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.colonelInfo = {};
			vm.groups=[];
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.groups=[];
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.colonelInfo.id == null ? "../colonelinfo/save" : "../colonelinfo/update";
			var groupNames=$(".groupName");
            var groupDates=$(".groupDate");
            vm.colonelInfo.groupName="";
            vm.colonelInfo.groupDate="";
            for(var k=0;k<groupNames.length;k++){
            	if(groupNames[k].value!=""&&groupDates[k].value!=""){
                	vm.colonelInfo.groupName+=groupNames[k].value+"|";
                    vm.colonelInfo.groupDate+=groupDates[k].value+"|";
                }
			}
            if(vm.colonelInfo.groupDate.substr(vm.colonelInfo.groupDate.length-1, 1)=="|"){
                vm.colonelInfo.groupDate=vm.colonelInfo.groupDate.substr(0, vm.colonelInfo.groupDate.length-1);
                vm.colonelInfo.groupName=vm.colonelInfo.groupName.substr(0, vm.colonelInfo.groupName.length-1);
			}
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.colonelInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
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
				$.ajax({
					type: "POST",
				    url: "../colonelinfo/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../colonelinfo/info/"+id, function(r){
                vm.colonelInfo = r.colonelInfo;
                var groupName = r.colonelInfo.groupName.split("|");
				var groupDate = r.colonelInfo.groupDate.split("|");
				for(var j=0;j<groupName.length;j++){
					$("#indexGroup").hide();
                    vm.groups.push({
                        groupName:groupName[j],
                        groupDate:groupDate[j]
					});
				}
            });
		},
		reload: function (event) {
			vm.showList = true;
            i=1;
            $("#indexGroup").show();
            var groupNames=$(".groupName");
            for(var k1=1;k1<=groupNames.length;k1++){
                $("#addGroupDiv_"+k1).remove();
                $("#addGroupDiv_show"+k1).remove();
            }
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				 postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
        addGroup: function(){//新增
			$("#addGroupDiv").clone(true).attr('id','addGroupDiv_'+i++).appendTo("#addGroup").show();
		}
	}
});

 function delGroup(event){//删除
     event.parentElement.remove()
}