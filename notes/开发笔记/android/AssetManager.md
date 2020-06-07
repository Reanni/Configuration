# Resources 与 AssetManager 的关系
- Resources内部也是通过AssetManager来访问那些被编译过的应用程序资源文件的,不过在访问之前会先根据资源ID查找得到对应的资源文件名.
- AssetManager通过文件名,既可以访问那些被编译过的也可以访问没有被编译过的应用程序资源文件

# 文件夹`raw`和`assets`的区别
- raw : Android会为这个目录中的所有文件生成一个ID.访问文件速度更快,还可以在xml中访问
- assets : 不会生成ID,只能通过AssetManager访问.访问速度相对慢,不能在xml中访问
