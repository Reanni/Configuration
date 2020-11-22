# inflate()

- createViewFromTag() 创建xml的rootView
- createView() 反射调用View的两参构造函数

```
fun inflate(resource: Int, root: ViewGroup?, attachToRoot: Boolean): View? {
    ...
    val xmlRootView = createViewFromTag(root, name, context, attrs) //这里是解析生成xml的rootView的伪代码
    if (null != root) {
        val params = root.generateLayoutParams(attrs)
        if (attachToRoot) {
            root.addView(xmlRootView, params)
        } else {
            xmlRootView.layoutParams = params
        }
    }
   ...
}

```
- root不为null的时候,才会给xmlRootView设置父控件的params
- attachToRoot决定是否被父控件添加

- view的加载顺序, mFactory2.onCreateView()、mFactory.onCreateView()、onCreateView/createView
