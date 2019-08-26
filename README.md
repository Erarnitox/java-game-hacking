# Sauerbraten Hack

This hack is an example on how to use and what you can do with the Game Hacking Library `GHTools`

It's made to be understandable and easy to adapt to other games.

## Features

####Overlay
The hack makes use of JavaFx to overlay things like a menu over the Game.

I used this method because it's simple and can be used on any Game to display a lot of different things.

You can find some examples below.

####Godmode
For the Campaign i made a simple Godmod Toggle with the following features:
- Unlimited Ammo
- Take No Damage
- No Recoil
- Ultra Rapid Fire
- Deal Insane Damage
![Godmode](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Godmode.gif)

This is an examaple on how to do basic memory editing like reading and writing from or to memory.

But this also demonstrates how to change opcodes of the binary in memory at runtime.

####Aimbot
![Aimbot](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Aimbot.gif)
Demonstrates how one could implement an Aimbot.

####ESP
![ESP](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/ESP.gif)
Demonstrates how one could implement an ESP hack using the `GHTools`.

The calculations are specific to OpenGL!

The main point of this was to demonstrate how to draw the ESP Boxes to the JavaFX Overlay.

####Rake Mode
![Rake](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Rake.gif)
Since we are using JavaFX we can display all kinds of stuff to the screen.

To demonstrate this i used this opportunity to honor [**Rake**](https://guidedhacking.com/members/rake.26782/).

####Multi-threaded
The whole hack is object oriented and multi-threaded so one can only make use of the pieces one needs or activate different modes at the same Time. 
![Aimbot&ESP](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Aimbot%26ESP.gif)
In this example i made a seperate mode that activates the ESP and Aimbot at the same time.

##Download
If you just want to use this hack you can download a pre-compiled jar executable [**here**](https://github.com/Erarnitox/java-sauerbraten-hack/raw/master/bin/Cube2Hack.jar).

#GHTools
`GHTools` is a wrapper around [**JNA**](https://github.com/java-native-access) makes it easier to use and provides some new functionality for game hacking as well.

### Installing

- To use `GHTools` you can either download it from [**here**](https://github.com/Erarnitox/java-sauerbraten-hack/raw/master/bin/GHTools.jar) and import in in your project.

- Or get the source for it [**here**](https://github.com/Erarnitox/java-sauerbraten-hack/tree/master/src/com/guidedhacking).

If you want to use the source you need to download and import these in your project first:
1. [**JNA**](http://repo1.maven.org/maven2/net/java/dev/jna/jna/5.1.0/jna-5.1.0.jar)
2. [**JNA Platform**](http://repo1.maven.org/maven2/net/java/dev/jna/jna-platform/5.1.0/jna-platform-5.1.0.jar)

For the JavaFX Overlay make sure to use `Java 1.8` or download the current version of JavaFX and import in in your project from [**here**](https://github.com/javafxports/openjdk-jfx/releases)


### Usage

## Built With

* [JNA](https://github.com/java-native-access) - Java Native Access

## Acknowledgments

* [**Rake**](https://guidedhacking.com/members/rake.26782/) - for running [guidedhacking.com](https://guidedhacking.com/) and his great tutorials. Without him this repo would probably not exist.

