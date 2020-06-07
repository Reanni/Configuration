# OkHttp简介 ppt3

# OkHttp的使用流程

# 分发器(Dispatcher)
内部维护队列与线程池，完成请求调配
- RealCall.execute()
同步请求将RealCall提交到Dispatcher中的runningSyncCalls队列 、 后直接主线程执行任务`getResponseWithInterceptorChain()` 、执行完成后调用`Dispatcher.finished(call:RealCall)`方法

- RealCall.enqueue()  子线程执行`getResponseWithInterceptorChain()`
异步请求将RealCall包装成AsyncCall提交到Dispatcher
Dispatcher中有两个队列 readyAsyncCalls(等待队列) 、runningAsyncCalls(执行中队列,任务同时会提交到ThreadPool)
- 队列分配
  - runningAsyncCalls的入队条件 : 执行中总任务数并发数小于`maxRequests`(默认64,可设置)、执行中同一host任务数并发数小于`maxRequestsPerHost`(默认5,可设置)
  - 满足runningAsyncCalls入队条件的加入runningAsyncCalls,不满足加入readyAsyncCalls
- 任务从readyAsyncCalls移到到runningAsyncCalls
  - 一个任务执行完成后(不管成功还是失败)会调用`Dispatcher.finished(call:AsyncCall)`方法
  - 方法中会将执行完成的任务从runningAsyncCalls中移除,并从readyAsyncCalls中移入满足runningAsyncCalls入队条件的任务并提交到ThreadPool

Dispatcher默认线程池 : 最大Int数的非核心线程、存活60秒

# 拦截器(Interceptors)
五大默认拦截器完成整个请求过程 责任链模式
- RetryAndFollowUpInterceptor(重试与重定向拦截器)
  - 可重试条件 :
    1. 路由或IO异常
    2. OkHttpClient配置允许重试
    3. 可重试的异常 :
       - 不是协议异常
       - 不是Socket超时异常
       - 不是SSL证书(格式)的问题
       - 不是SSL证书校验失败的问题
    4. 有更多的路线 : 一个域名可能会被解析成多个ip,一个ip连接不下可以尝试其他ip.还有代理ip)
- BridgeInterceptor(桥接拦截器)
- CacheInterceptor(缓存拦截器)
- ConnectInterceptor(连接拦截器)
- CallServerInterceptor(请求服务拦截器)
