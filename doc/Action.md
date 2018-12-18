#Action中API
> 主要基于appium提供基于ios gestures再次封装而来。

提供方法包括：
- click
- swipePage :方向参数是"up"，"down"，"left"，"right"
- swipe: 起止坐标以及滑动时间
- move: 滑动到终止元素，以及纵向方向
- tapByCoordinate: 通过坐标点击
- iOSAlertPresented: Alert出现
- setValue: 输入内容
- isElementPresented: 判断元素是否展示
- waitForElementPresent: 等待元素时间，超时设置20s
- iOSStartPerfRecord: 开始录制ios性能，传入参数为Profile名称
- iOSStopPerfRecord: 保存录制的文件，传入内容Profile名称和保存的文件名称

具体总结见Action类
