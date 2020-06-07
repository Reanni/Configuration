# 四个场景角色

1. Lopper(安检机)

2. MessageQueue(安检机的传送带)

3. Message(传送带上物品)

4. Handler(放物品的人以及取物品的人)

- 一个队伍只有一台安检机  ——  一个线程有且只能有一个Looper
- 一台安检机只有一条传送带 ——  一个线程只有一个MessageQueue
- 队伍有了安检机并且打开了才能进行安检 —— 线程创建了Looper并且调用Looper.loop()才能处理消息
- 人通过意向的安检机检查物品 —— handler向指定线程的Looper里的MessageQueue发送消息
# 其他一些注意事项

- ThreadLocal:

  实现线程隔离，确保一个线程只能有一个Looper
  
- sendMessage、postMessage区别:

  都是调用MessageQueue.enqueueMessage() 将消息加入消息队列，但发送的消息不一样。sendMessage发送的Message会给object、what属性附值；postMessage发送的Message会给callback属性附值
  
  