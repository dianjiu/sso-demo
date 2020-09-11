# sso-demo
本项目为学习单点登录系统的练习Demo

## 目录结构
### sso-client-one  单点测试服务1
### sso-client-two  单点测试服务2
### sso-server      单点登录服务端

## 涉及技术
1. cookie
2. redis
3. thymeleaf
4. springboot

## 实现目标
1. 任意测试服务登录,统一跳转到单点登录系统的登录页面
2. 任意测试服务登录，访问其他测试服务时，无需重复登录
3. 支持跨父域名的单点登录，
eg:a.com b.com sso.com

## 
1. 测试服务中访问任意前台系统(a.com/index.html)不拦截请求
2. 测试服务中访问任意后台请求(a.com/admin/delUser)拦截请求，从客户端取出cookie,
   客户端校验cookie是否存在，
   不存在重定向到sso.com/login页面
   存在则去sso.com/checkCookie校验cookie的合法性,合法则放行服务
3. 单点服务的checkCookie中获取测试服务传过来的cookieName和cookieValue，
   cookieName为userName,对cookieValue进行jwt的解密操作，防止被篡改，
   拿到解密后的redisKey，通过Redisutils取出缓存中的用户信息
   如果不存在用户信息，则校验失败，返回false，测试服务重定向到sso.com/login
   如果存在，则校验cookieName和redis中的用户信息是否同，不同返回fasle,相同返回true
4. 单点服务的login中获取到用户输入的用户名和密码，去查询数据库中是否存在，
   如果不存在则则提示用户名或密码错误，
   如果存在则通过UUID生成Redis的key，value为查到的用户信息，存入到redis，当天有效
   并且通过JWTUtil对redisKey进行加密得到cookie的value，用户名为cookie的name，
   重定向到goto路径
5. 单点服务的logout







