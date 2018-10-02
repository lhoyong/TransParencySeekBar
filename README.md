# TransParencySeekBar

## Intro

* Default Style

![defstyle](https://github.com/lhoyong/TransParencySeekBar/blob/master/gif/defStyle.gif)

* Clickable Style

![clickable](https://github.com/lhoyong/TransParencySeekBar/blob/master/gif/clickable.gif)

----

## How to use

* Add your project

build.gradle (project)
<pre>
    <code>
        repositories {
                    maven { url 'https://jitpack.io' }
        }
    </code>
</pre>

build.gradle (app)

<pre>
    <code>
        dependencies {
            implementation 'com.github.lhoyong:TransParencySeekBar:0.0.4'
        }
    </code>
</pre>

    <com.github.lhoyong.transparencyseekbar.TransParencySeekBar
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:maxProgress="300"
        app:progress="150"
        app:thumbClickable="true" />

----

## Attributes

[attr.xml](https://github.com/lhoyong/TransParencySeekBar/blob/master/lib/src/main/res/values/attrs.xml)

| Name  | Type  | Example | Deafult |
| :------------ |:---------------:| -----:        | -----: |
| tb_enable | Boolean | app:enable="true"        | true   |
| tb_backgroundColor      | Color        |   app:backgroundColor="#ffffff" | #44000000
| tb_progressColor | Color       |    app:progressColor="#000000"        | #66000000
| tb_maxProgress     | Integer | app:maxProgress="300"        | 100   |
| tb_progress     | Integer | app:progress="150"        | 0   |
| tb_thumb     | Reference | app:thumb="@drawable/thumb"        |    |
| tb_thumbClickable     | Boolean | app:thumbClickable="true"        | false   |