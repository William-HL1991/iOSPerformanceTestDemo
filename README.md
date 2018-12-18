# iOSPerformanceTestDemo
这个Demo主要用于做ios性能自动化，配合xcode10可以获取手机上安已安装任一app的性能。

##基础要求
- JDK > 1.8
- Xcode版本 > 10
- appium版本大于 > 1.8
- node
- Maven 
- ios-deploy
- ideviceinstaller
- libimobiledevice
- Xcode Command Line Tools

##App元素定位
> 前提：xcode10以及新版本WDA支持操作Relesae App

首先需要获取对应产品的bundleId，需要强大的工具 ideviceinstaller。通过ideviceinstaller -l查看所有app的bundleId。
通过appum-inspector或者通过启动WDA然后访问http://localhost:8100/inspector查看。

##性能数据记录
主要通过instrument抓取当前被测试app的性能，非Debug版本app中ALLOCATIONS、LEAKS等不会记录具体的内容。所有性能数据均
保存在当前项目的根目录中，已case的名称开头。

appium设置及使用方式见我的简书[Appium-ios真机](https://www.jianshu.com/p/1e73ac874258)