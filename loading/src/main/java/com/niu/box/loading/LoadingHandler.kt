package com.niu.box.loading

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

object LoadingHandler {

    private val loadingConfig = mutableMapOf<Int, Int>()
    private const val loadingTag = "frag_loading_tag"

    fun showLoading(act: FragmentActivity) {
        val key = act.hashCode()
        var showCount = 0
        if (loadingConfig.contains(key)) {
            showCount = loadingConfig[key]!!
        }
        showCount++
        loadingConfig[key] = showCount
        if (showCount == 1) {
            showFirstLoading(act)
        }
    }

    fun hideLoading(act: FragmentActivity) {
        val key = act.hashCode()
        var showCount = if (loadingConfig.contains(key)) {
            loadingConfig[key]!!
        } else {
            1
        }
        showCount--
        if (showCount == 0) {
            dismissLoading(act)
        }

        loadingConfig[key] = showCount
    }

    fun clearLoading(act: FragmentActivity) {
        dismissLoading(act)
        loadingConfig.remove(act.hashCode())
    }

    private fun showFirstLoading(act: FragmentActivity) {
        ProgressDialogFragment.newInstance()
            .show(act.supportFragmentManager, loadingTag)
    }

    private fun dismissLoading(act: FragmentActivity) {
        act.supportFragmentManager.findFragmentByTag(loadingTag)?.let {
            if (it is DialogFragment) {
                it.dismissAllowingStateLoss()
            }
        }
    }
}