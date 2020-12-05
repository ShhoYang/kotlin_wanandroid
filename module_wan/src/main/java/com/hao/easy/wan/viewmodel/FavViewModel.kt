package com.hao.easy.wan.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hao.easy.base.Config
import com.hao.easy.base.extensions.subscribeBy
import com.hao.easy.base.viewmodel.BaseListViewModel
import com.hao.easy.wan.model.Article
import com.hao.easy.wan.repository.Api

/**
 * @author Yang Shihao
 * @date 2018/12/3
 */
class FavViewModel : BaseListViewModel<Article>() {

    val deleteResult = MutableLiveData<Boolean>()

    override fun loadData(page: Int, onResponse: (ArrayList<Article>?) -> Unit) {
        Api.getMyFav(page - 1).subscribeBy({
            onResponse(it?.datas)
        }, {
            onResponse(null)
        }).add()
    }

    fun cancelCollect(item: Article, position: Int) {
        Api.cancelCollectFromFav(item.id, item.originId).subscribeBy({
            deleteResult.value = true
            refresh()
            Config.refresh()
        }).add()
    }
}