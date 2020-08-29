# Watery
## 一个使用android studio开发的记录饮水的app（课设作业）


## 软件主要功能
1）	实现登录注册功能
2）	可选择并填写饮品的种类和摄入量，将记录添加到手机的记录页面中，也可以删除相应记录，添加后会修改数据并提示还需摄入的水分。
3）	可根据用户填写的资料测试出每日所需饮水量，并能设置为新的目标

## 使用说明
登录页面
![Image text](https://github.com/Quinnyuu/Watery/blob/master/app/src/main/res/productShow/login.png)
注册页面
 ![Image text](https://github.com/Quinnyuu/Watery/blob/master/app/src/main/res/productShow/resign.png)
点击中间的加号，可添加今日所喝饮品，点击饮料会弹出输入框，可输入摄入量。
![Image text](https://github.com/Quinnyuu/Watery/blob/master/app/src/main/res/productShow/today.png)
![Image text](https://github.com/Quinnyuu/Watery/blob/master/app/src/main/res/productShow/today2.png)
点击确认在中间的页面中会显示添加的饮料，右滑可删除记录。
![Image text](https://github.com/Quinnyuu/Watery/blob/master/app/src/main/res/productShow/record.png)
在档案页面中可以设置个人信息，并测试每日所需摄水量，可以设置为目标
![Image text](https://github.com/Quinnyuu/Watery/blob/master/app/src/main/res/productShow/me.png)

## 软件的不足：
1.	手机底部导航栏的问题，遇到弹窗会弹出并不能隐藏
2.	Keyboard的问题，本来想要不调用系统的键盘，自己设计一个键盘，也通过继承keyboard写以一个自定义的键盘，但当点击弹出popwindow的时候不知道为什么，弹出了上面的输入框却没有弹出自定义的键盘，由于时间问题我没有去细究，以后会去研究并实现这个功能，使页面更加完善。
3.	未完成数据库提取出体重的信息数据并设置到页面上。
