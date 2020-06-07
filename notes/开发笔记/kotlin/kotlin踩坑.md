# 依赖及插件
- test及androidTest依赖只能当前Module可见，无法对所Module可见
- 合理使用implementation能减少build时间。
   * 被依赖Module中的依赖使用implementation修时，当被依赖Module中的依赖发生变动，只用重新build被依赖Module
   * 被依赖Module中的依赖使用api修饰时，当被依赖Module中的依赖发生变动，两个Module都需要build
# kotlin-android-extensions
- 不能使用子module里布局 (等找到原因)
- 支持Android 多渠道版本，假如在你的build.gradle中有一个名为free的版本(待验证)  
   ```
   android {
        productFlavors {
            free {
                versionName "1.0-free"
            }
        }
  }
   ```
   然后你就可以通过如下方式导入针对***free/res/layout/activity_free.xml***的所有生成的属性。
   `import kotlinx.android.synthetic.free.activity_free.* `