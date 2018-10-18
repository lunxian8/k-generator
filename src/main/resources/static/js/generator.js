$(function () {
    /**
     * 数据库下拉框渲染
     */
    $.ajax({
        type: "post",
        url: "TCodeDbinfo/tcodedbinfo/getDbInfo",
        async : false,
        success: function (data) {
            var list=data.list;
            if (list!=null || list!=undefined ||list.length!=0){
                vm.selected=list[0].id;
                vm.options=list;
            }
        },
        error: function (data) {
            console.log("数据操作失败");
            alert("请重新尝试")
        }
    });
    /**
     * 模板组下拉框渲染
     */
    $.ajax({
        type: "post",
        url: "TCodeTemplateGroup/tcodetemplategroup/getDbInfo",
        async : false,
        success: function (data) {
            var list=data.list;
            if (list!=null || list!=undefined ||list.length!=0){
                vm.template=list[0].id;
                vm.templates=list;
            }
        },
        error: function (data) {
            console.log("数据操作失败");
            alert("请重新尝试")
        }
    });
    $("#jqGrid").jqGrid({
        postData: {"dbID":vm.selected},
        url: 'sys/generator/listJb',
        datatype: "json",
        colNames : [ '表名', 'Engine', '表备注', '创建时间'],
        colModel: [
            { name: 'tableName',index : 'tableName', width: 100, key: true,sortable:false},
            { name: 'engine',index : 'engine', width: 70,sortable:false},
            { name: 'tableComment',index : 'tableComment', width: 100,sortable:false},
            { name: 'createTime',index : 'createTime', width: 100,sortable:false}
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
        },
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            tableName: null,
            packageUrl: "com.lunxian.modules"
        },
        selected: null,
        options: [
        ],
        template: null,
        templates: [
        ]
    },
    methods: {
        query: function () {
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'tableName': vm.q.tableName,"dbID":vm.selected},
                page: 1
            }).trigger("reloadGrid");
            //vm.q.tableName=null
        },
        generator: function () {
            var tableNames = getSelectedRows()
            if (tableNames == null) {
                return;
            }
            /**
             * 检查包路径是否为空
             */
            if (vm.q.packageUrl == null || vm.q.packageUrl == undefined || vm.q.packageUrl == '') {
                alert("请配置包路径");
                return;
            }
            debugger;
            location.href = encodeURI("sys/generator/codeJb?tables=" + JSON.stringify(tableNames) + "&packageUrl=" + vm.q.packageUrl+"&dbID="+vm.selected+"&tempId="+vm.template);
        },
        generatorView: function () {
            var tableNames = getSelectedRow();
            if (tableNames == null) {
                return;
            }
            /**
             * 检查包路径是否为空
             */
            if (vm.q.packageUrl == null || vm.q.packageUrl == undefined || vm.q.packageUrl == '') {
                alert("请配置包路径");
                return;
            }
            $.ajax({
                type: "get",
                url: "sys/generator/codeViewJb?table=" + tableNames + "&packageUrl=" + vm.q.packageUrl+"&dbID="+vm.selected+"&tempId="+vm.template,
                success: function (data) {
                    if(data.code === 0){
                        var codeTmp = data.codeView;
                        console.log(codeTmp);
                        codeValue.entity = codeTmp.Entity,
                            codeValue.daoXml = codeTmp.DaoXml,
                            codeValue.dao= codeTmp.Dao,
                            codeValue.service = codeTmp.Service,
                            codeValue.serviceImpl = codeTmp.ServiceImpl,
                            codeValue.controller = codeTmp.Controller,
                            codeValue.html = codeTmp.html,
                            codeValue.js = codeTmp.js
                    }else{
                        alert(data.msg);
                    }
                },
                error: function (data) {
                    console.log("数据操作失败");
                    alert("预览失败，请重新尝试")
                }
            });
            // location.href = "sys/generator/codeView?table=" + tableNames + "&packageUrl=" + vm.q.packageUrl;
            Prism.highlightAll();
        },
    },

});

var codeValue = new Vue({
    el: '#codeView',
    data: {
        entity: '',
        dao:'',
        daoXml: '',
        service: '',
        serviceImpl: '',
        controller: '',
        html:'',
        js:''
    }
});

/**
 * highlightJs 代码高亮
 */
// Vue.directive('highlight', function (el) {
//     let blocks = el.querySelectorAll('pre code');
//     blocks.forEach((block) => {
//         hljs.highlightBlock(block);
//     })
// });
