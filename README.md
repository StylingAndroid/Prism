###Prism
#A dynamic UI colouring library

![Build Status](https://ci.stylingandroid.com/jenkins/buildStatus/icon?job=Prism "Build Status")

To use the libraies below you will need to add the Styling Android repo:

    repositories {
        maven {
            url 'https://dl.bintray.com/stylingandroid/maven/'
        }
    }

#Prism
[ ![Download](https://api.bintray.com/packages/stylingandroid/maven/prism/images/download.svg) ](https://bintray.com/stylingandroid/maven/prism/_latestVersion)

Prism is the main library and contains the core functionality.

It can be included in your project by adding the following dependency:

    dependencies {
        compile 'com.stylingandroid:prism:0.1.0'
    }



#Prism VewPager
[ ![Download](https://api.bintray.com/packages/stylingandroid/maven/prism-viewpager/images/download.svg) ](https://bintray.com/stylingandroid/maven/prism-viewpager/_latestVersion)

Prism-viewpager is a plugin for prism which provides a ViewPager trigger to trigger colour changes, and also a ViewPager Setter which enables the
 ViewPager overscroll indicator to be coloured in a backwardly compatible way. There are different behaviours on pre-ICS, ICS -> Kitkat, and Lollipop
 &amp; later.

It can be included in your project by adding the following dependency:

    dependencies {
        compile 'com.stylingandroid:prism-viewpager:0.1.0'
    }


