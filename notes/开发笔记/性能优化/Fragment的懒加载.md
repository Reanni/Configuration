# Fragment
- 使用方式  
  1. 静态使用 : 作为一个布局标签使用,给"name"属性指定具体的Fragment类. 有以下特点:
     - 一旦添加,就无法在运行时动态删除
     - 加载的时机
     - 一定要有一个空参的构造函数 : fragment自动恢复时只会调用无参构造函数(会存在成员变量没有保存的问题)
  2. 动态使用 : 使用FragmentManager开启事物、添加事物、提交事物
- 系统bug : v4包小于24版本会有各种bug,基本在v4包28修复.建议全用28以上或androidx版本
  - fragment重叠问题
  - fragment嵌套时,onActivityResult分发结果无法收到
  - fragment转场动画闪一下的问题
  - fragment一次性出栈多个产生问题
- 使用bug
  - getActivity() == null  
  Activity切换导致获取到的宿主Activity为空.解决 :
    1. onAttach()保存宿主的引用,但有可能会造成内存泄露
    2. 监听Fragment的生命周期,当不再执行相关代码
  - "Can not perform this action after onSaveInstanceState"  
  在宿主走完onSaveInstanceState()生命周期后不能再提交事物.   解决 : 不要在子线程提交事物
  - 重叠  
  由于fragment的自动恢复机制,宿主onSaveInstanceState()会保存fragment信息,宿主重启后会读取保存信息并重建fragment.解决 :
    1. 重写onSaveInstanceState(),使宿主失去自动保存的能力
    2. 创建fragment的时候先去读取保存信息,如果没有相关信息才新建
  - 回退栈管理的问题
  - 宿主生命周期重启时(如Activity切屏)导致自身生命周期重启  
  解决 : setRetainInstance(true).设置后不会因为宿主的生命周期而销毁,并且可以保存数据




# Viewpager的坑
- item设置尺寸设置wrap_content、精确值无效：
- viewpager设置wrap_content无效：viewpager的onMeasure()中是先确定自己大小再测量孩子，不会根据children的测量结果确定自身大小
- Viewpager.setOffscreenPageLimit(0)无效：源码里对该值定义了最小值为1，当设置的值小于最小值时就改成最小值,也就是viewpager最少要同时加载3个child

# 使用Viewpager时Fragment的懒加载
1. 为什么要懒加载：因为viewpager最少要同时加载3页，会影响当前页的加载速度，使用懒加载后页面要展示才加载不展示时伪加载，提升性能(提高当前页的加载速度，也为用户节省流量)
2. 使用viewpager+fragment时fragment的生命周期：此时fragment的生命周期是viewpager通过adapter来控制,并且会在调用fragment生命周期方法之前，先调用Fragment.setUserVisibleHint()方法告知是否可见
3. 实现分析：
   - 可以创建方法dispatchUserVisibleHint()在setUserVisibleHint()方法中调用，通过可见不可见去实现Fragment真正的Resume()和Pause()
   - 第一次调用setUserVisibleHint()如果不可见走Pause()方法时,由于视图还没有创建会引起空指针，所以要增加一个isViewCreated标志位来判断dispatchUserVisibleHint()方法是否可以被调用,待生命周期方法onCreateView()创建视图后对isViewCreated赋值
   - setUserVisibleHint()方法在切换Tab时就会调用，此时不一定发生可见状态切换，所以要增加一个curVisibleState标志位记录当前可见状态，只有当前状态切换后才调用dispatchUserVisibleHint()方法
   - isViewCreated标志位要在生命周期onDestroyView()中更新状态
   - 当Activity跳转回显时Activity.onResume()会触发Fragment.onResume(),此时不会走setUserVisibleHint()方法,所以还需要处理Fragment的onResume()应对这种情况:
   - 同上还需要处理Fragment的onPause()
   - 处理onHiddenChanged()情况
   - 可选1:根据需求还可以判断是否为第一次可见，第为第一次可见时进行一些特别的操作
   - 可先2:当Fragment里再嵌套一层viewpager+fragment时会产生bug,可以增加方法dispatchChildVisibleState()和isParentInvisible()判断父Fragment是否可见以及分发子Fragment是否可见

# 使用Viewpager2时Fragment的懒加载
  Viewpager2能感知Fragment的生命周期，使用懒加载不需要像Viewpager那么复杂，有现成的api可用