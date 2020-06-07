# 启动流程
- 从`Context.startActivity()`开始,他的实现是`ContextImpl.startActivity()`
- 内部调用`Instrumentation.execStartActivity()`来尝试启动Activity
- 内部会调用AMS的startActivity方法,这里是一个跨进程的过程
- AMS校验完Activity的合法性后,会通过ApplicationThread回调到我们的回程,这里又一次跨进程
- ApplicationThread是一个binder,回调逻辑是在binder线程池中完成的,通过`ActivityThread.mH:Handler`发送消息
- api 28前发送第一个消息是`LAUNCH_ACTIVITY`、api 28开始换成`EXECUTE_TRANSACTION`,消息对应`handleLaunchActivity()`,在这个方法里会完成Activity的创建和启动
