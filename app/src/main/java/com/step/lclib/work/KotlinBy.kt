package com.step.lclib.work


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.step.lclib.R
import com.step.lclib.work.CLDelegates.viewFinder
import com.step.lclib.work.page.StorageActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class VipVideoSaleTitleView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attr, defStyleAttr) {
    init {
        lclog("开始执行 init")
        initView()
        lclog("开始执行 init 执行完成")
    }

    private val videoContainer: RecyclerView by lazy {
        lclog("开始执行 lazy init ")
        findViewById<RecyclerView>(R.id.rv_kotlin_by)
    }

    private fun initView() {
        lclog("开始执行 init 方法内部 11")
        LayoutInflater.from(context).inflate(R.layout.test_kotlin_by, this)
        lclog("开始执行 init 方法内部 22")
        lclog("测试VIP视频：this: " + this)
        lclog("测试VIP视频：videoContainer " + (videoContainer ?: "你是空"))
        post {
            lclog("测试VIP视频：post this: " + this)
            lclog("测试VIP视频：post videoContainer " + (videoContainer ?: "你是空"))
        }
//        videoContainer.layoutManager = LinearLayoutManager(context)
//        videoContainer.isNestedScrollingEnabled = false
//        videoContainer.isFocusable = false
    }
}

/**
 * 属性委托
 */
object CLDelegates {

    fun <T : View> View.viewFinder(@IdRes id: Int): ReadOnlyProperty<Any?, T?> {
        lclog("开始执行 viewFinder 初始化 $this")
        return ViewFinderProperty(this, id)
    }
}

private class ViewFinderProperty<V, T : View?>(
    private val parent: View?,
    @IdRes private val id: Int
) :
    ReadOnlyProperty<V, T?> {

    private var view: T? = null

    override fun getValue(thisRef: V, property: KProperty<*>): T? {
        lclog("开始执行 viewFinder 获取值 $view   $parent")
        if (parent == null) {
            return RecyclerView(StorageActivity.context) as T
        }
        return view ?: parent?.findViewById<T?>(id).also { view = it }
    }
}