$(function () {
    $("#jqGrid").jqGrid({
        url: 'TCodeDbinfo/tcodedbinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'ID', width: 50, key: true,hidden:true},
			{ label: '别名', name: 'ALIAS',  width: 80 },
			// { label: '数据库驱动', name: 'DB_DRIVER',  width: 80 },
			{ label: '数据库地址', name: 'DB_URL', width: 80 },
			// { label: '数据库账户', name: 'DB_USER_NAME',  width: 80 },
			// { label: '连接密码', name: 'DB_PASSWORD', width: 80 },
			// { label: '', name: 'USER_ID',  width: 80 },
			// { label: '数据库类型', name: 'DB_TYPE', width: 80 },
			// { label: '创建人', name: 'CRT_USER_ID',  width: 80 },
			{ label: '创建时间', name: 'CRT_TIME',  width: 80 },
			// { label: '修改人', name: 'MDF_USER_ID', width: 80 },
			{ label: '修改时间', name: 'MDF_TIME', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50, 100, 200],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tCodeDbinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tCodeDbinfo = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			console.log(vm.tCodeDbinfo)
			var url = vm.tCodeDbinfo.id == null ? "TCodeDbinfo/tcodedbinfo/save" : "TCodeDbinfo/tcodedbinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tCodeDbinfo),
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
			console.log(ids);
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "TCodeDbinfo/tcodedbinfo/delete",
                    contentType: "application/json",
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
			$.get("TCodeDbinfo/tcodedbinfo/info/"+id, function(r){
				console.log(r.tCodeDbinfoEntity)
                vm.tCodeDbinfo = r.tCodeDbinfoEntity;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});