package cn.reanni.dependencies.ui

interface IView {
    /**
     *  网络有返回,获取到正确的数据
     */
    fun onResultRight(data: Any?, resultType: String)

    /**
     *  网络有返回,由于请求参数错误等问题,获取到错误提示
     */
    fun onResultWrong(errorMes: String, resultType: String)

    /**
     *  请求网络发生错误
     */
    fun onNetworkError(exception: Exception)

}