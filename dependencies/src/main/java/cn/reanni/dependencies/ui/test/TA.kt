package cn.reanni.dependencies.ui.test

import cn.reanni.dependencies.ui.LayoutActivity

class TA : LayoutActivity<TP>() {
    override fun attachContentLayoutId(): Int {
        return 0
    }

    override fun onInitLayout() {}
}