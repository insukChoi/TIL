package com.insuk.kotest.isolationModes

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

// 글로벌 셋팅
class ProjectConfig : AbstractProjectConfig() {
    override val isolationMode: IsolationMode = IsolationMode.SingleInstance
}