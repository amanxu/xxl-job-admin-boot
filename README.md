1.本项目基于大神雪里的xxl-job开源项目1.9.1版本改造，其中xxl-job-core包直接引用maven仓库依赖，项目中删除原有的core包

  开源项目原github地址：https://github.com/xuxueli/xxl-job/
  
  改造后项目框架：springboot + jwt + vue 
  
2.本项目对原有项目的改动之处：

  2.1 将原有的springMVC工程迁移到springboot项目中，权限验证由原来的cookie改为springboot+jwt
  
  2.2 重构原有项目的前端页面，将原有的freemark前端框架修改为thymeleaf框架
  
  2.3 将原来的前端页面使用vue做了重构，改为前后端分离开发
  
  vue前端地址：https://github.com/amanxu/xxl-job-admin-vue-h5
  
  2.4 新增了全用户权限分配和用户管理模块儿，权限按照执行器为粒度进行隔离，可以创建拥有不同执行器权限的用户使用
    户只能查看被分配权限的执行器、作业列表、日志列表和报表
    
  2.5 本项目只对调度中心的管理端做了页面和框架的优化，并没有对开源项目的核心代码做任何修改，请放心使用
  
  项目线上体验地址：http://47.94.85.76:8080

   
