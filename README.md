# resourceflavors
这是一个多渠道修改资源文件的gradle插件

你可能遇到这样一个需求：公司产品需要做定制化开发，即把公司AP的包名、app名字、appIcon、引导页...等等一些小部件更换掉，然后为客户定制化他们需要的app
但不需要修改功能模块，或仅修改一小部分.这时候你可能会利用系统的多渠道打包方式实现，但是资源文件却很难替换。这时候你可能需要用到该库。

# how to use

1、在根目录的依赖中加入

classpath 'com.atom.plugin:ResourceFlavorsPlugin:1.1.0'

2、在主项目build文件加入

apply plugin: 'com.atom.resourceflavors' //应用插件

3、主项目加入配置参数

rfp{

    resourceDir 'definepic'//需要替换的资源文件路径名，必须在根目录内
    
    appName 'app'//主项目名字
    
}

你需要在resourceDir下以你的渠道命名文件下，然后按res目录的结构放入资源文件，实现替换，具体可参照本demo
