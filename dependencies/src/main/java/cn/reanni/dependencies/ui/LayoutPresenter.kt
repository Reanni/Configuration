package cn.reanni.dependencies.ui

open class LayoutPresenter<out V : IView> {

    private lateinit var iView: V

    open fun fetch() {}

}