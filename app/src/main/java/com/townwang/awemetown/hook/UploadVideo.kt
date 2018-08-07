/*
 * Copyright © 文科中的技术宅
 * blog:https://www.townwang.com
 */
package com.townwang.awemetown.hook

import android.content.Context
import com.townwang.awemetown.base.baseImpl.BaseHook
import com.townwang.awemetown.config.VConfig
import com.townwang.awemetown.mvp.bean.HookBean
import com.townwang.awemetown.utils.VUtils
import de.robv.android.xposed.XC_MethodReplacement

/**
 * @author Town
 * @created at 2018/7/16 0:21
 * @Last Modified by: Town
 * @Last Email: android@townwang.com
 * @Last Modified time: 2018/7/16 0:21
 * @Remarks  upload video
 */

object UploadVideo : BaseHook() {

    fun start() {
        initBean()
        hookFunction()
    }

    override fun initBean(): HookBean {
        bean = HookBean()
        if (VUtils.getAwemeVersionCode() in 200..219) {
            bean.configCode = VUtils.getAwemeVersionCode()
            bean.className = VConfig.UPDATE_CLASS_NAME_200
            bean.funName = VConfig.FUNC_NAME_I
        } else {
            bean.configCode = VUtils.getAwemeVersionCode()
            bean.className = VConfig.UPDATE_CLASS_NAME_220
            bean.funName = VConfig.FUNC_NAME_B
        }
        return bean
    }

    private fun hookFunction() {
        try {
            if (bean.configCode in 200..219) {
                findAndHookMethod(XC_MethodReplacement.returnConstant(59))
            } else {
                findAndHookMethod(Context::class.javaObjectType, XC_MethodReplacement.returnConstant(250f))
            }
        } catch (e: Throwable) {

        } catch (e: Exception) {

        }
    }
}