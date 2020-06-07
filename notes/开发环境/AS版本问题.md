# 2.x 版本问题
1.	问题 : 报错 No toolchains found in the NDK toolchains folder for ABI with prefix: mips64el-linux-android  
	原因 : 高版本sdk的ndk路径'\ndk-bundle\toolchains'下缺少'mips64el-linux-android-4.9'文件夹  
	解决 : 在安卓开发者官网(https://developer.android.com/ndk/downloads/?hl=zh-cn)下载完整的ndk,将下载的ndk的'toolchains'目录中的'mips64el-linux-android-4.9'拷贝至本地sdk的'\ndk-bundle\toolchains'目录下。
	未验证 : 能下载版本与本地版本需要保持一致
	文件 : 已下载ndk版本为'r16b'的'mips64el-linux-android-4.9'文件,见文件mips64el-linux-android-4.9_r16b
	参考 : https://blog.csdn.net/qq_24118527/article/details/82867864

2.	问题 : 无法下载support包26以上的依赖  
	原因 : support本地仓库版本只更新到'26.0.0-alpha1'，后续版本需要从远程仓库下载。需要在gradle中配置远程仓库路径  
	解决 : gradle配置文件的'allprojects.repositories'标签下添加远程仓库路径'maven {url 'https://dl.google.com/dl/android/maven2/'}'.如下：
    ```
    allprojects {
        repositories {
            jcenter()
            maven { url 'https://dl.google.com/dl/android/maven2/' }//当依赖的v7包版本高于26（等于26不算）时，需要加入此行代码
        }
    }
    ```

3.	问题 : 当依赖的v7包版本高于26.0.0-alpha1时，XML无法预览  
	原因 : 26.0.0-alpha1之后主题的命名格式发生了改变，需要更新主题名，否则找不到该主题就无法预览  
	解决 : 如下:将"Theme.AppCompat.Light.DarkActionBar"改成"Base.Theme.AppCompat.Light.DarkActionBar"
    ```
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <!--<style name="AppTheme" parent="Base.Theme.AppCompat.Light.DarkActionBar">-->
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    ```
4.	问题 : 使用Lambda需要java编译版本为1.8,该版本ide配置java编译版本1.8需要配置jack支持,kotlin 1.1.1以上版本又与废弃掉的jack冲突  
	解决 : (使用1.7编译+Lambda插件)或者(使用不超过1.1.1的kotlin)最好(用最新的ide)