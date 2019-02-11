# ButtonView-master
多状态自定义按钮

截图
-------
![](https://github.com/Richard-person/ButtonView-master/blob/master/screenshot/test1.png)

使用方法
-------
1.导入<br>
 Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Richard-person:ButtonView-master:v1.0.2'
	}

2.XML<br>

<com.richard.buttonview.ButtonView<br>
&emsp;&emsp;android:layout_width="match_parent"<br>
&emsp;&emsp;android:layout_height="40dp"<br>
&emsp;&emsp;android:layout_margin="5dp"<br>
&emsp;&emsp;android:onClick="clickButton"<br>
&emsp;&emsp;app:bv_backColor="#212121"<br>
&emsp;&emsp;app:bv_bottomLeftRadius="10dp"<br>
&emsp;&emsp;app:bv_bottomRightRadius="30dp"<br>
&emsp;&emsp;app:bv_enabled="true"<br>
&emsp;&emsp;app:bv_text="忘记密码"<br>
&emsp;&emsp;app:bv_text_color="#ffffff"<br>
&emsp;&emsp;app:bv_text_size="14sp"<br>
&emsp;&emsp;app:bv_topLeftRadius="10dp"<br>
&emsp;&emsp;app:bv_topRightRadius="30dp"/><br>

控件属性介绍
-------

* bv_icon 按钮小图标
* bv_text 文本
* bv_text_size 文本字体大小
* bv_text_color 文本字体颜色
* bv_backColor 按钮背景颜色
* bv_radius 四个角弧度
* bv_topLeftRadius 左上角弧度
* bv_topRightRadius 右上角弧度
* bv_bottomLeftRadius 左下角弧度
* bv_bottomRightRadius 右下角弧度
* bv_enabled 是否禁用按钮
