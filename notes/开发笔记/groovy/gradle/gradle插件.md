# 3种方式编写gradle插件
1. 使用特殊目录`buildSrc`
   1. 新建`buildSrc`目录(目录非Module)
   2. 执行 Make Project 操作
   3. buildSrc目录下创建build.gradle文件添加依赖
      ```
      implementation gradleApi()  //使用gradle的api
      ```
   4. buildSrc目录下创建`src\main\java\pluginPackageName`目录编写插件代码
      ```
      import org.gradle.api.Plugin
      import org.gradle.api.Project
      
      class CustomPlugin : Plugin<Project>{
        
          override fun apply(project:Project){} 
            
      } 
      ```
   5. 通过添加`apply plugin: pluginPackageName`引用插件
