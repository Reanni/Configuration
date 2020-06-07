package cn.reanni.dependencies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.reanni.dependencies.R
import kotlinx.android.synthetic.main.common_base_content_layout_default.*

abstract class LayoutFragment : Fragment() {

    /**
     * Activity根布局
     */
    private lateinit var rootLayout: View
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**初始化主题 */

        /**初始化布局 */
        if (attachTitleLayoutId() < 0) {
            rootLayout = inflater.inflate(attachContentLayoutId(), null)
        } else {//单独的标题栏布局
            rootLayout = inflater.inflate(R.layout.common_base_content_layout_default, null)
            title_layout_stub.layoutResource = attachTitleLayoutId()
            title_layout_stub.inflate()
            content_layout_stub.layoutResource = attachContentLayoutId()
            content_layout_stub.inflate()
            if (attachTitleLayoutId() == -1) {
            }//对统一的标题栏做一些统一初始化
        }
        onInitLayout()
        /**注册第三方*/

        /**连接Presenter*/
//        presenter.fetch()

        return rootLayout
    }

    override fun onResume() {
        super.onResume()
        onUpdateLayout()
    }


    protected open fun attachTitleLayoutId(): Int = -1

    protected abstract fun attachContentLayoutId(): Int

    /**
     * 初始化布局,onCreate()中调用,只调用一次
     */
    protected abstract fun onInitLayout()

    /**
     * 更换布局,由不可交互转换到可交互状态后调用,多次调用
     */
    protected open fun onUpdateLayout() {}


    /******************************************************* IView里的接口方法，Activity跟Fragment类保持方法一致  start *********************************************************************/
/*
    override fun onResultRight(data: Any?, resultType: String): Unit {}

    override fun onResultError(errorMes: String, resultType: String) {}*/

    /******************************************************* IView里的接口方法，Activity跟Fragment类保持方法一致 end ************************************************************************/
}