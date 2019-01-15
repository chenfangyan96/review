#javaweb
review  javase   javaee
JSP  动态网页
CS架构：客户端比较美观  速度比较快
不足： 需要安装客户端   需要更新的话  全部更新
      如果软件升级    客户端都需要升级  升级麻烦
      维护麻烦  需要维护每一台
      
BS   
      只需要通过浏览器直接访问服务端  不要客户端   
tomcat
bin  可执行文件
conf  配置文件  server.xml
lib：  依赖jar包-
log   日志  记录出错
temp临时文件
webapps  可执行的项目
work 存放有jsp翻译成的java 以及编辑成的class文件  jsp   java  class

修改tomcat端口号   conf  server.xml    70行左右    Connector   port="8080"
配置项目在tomcat的路径   
1》在server。xml中HOST中配置  添加 <Context  (实际路径)doBase="D:\study\javaProhect" path="/javaProject" >//这个是相对路径  相对于weapps的路径  其实就是D:\study\apachr\webapps\javaProject"
2》在taomcat下conf   Catalina   localhost下新建  项目名.xml  中新增一行  <Context   dobase="诗经路径"    path=“/javaProject”>
