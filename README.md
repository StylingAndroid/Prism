###Prism
#A dynamic UI colouring library

![Build Status](https://ci.stylingandroid.com/jenkins/buildStatus/icon?job=Prism "Build Status")

To use the libraries below you will need to add the Styling Android repo:

    repositories {
        maven {
            url 'https://dl.bintray.com/stylingandroid/maven/'
        }
    }

#Prism
[ ![Download](https://api.bintray.com/packages/stylingandroid/maven/prism/images/download.svg) ](https://bintray.com/stylingandroid/maven/prism/_latestVersion)

Prism is the main library and contains the core functionality. It can perform colour changing of Views built in to the core Api, support libraries,
or even your custom _Views_. And all this without requiring any dependencies on any external libraries (not even support libraries) - so it's
extremely lightweight.

It can be included in your project by adding the following dependency:

    dependencies {
        compile 'com.stylingandroid.prism:prism:0.9.2'
    }

Prism is divided in to three object types: _Setters_, _Filters_ and _Triggers_. Each of these individually is really quite simple, but it is combining
them along with some intelligent factories is what makes Prism powerful. So let's take a look at each of them in turn.

### Setters

A setter allows us to set the colour of a UI object - typically a _View_ but not always, as we shall see. The basic function of a _Setter_ is to map a
generic `setColour(int colour)` call to a specific method call on the View that it wraps. For example, the built in _ViewBackgroundColourSetter_ will
map to `setBackgroundColor(int color)`. In some cases the `Setter` can provide different behaviour on different versions of Android - for example
_StatusBarColourSetter_ will have no effect on pre-Lollipop devices because status bar colouring is not supported prior to Lollipop. However it will
degrade gracefully (no crashing!) so you can safely use it and allow the setter implementation to worry about how to apply the colour at runtime.

The standard setters built in to Prism are:

* `FabSetter(FloatingActionButton fab)` - sets the background tint colour on the design support library implementation of _FloatingActionButton_
* `StatusBarSetter(Window window)` - sets the status bar colour on the supplied Window. Note that this does not take a _View_.
* `TextSetter(TextView textView)` - sets the text colour on the supplied _TextView_.
* `ViewBackgroundSetter(View view)` - sets the background colour of the supplied _View_.

Of course you can create your own _Setters_ for custom _Views_ which may require have additional components which can be coloured, and you can create
multiple _Setters_ for a single _View_ to sett different attributes and add them all to the Prism to colour multiple components simultaneously.

There will be some examples in the advanced documentation explaining how to create and use custom _Setters_.

### Filters

A _Filter_ allows us to modify a colour. Prism is generally called with a single colour value and sometimes we may want to apply different variants of
that colour to different UI components - _Filters_ enable us to do just that. In its most basic form a _Filter_ takes a colour value as in input,
performs some colour transformation upon that colour, and returns the transformed colour value. The basic built-in colour filters are:

* `IdentityFilter()` - returns the colour which was input.
* `ShadeFilter(float amount)` - darkens the colour by effectively mixing in black. The value is a float from 0.0-1.0 which determines how much black
to add. A value of 0 makes no change, and a value of 1.0 will produce pure black
* `TintFilter(float amount)` - lightens the colour by effectively mixing in white. The value is a float from 0.0-1.0 which determines how much white
to add. A value of 0 makes no change, and a value of 1.0 will produce pure white.

Once again it is possible to create your own _Filters_ and that will be covered in the advanced documentation.

### Triggers

A _Trigger_ is what triggers colour change events. Typically it will call `setColour(in colour)` on a _Prism_ instance to propagate a colour change to
 all of the _Setters_ which have been registered with that instance.

There are no _Triggers_ currently built in to the core _Prism_ library because that would require some library dependencies. However there are a
 couple of optional plug-in libraries which provide some _Trigger_ implementations.


Once again, you can create custom _Triggers_ and details will be provided in the advanced documentation.

### Prism

Now that we have our three types of components, we need to wire them up and that is what the _Prism_ instance does. Each _Prism_ instance has zero or one
_Triggers_ (which will cause a colour change), and one or more _Setters_ (which will be called in response to a trigger colour change). Each _Setter_
 can have a _Filter_ attached which will transform the trigger colour before applying it to the _Setter_.

As well as this basic wiring, _Prism_ also contains some intelligent factories which will construct appropriate _Setter_ implementations
automatically for you. For example, if you pass in a design library _FloatingActionButton_ to `Prism#background()` it will automatically create a
_FabColourSetter_ for you.

A Prism instance is constructed using a builder pattern which constructs the components, wires them up, and then attaches to the _Trigger_ and then
responds to trigger events.

Lets look at how we can create a simple Prism instance:

```java
private static final float TINT_FACTOR_50_PERCENT = 0.5f;

private TextView textView;
private FloatingActionButton fab;
private AppBarLayout appBar;

private Prism prism;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = (TextView)findViewById(R.id.text_view);
    appBar = (AppBarLayout) findViewById(R.id.app_bar);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    fab = (FloatingActionButton) findViewById(R.id.fab);

    setSupportActionBar(toolbar);

    Filter tint = new TintFilter(TINT_FACTOR_50_PERCENT);
    prism = Prism.Builder.newInstance()
        .background(appBar)
        .background(getWindow())
        .text(tetView)
        .background(fab, tint)
        .build();
}

@Override
protected void onDestroy() {
    if (prism != null) {
        prism.destroy();
    }
    super.onDestroy();
}
```

That will connect everything up without a _Trigger_ - the zero-argument form `newInstance()` call did that. We can now trigger it manually by
simply calling `setColour(int colour)` on the `prism` instance:

```java
prism.setColour(Colour.RED);
```

and that will set the background colour of the `appBar`, the status bar on Lollipop+ (that's what the `getWindow()` call is doing), and the text colour
of the `textView` all to red. For the `fab` it will lighten it by adding 50% white to the red to produce a much lighter shade.

Subsequent calls to `setColour(int colour)` on the `prism` instance will cause further colour changes.

So that's quite a neat way of connecting up some colour changing behaviours but there's nothing really impressive going on other than Prism saving us
from having to implement a lot of boilerplate code to do the same thing. However when we add a _Trigger_ in to the mix we really start seeing some
impressive behaviour for very little extra work. I would urge you to take a look at the _ViewPagerTrigger_ documentation below to really see this
in action.

#Colour vs. Color
As you may have guessed from the spelling of the word 'colour' throughout this readme I am English and prefer to use the English spelling of 'colour'.
I am fully aware that there are many people who spell it 'color'. In order to give people the freedom to use whichever spelling they
prefer, Prism will accept *both* spellings. So you can call `setColor(int color)` in place of `setColour(int colour)` if you prefer. However, using
the English spelling of 'colour' will be very slightly better performance because there will be one less method call as internally
`setColor(int color)` simply calls `setColour(int colour)`. So if you chose to spell 'colour' incorrectly you will suffer a very minor performance
hit!

#Prism ViewPager
[ ![Download](https://api.bintray.com/packages/stylingandroid/maven/prism-viewpager/images/download.svg) ](https://bintray.com/stylingandroid/maven/prism-viewpager/_latestVersion)

Prism-viewpager is a plugin for prism which provides a ViewPager trigger to trigger colour changes, and also a ViewPager Setter which enables the
 ViewPager overscroll indicator to be coloured in a backwardly compatible way. There are different behaviours on pre-ICS, ICS -> Kitkat, and Lollipop
 &amp; later.

It can be included in your project by adding the following dependency:

    dependencies {
        compile 'com.stylingandroid.prism:prism-viewpager:0.9.2'
    }

There are two components in the `prism-viewpager` library: a _Trigger_ to trigger colour changes from a ViewPager, and a _Setter_ to get the
overscroll glow on the _ViewPager_.

### ViewPagerTrigger

_ViewPagerTrigger_ is actually really easy to implement. The only requirement is that your _PagerAdapter_ must implement the _ColourProvider_
interface (ColourProvider is provided in the package `com.stylingandroid.prism.viewpager`):

```java
public interface ColourProvider {
    @ColorInt int getColour(int position);
    int getCount();
}
```

`getCount()` is already defined in the _PagerAdapter_, so you get that for free. `getColour(int position)` requires your _PagerAdapter_ to provide
a colour value for each page within the _ViewPager_ and changing pages will then trigger colour changes to _Prism_.

Hooking this up is really easy:

```java
public Trigger getTrigger(ViewPager viewPager, PagerAdapter pagerAdapter) {
    if (pagerAdapter instanceof ColourProvider) {
        return ViewPagerTrigger(viewPager, (ColourProvider)pagerAdapter);
    }
    return null;
}
```

For those that chose an alternative spelling of 'colour' you can implement the `ColorProvider` interface instead. You'll need to change `getTrigger()`
accordingly, but this will get wrapped inside an Adapter class internally so, once again, there is a minor performance hit for doing this.

You can now use this when building a _Prism_ instance:

```java
    ColourFilter tint = new TintFilter(TINT_FACTOR_50_PERCENT);
    Prism prism = Prism.newInstance(getTrigger(viewPager, pagerAdapter))
        .background(appBar)
        .background(getWindow())
        .text(tetView)
        .background(fab, tint)
        .build();
```

For that we get a smooth animated colour transition whwnever the user select a new page. If the user drags between pages, the colour transition
animation will follow the user's finger.

### ViewPagerGlowSetter

This is built in to the palette-viewpager library. Whenever you create a _Prism_ instance with a _ViewPagerTrigger_ it automatically registers the
_ViewPagerGlowFactory_ with Prism, so the following code will automatically create overscroll glow colouring on your _ViewPager_:

```java
    Filter tint = new Filter(TINT_FACTOR_50_PERCENT);
    ColourSetter prism = Prism.newInstance().add(getTrigger(viewPager, pagerAdapter))
        .colour(viewPager, tint)
        .build();
```

If you're not using a _ViewPagerTrigger_ it's pretty easy to manually create the _ViewPagerGlowSetter_:

```java
    Filter tint = new TintFilter(TINT_FACTOR_50_PERCENT);
    Setter glowSetter = ViewPagerGlowSetter.newInstance(viewPager, tint);
    Prism prism = Prism.newInstance().add(someOtherTrigger)
        .add(glowSetter)
        .build();
```
# Prism PaletteTrigger

Details coming soon...
