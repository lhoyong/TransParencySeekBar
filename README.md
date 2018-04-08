# TransParencySeekBar

## Intro

* Default Style

![defstyle](https://github.com/lhoyong/TransParencySeekBar/blob/master/gif/def.gif)

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
            implementation 'com.github.lhoyong:TransParencySeekBar:0.0.3'
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

| Name  | Type  | Example | Deafult |
| :------------ |:---------------:| -----:        | -----: |
| enable     | Boolean | app:enable="true"        | true   |
| backgroundColor      | Color        |   app:backgroundColor="#ffffff" | #44000000
| progressColor | Color       |    app:progressColor="#000000"        | #66000000
| maxProgress     | Integer | app:maxProgress="300"        | 100   |
| progress     | Integer | app:progress="150"        | 0   |
| thumb     | Reference | app:thumb="@drawable/thumb"        |    |
| thumbClickable     | Boolean | app:thumbClickable="true"        | false   |
