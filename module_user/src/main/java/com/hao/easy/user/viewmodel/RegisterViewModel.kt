package com.hao.easy.user.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hao.easy.base.extensions.subscribeBy
import com.hao.easy.base.viewmodel.BaseViewModel
import com.hao.easy.user.repository.Api

/**
 * @author Yang Shihao
 * @date 2018/11/25
 */
class RegisterViewModel : BaseViewModel() {

    val registerLiveData = MutableLiveData<String?>()

    fun register(username: String, password: String) {
        Api.register(username, password, password).subscribeBy({
            registerLiveData.value = null
        }).add()
    }
}