package cn.reanni.dependencies.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.reanni.dependencies.R
import kotlinx.android.synthetic.main.common_base_content_layout_default.*

abstract class LayoutActivity<P : LayoutPresenter<IView>> : AppCompatActivity(), IView {

    /**
     * Activity根布局
     */
    private lateinit var rootLayout: View

    private val defaultTitleLayoutId = R.layout.common_title_default

//    private var presenter: P
/* */
    /**
     * 标题栏布局
     *//*
    private lateinit var titleLayout: View
    */
    /**
     * 内容区域布局
     *//*
    private lateinit var contentLayout: View*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**初始化主题 */

        /**初始化布局 */
        if (attachTitleLayoutId() < 0) {
            rootLayout = layoutInflater.inflate(attachContentLayoutId(), null)
            setContentView(rootLayout)
        } else {//单独的标题栏布局
            rootLayout = layoutInflater.inflate(R.layout.common_base_content_layout_default, null)
            setContentView(rootLayout)
            title_layout_stub.layoutResource = attachTitleLayoutId()
            title_layout_stub.inflate()
            content_layout_stub.layoutResource = attachContentLayoutId()
            content_layout_stub.inflate()
            if (attachTitleLayoutId() == defaultTitleLayoutId) {
            }//对统一的标题栏做一些统一初始化
        }
        onInitLayout()
        /**注册第三方*/

        /**连接Presenter*/
//        presenter = newPresenter()
//        presenter.fetch()
    }

    override fun onResume() {
        super.onResume()
        onUpdateLayout()
    }

    /*  private fun newPresenter(): P {
          return (javaClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.filter {
              LayoutPresenter::class.java.isAssignableFrom(it as Class<*>)
          }?.get(0)?.let {
              (it as Class<*>).getConstructor().newInstance() as P
          } ?: throw NullPointerException("presenter in MVPActivity must not be null.")
      }*/

    protected open fun attachTitleLayoutId(): Int = defaultTitleLayoutId

    protected abstract fun attachContentLayoutId(): Int

    /**
     * 初始化布局,onCreate()中调用,只调用一次
     */
    protected abstract fun onInitLayout()

    /**
     * 更新布局,由不可交互转换到可交互状态后调用,多次调用
     */
    protected open fun onUpdateLayout() {}

    /******************************************************* IView里的接口方法，Activity跟Fragment类保持方法一致  start *********************************************************************/

    override fun onResultRight(data: Any?, resultType: String) {}

    override fun onResultWrong(errorMes: String, resultType: String) {}

    override fun onNetworkError(exception: Exception) {}

    /******************************************************* IView里的接口方法，Activity跟Fragment类保持方法一致 end ************************************************************************/


}