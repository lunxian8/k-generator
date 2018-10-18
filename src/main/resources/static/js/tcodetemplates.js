$(function () {
    $.ajax({
        type: "post",
        url: "TCodeTemplateGroup/tcodetemplategroup/getDbInfo",
        async : false,
        success: function (data) {
            var list=data.list;
            if (list!=null || list!=undefined ||list.length!=0){
                vm.options=list;
            }
        },
        error: function (data) {
            console.log("数据操作失败");
            alert("请重新尝试")
        }
    });
    $("#jqGrid").jqGrid({
        url: 'TCodeTemplates/tcodetemplates/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true,hidden:true},
			{ label: '模板名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '模板内容', name: 'content', index: 'content', width: 80,hidden:true},
			{ label: 'File Name', name: 'fileName', index: 'file_name', width: 80 },
            { label: '保存包名', name: 'savaPackage', index: 'savaPackage', width: 80 },
			{ label: '所属模板组', name: 'groupId', index: 'group_id', width: 80,hidden:true },
            { label: '所属模板组', name: 'tempName', index: 'tempName', width: 80},
			{ label: '模板类型', name: 'fileType', index: 'file_type', width: 80 },
            { label: '描述', name: 'remarks', index: 'remarks', width: 80 },
            { label: '创建时间', name: 'crtTime', index: 'crt_time', width: 80,sortable:false},
			{ label: '修改时间', name: 'mdfTime', index: 'mdf_time', width: 80,sortable:false},
			{ label: '排序', name: 'sort', index: 'sort', width: 80,hidden:true },
			{ label: '状态', name: 'delFlag', index: 'del_flag', width: 80,hidden:true }
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tCodeTemplates: {},
        options: [
        ]
	},

	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tCodeTemplates = {};
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
			var url = vm.tCodeTemplates.id == null ? "TCodeTemplates/tcodetemplates/save" : "TCodeTemplates/tcodetemplates/update";
			$.ajax({
				type: "POST",
			    url:  url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tCodeTemplates),
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
				    url:  "TCodeTemplates/tcodetemplates/delete",
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
			$.get("TCodeTemplates/tcodetemplates/info/"+id, function(r){
                vm.tCodeTemplates = r.tCodeTemplatesEntity;
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


/**
 * highlightJs 代码高亮
 */
// Vue.directive('highlight', function (el) {
//     let blocks = el.querySelectorAll('textarea');
//     blocks.forEach((block) => {
//         hljs.highlightBlock(block);
//     })
// });