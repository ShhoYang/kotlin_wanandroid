package com.hao.easy.user.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.widget.TextView
import com.hao.easy.base.extensions.snack
import com.hao.easy.base.ui.BaseActivity
import com.hao.easy.base.ui.WebActivity
import com.hao.easy.user.R
import kotlinx.android.synthetic.main.user_activity_about.*
import org.jetbrains.anko.browse


class AboutActivity : BaseActivity(), View.OnClickListener, View.OnLongClickListener {

    override fun getLayoutId() = R.layout.user_activity_about

    override fun initView() {
        title = "關於項目"
        tvVersion.text = getVersionName()


        tvUserHome.setOnClickListener(this)
        tvDownloadLink.setOnClickListener(this)
        tvProjectLink.setOnClickListener(this)
        tvEmail.setOnClickListener(this)
        tvThanks.setOnClickListener(this)

        tvUserHome.setOnLongClickListener(this)
        tvDownloadLink.setOnLongClickListener(this)
        tvProjectLink.setOnLongClickListener(this)
        tvEmail.setOnLongClickListener(this)
        tvThanks.setOnLongClickListener(this)
    }

    private fun getVersionName(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            "V${packageInfo.versionName}"
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }
    }

    private fun copy(text: String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.primaryClip = ClipData.newPlainText(null, text)
    }

    override fun onClick(v: View) {
        when (v) {
            tvUserHome -> WebActivity.start(this, "個人首頁", "https://haoshi.co")

            tvDownloadLink -> browse("https://haoshi.co/wanandroid.apk")

            tvProjectLink -> WebActivity.start(this, "玩Android", tvProjectLink.text.toString())

            tvThanks -> WebActivity.start(this, "鸿洋玩Android开发Api", "http://www.wanandroid.com/blog/show/2")
        }
    }

    override fun onLongClick(v: View): Boolean {
        if (v == tvThanks) {
            copy("http://www.wanandroid.com/blog/show/2")
        } else {
            copy((v as TextView).text.toString())
        }
        v.snack("已複製到剪切板")
        return true
    }
}
