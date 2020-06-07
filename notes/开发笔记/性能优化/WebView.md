# 跨平台与Hybrid
- 跨平台 : 一种语言的代码可以在不同平台编译
- Hybrid : 一个应用包含多种语言
- H5相比于原生的缺点 : 性能相对差、相对不安全、不能跟随原生平台的风格、部分功能(硬件相关)必须通过原生实现

# WebView常用子类
- WebSetting : 使用配置
- WebViewClient : 处理通知、请求事件
- WebChromeClient ： 辅助WebView处理js对话框、网站图标、网站title、加载进度等

# 优化方向
- 预加载
- 单独进程
- 安全漏洞(更严格的域控制)
