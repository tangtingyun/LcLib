package com.step.lclib.work

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


interface OnPermResult {
    fun granted()

    fun rejected()
}

open class SimplePermResult : OnPermResult {
    override fun granted() {
    }

    override fun rejected() {
    }
}

object PermissionDialog {


    @JvmStatic
    fun requestStorageNoTip(ctx: Activity?, cb: OnPermResult?) {
        requestNoTip(
            ctx,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            cb
        )
    }

    @JvmStatic
    fun requestContactNoTip(ctx: Activity?, cb: OnPermResult?) {
        requestNoTip(
            ctx,
            arrayOf(Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS),
            cb
        )
    }

    @JvmStatic
    fun requestNoTip(ctx: Activity?, permissions: Array<String>, cb: OnPermResult?) {
        if (ctx == null) return
        if (cb == null) return
        permissions.filter { per ->
            ContextCompat.checkSelfPermission(ctx, per) != PackageManager.PERMISSION_GRANTED
        }.takeIf { it.isNotEmpty() }?.let { needRequestPermission ->
            AndPermission.with(ctx)
                .runtime()
                .permission(needRequestPermission.toTypedArray())
                .onGranted { _ -> cb.granted() }
                .onDenied { denyPermissions ->
                    Permission.transformText(ctx, denyPermissions).joinToString()
                    cb.rejected()
                }
                .start()
        } ?: cb.granted()
    }

    @JvmStatic
    fun goSetting(ctx: Activity?) {
        ctx?.apply {
            AndPermission.with(this)
                .runtime()
                .setting()
                .start(12);
        }
    }
}