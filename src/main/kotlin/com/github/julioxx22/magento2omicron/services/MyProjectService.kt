package com.github.julioxx22.magento2omicron.services

import com.github.julioxx22.magento2omicron.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
