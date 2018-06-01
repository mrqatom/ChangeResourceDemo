package com.atom

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

public class ResourceFlavorsPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.add("rfp", FlavorType)
        project.afterEvaluate {
            FlavorType ext = project.rfp
            def resourceDir = ext.resourceDir
            def appName = ext.appName
            //非打包时不做任何操作
            def startParameter = project.gradle.startParameter
            def targetTasks = startParameter.taskNames

            targetTasks.each {
                if (!it.contains("assemble") && !it.contains("aR")) {
                    return
                }
            }

            project.android.applicationVariants.all { variant ->
                String variantName = variant.name.capitalize()
                def variantFlavorName = variant.flavorName
                Task preBuild = project.tasks["pre${variantName}Build"]
                if (variantFlavorName == null || "" == variantFlavorName) {
                    return
                }
                preBuild.doFirst {
                    project.copy {
                        from "../${resourceDir}/${variantFlavorName}"
                        into "../${appName}/src/main/res"
                    }
                    println "${variantFlavorName} resource is changed!"
                }
            }
        }
    }
}
