webpackJsonp([3],{"4y/F":function(e,t,n){(e.exports=n("FZ+f")(!0)).push([e.i,"\n.sucColor {\n  color: seagreen;\n}\n.failColor {\n  color: #f56c6c;\n}\n.top {\n  padding: 10px;\n}\n.el-form--inline .el-form-item {\n  margin-right: 2px;\n}\n.drop-down-view .el-dropdown {\n  vertical-align: top;\n}\n.drop-down-view .el-dropdown + .el-dropdown {\n  margin-left: 15px;\n}\n.drop-down-view .el-icon-arrow-down {\n  font-size: 12px;\n}\n.drop-down-view .el-button {\n  padding: 8px 7px;\n}\n.el-button--mini {\n  padding: 7px 10px;\n}\n.el-button + .el-button {\n  margin-left: 1px;\n}\n.dialog-detail-view .el-form-item {\n  margin-bottom: 0px;\n}\n.dialog-detail-view .el-form-item__content {\n  line-height: 30px;\n}\n.dialog-detail-view .el-form-item__label {\n  line-height: 30px;\n}\n.el-dialog__header {\n  padding-bottom: 0;\n}\n.el-dialog__body {\n  padding: 0;\n}\n.createPost-main-container {\n  padding: 20px 0 !important;\n}\n.fitersLine .el-form-item__content {\n  margin-left: 0 !important;\n}\n.fitersLine .el-form-item__label {\n  min-width: auto !important;\n}\n.fitersLine .el-select {\n  margin-top: 3px;\n}\n","",{version:3,sources:["D:/vuework/xxl-job-admin-vue-h5/src/views/logs/list.vue"],names:[],mappings:";AACA;EACE,gBAAgB;CACjB;AACD;EACE,eAAe;CAChB;AACD;EACE,cAAc;CACf;AACD;EACE,kBAAkB;CACnB;AACD;EACE,oBAAoB;CACrB;AACD;EACE,kBAAkB;CACnB;AACD;EACE,gBAAgB;CACjB;AACD;EACE,iBAAiB;CAClB;AACD;EACE,kBAAkB;CACnB;AACD;EACE,iBAAiB;CAClB;AACD;EACE,mBAAmB;CACpB;AACD;EACE,kBAAkB;CACnB;AACD;EACE,kBAAkB;CACnB;AACD;EACE,kBAAkB;CACnB;AACD;EACE,WAAW;CACZ;AACD;EACE,2BAA2B;CAC5B;AACD;EACE,0BAA0B;CAC3B;AACD;EACE,2BAA2B;CAC5B;AACD;EACE,gBAAgB;CACjB",file:"list.vue",sourcesContent:["\n.sucColor {\n  color: seagreen;\n}\n.failColor {\n  color: #f56c6c;\n}\n.top {\n  padding: 10px;\n}\n.el-form--inline .el-form-item {\n  margin-right: 2px;\n}\n.drop-down-view .el-dropdown {\n  vertical-align: top;\n}\n.drop-down-view .el-dropdown + .el-dropdown {\n  margin-left: 15px;\n}\n.drop-down-view .el-icon-arrow-down {\n  font-size: 12px;\n}\n.drop-down-view .el-button {\n  padding: 8px 7px;\n}\n.el-button--mini {\n  padding: 7px 10px;\n}\n.el-button + .el-button {\n  margin-left: 1px;\n}\n.dialog-detail-view .el-form-item {\n  margin-bottom: 0px;\n}\n.dialog-detail-view .el-form-item__content {\n  line-height: 30px;\n}\n.dialog-detail-view .el-form-item__label {\n  line-height: 30px;\n}\n.el-dialog__header {\n  padding-bottom: 0;\n}\n.el-dialog__body {\n  padding: 0;\n}\n.createPost-main-container {\n  padding: 20px 0 !important;\n}\n.fitersLine .el-form-item__content {\n  margin-left: 0 !important;\n}\n.fitersLine .el-form-item__label {\n  min-width: auto !important;\n}\n.fitersLine .el-select {\n  margin-top: 3px;\n}\n"],sourceRoot:""}])},HnGW:function(e,t,n){(e.exports=n("FZ+f")(!0)).push([e.i,"\n.app-container[data-v-1accf738] {\n  padding: 20px;\n}\n.pagination-container[data-v-1accf738] {\n  margin-top: 30px;\n}\n","",{version:3,sources:["D:/vuework/xxl-job-admin-vue-h5/src/views/logs/list.vue"],names:[],mappings:";AACA;EACE,cAAc;CACf;AACD;EACE,iBAAiB;CAClB",file:"list.vue",sourcesContent:["\n.app-container[data-v-1accf738] {\n  padding: 20px;\n}\n.pagination-container[data-v-1accf738] {\n  margin-top: 30px;\n}\n"],sourceRoot:""}])},TL9o:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=n("TIfe"),a=n("COTu"),r=n("vLgD"),l=n("0xDb");var i=n("kED/"),s={name:"list",props:{},components:{},data:function(){return{statusEnum:[{code:1,msg:"成功"},{code:2,msg:"失败"},{code:3,msg:"进行中"}],executors:[],jobs:[],dialogStatus:"create",dialogFormVisible:!1,headers:{},modifyData:"",list:null,total:null,listLoading:!0,queryParams:{jobGroup:null,jobId:null,start:1,length:10,logStatus:null,filterTime:[]},tableKey:0,jobLogDetail:{triggerCode:null,handleCode:null,executorAddress:"",executorParam:"",glueType:"",handleMsg:"",jobDesc:"",author:"",triggerMsg:""},logTimeArr:["一个月前","三个月前","六个月前","一年之前"],clearLogParams:{jobGroup:null,jobId:null,logStatus:null,type:null}}},created:function(){},mounted:function(){this.headers={accessToken:Object(o.a)()},this.getList(),this.getExecutors()},watch:{},methods:{getList:function(){var e,t=this;(e=this.queryParams,Object(r.a)({url:l.b+"/joblog/pageList",method:"post",params:e})).then(function(e){var n=e.code,o=e.content,a=o.data,r=o.recordsTotal;t.listLoading=!1,200===n?(t.list=a,t.total=r,0===t.total&&t.$message({message:"检索结果为空",type:"warning"})):t.$message(e.msg)}).catch(function(e){console.log(e)})},getExecutors:function(){var e=this;Object(a.c)().then(function(t){var n=t.code,o=t.content;200===n&&(e.executors=o)}).catch(function(e){console.log(e.message)})},groupChange:function(){var e=this;this.queryParams.jobId=null,null!==this.queryParams.jobGroup&&Object(i.c)(this.queryParams.jobGroup).then(function(t){t.code;var n=t.content;200===t.code&&(e.jobs=n)}).catch(function(e){console.log(e.message)})},stopJob:function(e){var t=this;(function(e){return Object(r.a)({url:l.b+"/joblog/logKill",method:"get",params:{id:e}})})(e).then(function(e){var n=e.code;e.msg;200===n?t.$message({message:"终止成功",type:"success"}):t.$message({message:"终止失败",type:"warning"})}).catch(function(e){console.log(e.message)})},jobLog:function(e){var t=this;(function(e){return Object(r.a)({url:l.b+"/joblog/logDetailPage",method:"get",params:{id:e}})})(e).then(function(e){t.jobLogDetail=e.content,200===e.code?t.dialogFormVisible=!0:t.$message({message:"查询失败",type:"warning"})}).catch(function(e){console.log(e.message)})},clearFilterParam:function(){this.queryParams.jobId=null,this.queryParams.jobGroup=null,this.queryParams.logStatus=null,this.queryParams.filterTime=[]},clearJobLogs:function(e){var t=this;this.$confirm("您确认清除"+this.logTimeArr[e-1]+"的日志？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(n){t.exeClearLogs(e)}).catch(function(e){return console.log(e)})},exeClearLogs:function(e){var t,n=this;this.clearLogParams.jobGroup=this.queryParams.jobGroup,this.clearLogParams.jobId=this.queryParams.jobId,this.clearLogParams.logStatus=this.queryParams.logStatus,this.clearLogParams.type=e,(t=this.clearLogParams,Object(r.a)({url:l.b+"/joblog/clearLog",method:"get",params:t})).then(function(e){200===e.code?n.$message({message:"清除成功",type:"success"}):n.$message({message:"清除失败",type:"warning"})}).catch(function(e){console.log(e.message())})},handleFilter:function(){this.queryParams.start=1,this.getList()},handleSizeChange:function(e){this.queryParams.length=e,this.getList()},handleCurrentChange:function(e){this.queryParams.start=e,this.getList()},handleRefresh:function(){this.list=null,this.total=null,this.queryParams.start=1,this.getList()}}},c={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"top"},[n("el-row",{staticClass:"fitersLine"},[n("el-col",{attrs:{span:24}},[n("el-form",{attrs:{inline:!0}},[n("el-select",{attrs:{size:"small",placeholder:"请选择执行器"},on:{change:e.groupChange},model:{value:e.queryParams.jobGroup,callback:function(t){e.$set(e.queryParams,"jobGroup",t)},expression:"queryParams.jobGroup"}},[n("el-option",{attrs:{value:"",label:"全部"}}),e._v(" "),e._l(e.executors,function(e){return n("el-option",{key:e.id,attrs:{label:e.title,value:e.id}})})],2),e._v(" "),n("el-select",{attrs:{size:"small",placeholder:"请选择作业"},model:{value:e.queryParams.jobId,callback:function(t){e.$set(e.queryParams,"jobId",t)},expression:"queryParams.jobId"}},[n("el-option",{attrs:{value:"",label:"全部"}}),e._v(" "),e._l(e.jobs,function(e){return n("el-option",{key:e.id,attrs:{label:e.jobDesc,value:e.id}})})],2),e._v(" "),n("el-select",{attrs:{size:"small",placeholder:"请选择日志状态"},model:{value:e.queryParams.logStatus,callback:function(t){e.$set(e.queryParams,"logStatus",t)},expression:"queryParams.logStatus"}},[n("el-option",{attrs:{value:"",label:"全部"}}),e._v(" "),e._l(e.statusEnum,function(e){return n("el-option",{key:e.code,attrs:{label:e.msg,value:e.code}})})],2),e._v(" "),n("el-form-item",[n("el-date-picker",{attrs:{size:"small",type:"datetimerange","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.queryParams.filterTime,callback:function(t){e.$set(e.queryParams,"filterTime",t)},expression:"queryParams.filterTime"}})],1),e._v(" "),n("el-form-item",[n("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.getList}},[e._v("查询")])],1),e._v(" "),n("el-form-item",[n("el-dropdown",{staticClass:"drop-down-view",attrs:{size:"small"},on:{command:e.clearJobLogs}},[n("el-button",{attrs:{type:"primary"}},[e._v("\n              清除日志"),n("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),e._v(" "),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[n("el-dropdown-item",{attrs:{command:"1"}},[e._v("一个月前")]),e._v(" "),n("el-dropdown-item",{attrs:{command:"2"}},[e._v("三个月前")]),e._v(" "),n("el-dropdown-item",{attrs:{command:"3"}},[e._v("六个月前")]),e._v(" "),n("el-dropdown-item",{attrs:{command:"4"}},[e._v("一年之前")])],1)],1)],1)],1)],1)],1),e._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],key:e.tableKey,ref:"list",staticStyle:{width:"100%"},attrs:{data:e.list,"element-loading-text":"数据加载中，请稍后",border:"",fit:"","highlight-current-row":"","select-on-indeterminate":""}},[n("el-table-column",{attrs:{align:"center",label:"任务ID","min-width":"60"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{staticClass:"link-type"},[e._v(e._s(t.row.jobId))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"任务名称","min-width":"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{staticClass:"link-type"},[e._v(e._s(t.row.jobName))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"调度时间","min-width":"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{staticClass:"link-type"},[e._v(e._s(e._f("dateformat")(t.row.triggerTime,"YYYY-MM-DD HH:mm:ss")))])]}}])}),e._v(" "),n("el-table-column",{attrs:{"min-width":"40",align:"center",label:"调度结果","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{class:{sucColor:200===t.row.triggerCode,failColor:500===t.row.triggerCode}},[e._v("\n          "+e._s(200===t.row.triggerCode?"成功":500===t.row.triggerCode?"失败":""))])]}}])}),e._v(" "),n("el-table-column",{attrs:{"min-width":"100",align:"center",label:"执行时间","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(e._f("dateformat")(null===t.row.handleTime?"":t.row.handleTime,"YYYY-MM-DD HH:mm:ss")))])]}}])}),e._v(" "),n("el-table-column",{attrs:{"min-width":"40",align:"center",label:"执行结果","show-overflow-tooltip":""},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",{class:{sucColor:200===t.row.handleCode,failColor:500===t.row.handleCode}},[e._v("\n          "+e._s(200===t.row.handleCode?"成功":500===t.row.handleCode?"失败":""))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"操作","min-width":"60"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(n){e.jobLog(t.row.id)}}},[e._v("日志详情")]),e._v(" "),n("el-button",{directives:[{name:"show",rawName:"v-show",value:200===t.row.triggerCode&&0===t.row.handleCode,expression:"scope.row.triggerCode===200 && scope.row.handleCode===0"}],attrs:{size:"mini",type:"danger"},on:{click:function(n){e.stopJob(t.row.id)}}},[e._v("终止\n        ")])]}}])})],1),e._v(" "),n("div",{directives:[{name:"show",rawName:"v-show",value:!e.listLoading,expression:"!listLoading"}],staticClass:"pagination-container"},[n("el-pagination",{attrs:{"current-page":e.queryParams.start,"page-sizes":[10,20,30,50],"page-size":e.queryParams.length,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"update:currentPage":function(t){e.$set(e.queryParams,"start",t)}}})],1),e._v(" "),n("el-dialog",{staticClass:"dialog-detail-view",attrs:{title:"调度详情",visible:e.dialogFormVisible,center:""},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[n("el-form",{attrs:{model:e.jobLogDetail}},[n("el-form-item",{attrs:{label:"触发结果：","label-width":"80px",prop:"realName"}},[n("span",[e._v(e._s(0===e.jobLogDetail.triggerCode?null:200===e.jobLogDetail.triggerCode?"成功":"失败"))])]),e._v(" "),n("el-form-item",{attrs:{label:"执行结果：","label-width":"80px",prop:"phone"}},[n("span",[e._v(e._s(0===e.jobLogDetail.handleCode?null:200===e.jobLogDetail.handleCode?"成功":"失败"))])]),e._v(" "),n("el-form-item",{attrs:{label:"执行器地址：","label-width":"80px",prop:"phone"}},[n("span",[e._v(e._s(e.jobLogDetail.executorAddress))])]),e._v(" "),n("el-form-item",{attrs:{label:"执行参数：","label-width":"80px",prop:"phone"}},[n("span",[e._v(e._s(e.jobLogDetail.executorParam))])]),e._v(" "),n("el-form-item",{attrs:{label:"调度类型：","label-width":"80px",prop:"phone"}},[n("span",[e._v(e._s(e.jobLogDetail.glueType))])]),e._v(" "),n("el-form-item",{attrs:{label:"作业描述：","label-width":"80px",prop:"phone"}},[n("span",[e._v(e._s(e.jobLogDetail.jobDesc))])]),e._v(" "),n("el-form-item",{attrs:{label:"执行日志：","label-width":"80px",prop:"phone"}},[n("span",[e._v(e._s(e.jobLogDetail.handleMsg))])]),e._v(" "),n("el-form-item",{attrs:{label:"调度信息：","label-width":"80px",prop:"userName"}},[n("span",{domProps:{innerHTML:e._s(e.jobLogDetail.triggerMsg)}})])],1),e._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("确定")])],1)],1)],1)},staticRenderFns:[]};var d=n("VU/8")(s,c,!1,function(e){n("iW3V"),n("ghxK")},"data-v-1accf738",null);t.default=d.exports},ghxK:function(e,t,n){var o=n("4y/F");"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);n("rjj0")("636fb5d6",o,!0)},iW3V:function(e,t,n){var o=n("HnGW");"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);n("rjj0")("69ea4db2",o,!0)},"kED/":function(e,t,n){"use strict";t.b=function(e){return Object(o.a)({url:a.b+"/jobinfo/pageList",method:"get",params:e})},t.c=function(e){return Object(o.a)({url:a.b+"/jobinfo/jobsByGroup",method:"get",params:{groupId:e}})},t.a=function(e){return Object(o.a)({url:a.b+"/jobinfo/add",method:"post",data:e})},t.g=function(e){return Object(o.a)({url:a.b+"/jobinfo/remove",method:"get",params:{id:e}})},t.h=function(e){return Object(o.a)({url:a.b+"/jobinfo/update",method:"post",data:e})},t.f=function(e){return Object(o.a)({url:a.b+"/jobinfo/trigger",method:"get",params:{id:e}})},t.e=function(e){return Object(o.a)({url:a.b+"/jobinfo/resume",method:"get",params:{id:e}})},t.d=function(e){return Object(o.a)({url:a.b+"/jobinfo/pause",method:"get",params:{id:e}})};var o=n("vLgD"),a=n("0xDb")}});
//# sourceMappingURL=3.7f5b2489e13cde88a875.js.map