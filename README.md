# tomcat
🌻实现一个类似Tomcat服务器

## In
学习了一下Http以及Tomcat相关的东西，想着自己能实现一个类似的东西

## note啊

### http请求头 
  * Host:本次请求的主机路径
  * User-Agent:服务器端对于本次请求的客户端浏览器相关信息
  * Accept:用于指定客户端接收的信息类型，image/gif表明客户端希望接受GIF格式源文件；text/html表明想接受html文本文件
  * Accept-Language: 告诉服务端，浏览器可以识别的语言种类
  * Accept-Encoding:告诉服务端，浏览器可以接受哪种类型的压缩格式数据gzip,defalte(无损的数据压缩算法)
  
### 状态码
* 1xx：指示信息--表示请求已经接收，继续处理
* 2xx：成功--成功接收、理解、接受
* 3xx：重定向--完成请求的进一步操作
* 4xx：客户端相关错误-请求有语法错误或者请求无法实现
* 5xx：服务端错误--服务器未能实现合法的请求
* 常见状态码：
  * 200 OK 客户端请求成功
  * 404 Not   Found // 请求资源不存在
  
  
### 响应头
* Date：响应时间
* content-Type：本次响应的内容
* content-Encoding：本次内容采用的压缩格式
* content-length：响应内容的长度
* testserver：响应的服务器类型
