# 前置准备
- 配置Android环境变量 : %ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
- Systrace 是用python2.7制作的,需要安装python2.7环境(Ps：只支持python2.7)
    - 安装python2.7
    - 配置python环境变量 : %PYTHON_HOME%;%PYTHON_HOME%\Scripts
    - 在%ANDROID_HOME%\platform-tools\systrace 目录打开cmd 运行命令
        ```
            systrace.py -l         
        ```
      如果显示操作符说明表示能正常使用了,如果报错通过如下方式解决
        - ImportError: No module named win32con 表示缺少win32con模块,通过下面命令进行安装
            ```
            pip install pypiwin32      
            ```
        - 如果提示pip版本过低则需要通过如下命令升级pip
            ```
            python -m pip install --upgrade pip      
            ```
        - ImportError: No module named six.moves 表示缺少six模块,通过下面命令进行卸载和安装
            ```
            pip uninstall six      
            pip install six      
            ```
- 运行%ANDROID_HOME%\tools\monitor.bat打开 Android Device Monitor