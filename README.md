# Sauerbraten Hack ([**Video**](https://youtu.be/NnCIeuDxbm0))

This hack is an example on how to use and what you can do with the Game Hacking Library `GHTools`

It's made to be understandable and easy to adapt to other games.

## Features

#### Overlay
The hack makes use of JavaFx to overlay things like a menu over the Game.

I used this method because it's simple and can be used on any Game to display a lot of different things.

You can find some examples below.

#### Godmode
For the Campaign i made a simple Godmod Toggle with the following features:
- Unlimited Ammo
- Take No Damage
- No Recoil
- Ultra Rapid Fire
- Deal Insane Damage

![Godmode](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Godmode.gif)

This is an examaple on how to do basic memory editing like reading and writing from or to memory.

But this also demonstrates how to change opcodes of the binary in memory at runtime.

#### Aimbot
![Aimbot](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Aimbot.gif)

Demonstrates how one could implement an Aimbot.

#### ESP
![ESP](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/ESP.gif)

Demonstrates how one could implement an ESP hack using the `GHTools`.

The calculations are specific to OpenGL!

The main point of this was to demonstrate how to draw the ESP Boxes to the JavaFX Overlay.

#### Rake Mode
![Rake](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Rake.gif)

Since we are using JavaFX we can display all kinds of stuff to the screen.

To demonstrate this i used this opportunity to honor [**Rake**](https://guidedhacking.com/members/rake.26782/).

#### Multi-threaded
The whole hack is object oriented and multi-threaded so one can only make use of the pieces one needs or activate different modes at the same Time. 

![Aimbot&ESP](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/res/Aimbot%26ESP.gif)

In this example i made a seperate mode that activates the ESP and Aimbot at the same time.

## Download
If you just want to use this hack you can download a pre-compiled jar executable [**here**](https://github.com/Erarnitox/java-sauerbraten-hack/raw/master/bin/Cube2Hack.jar).

# GHTools
**Note:** the new repo for the Tools can be found [**here**](https://github.com/Erarnitox/GH_Tools).

`GHTools` is a wrapper around [**JNA**](https://github.com/java-native-access) makes it easier to use and provides some new functionality for game hacking as well.

### Installing

- To use `GHTools` you can either download it from [**here**](https://github.com/Erarnitox/java-sauerbraten-hack/raw/master/bin/GHTools.jar) and import in in your project.

- Or get the source for it [**here**](https://github.com/Erarnitox/java-sauerbraten-hack/tree/master/src/com/guidedhacking).

If you want to use the source you need to download and import these in your project first:
1. [**JNA**](http://repo1.maven.org/maven2/net/java/dev/jna/jna/5.1.0/jna-5.1.0.jar)
2. [**JNA Platform**](http://repo1.maven.org/maven2/net/java/dev/jna/jna-platform/5.1.0/jna-platform-5.1.0.jar)

For the JavaFX Overlay make sure to use `Java 1.8` or download the current version of JavaFX and import in in your project from [**here**](https://github.com/javafxports/openjdk-jfx/releases)


### Usage
#### Package Overview
All Classes can be found in the package `com.guidedhacking`. In this Overview we will have a brief look at its classes and their most often used methods.

##### GHArchitecture
Is a pure Enum type with the following Options:
- ###### Win32
- ###### Win64

##### GHInput
Unlike build in methods these will also work when the program is out of focus.

**Methods:**

- ###### boolean getKeyDown(int key)
returns `true` if the key is pressed and `false` otherwise.

- ###### void sendKeyPress(int key)
simulate a full key press and release.

- ###### void sendKeyDown(int key)
simulate a key press.

- ###### void sendKeyUp(int key)
simulate a key release.

- ###### void SetCursor(int x, int y)
set the position of the cursor to the specified position.

- ###### int[] getCursorPos()
returns an int array whit 2 elements. where the first element is the x-coordinate and the second value is the y-coordinate of the cursor.

##### GHMemory
Used to access the memory of another process.

**Methods:**

- ###### boolean openProcess(String windowName)
Open a handle to the process with this window name to be able to access its memory. Returns `true` if it was successful and `false` otherwise.

- ###### void setArchitecture(GHArchitecture architecture)
Used to set the architecture to the architecture of the game to use the correct pointer size.

- ###### long getObjectAddress(GHPointer staticMultiLevelPointer)
calculates the runtime address from the static pointer provided.

- ###### void close()
Close the handle you have opened to the game.

- ###### boolean isConnected()
Checks if the handle to the game is still open. Will return `true` if the handle is still open and `false` if its closed.

- ###### boolean readBit(long address, int position)
Used to read a single bit from memory. Return `true` if its 1 or `false` if its 0.

- ###### byte readByte(long address)
Returns the byte that can be found at the provided address in the memory.

- ###### short readShort(long address)
Returns the short that can be found at the provided address in the memory.

- ###### char readChar(long address)
Returns the char that can be found at the provided address in the memory.

- ###### int readInt(long address)
Returns the int that can be found at the provided address in the memory.

- ###### long readLong(long address)
Returns the long that can be found at the provided address in the memory.

- ###### float readFloat(long address)
Returns the float that can be found at the provided address in the memory.

- ###### double readDouble(long address)
Returns the double that can be found at the provided address in the memory.

- ###### readString(long address , int bytestoread)
Returns the String that can be found at the provided address in the memory.

- ###### byte[] readByteArray(long address, int bytesToRead)
Returns the byte[] that starts at the provided address with the provided length.

- ###### boolean writeBit(boolean data, long address, int position)
Write a single bit to memory (`true` for 1 and `false` for 0) to the specified position in the byte that can be found at the specified address.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeByte(byte data, long address)
Write a single byte to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeShort(short data, long address)
Write a short to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeChar(char data, long address)
Write a single char to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeInt(int data, long address)
Write an int to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeLong(long data, long address)
Write a long to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeFloat(float data, long address)
Write a float to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeDouble(double data, long address)
Write a double to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean writeString(long address,String string)
Write a String to the specified address in memory.
Will `return` true if successful and `false` otherwise.

- ###### boolean write(byte[] data, long address)
Write a byte[] to memory starting at the provided address.
Will `return` true if successful and `false` otherwise.

this class also provides some methods for working with objects in memory. If you are interested in them check the source of this class [here](https://github.com/Erarnitox/java-sauerbraten-hack/blob/master/src/com/guidedhacking/GHMemory.java). Please note that these methods are not tested!

##### GHPointer
Used to hold information about the static pointer and the offsets of a value.

**Constructor:**

- ###### GHPointer(long staticPointer, int ... offsets)

**Methods:**

- ###### long getStaticPointer()
- ###### int[] getOffsets()

##### GHTools

**Methods:**

- ###### boolean sleep(int time)
Sleep method with exception handeling.

- ###### int getGamePID()
Returns the process ID of the currently opened process.

- ###### boolean isGameVisible()
Return `true` if the game window is visible and `false` otherwise.

- ###### int getGameHeight()
Returns the height of the game window in pixels.

- ###### int getGameWidth()
Returns the width of the game window in pixels.

- ###### int getGameXPos()
Returns the x-position of the upper left corner of the game window on the sreeen.

- ###### int getGameYPos()
Returns the y-position of the upper left corner of the game window on the sreeen.

### Getting started

Once you have downloaded and imported [**GHTools**](https://github.com/Erarnitox/java-sauerbraten-hack/raw/master/bin/GHTools.jar) into your project you can get started coding your first hack for a game.

here is a very simple example on how to use `GHTools`

```
//import everything from the GHTools:
import com.guidedhacking.*; 

public class Example {
	
	//create a new pointer with the static address and offsets:
	private static GHPointer healthPtr  = new GHPointer(0x2DEAD,0x13); 
	
	public static void main(String[] args){
		
		//try to open a handle to the game process:
		if(GHMemory.openProcess("Game Window Title")) {
			
			//select the architecture of the game:
			GHMemory.setArchitecture(GHArchitecture.Win32); 
			
			//calculate the runtime address of the health value from the pointer:
			long healthAddy = GHMemory.getObjectAddress(healthPtr); 
			
			//read the health value from the games memory:
			int healthValue = GHMemory.readInt(healthAddy); 
			
			//increase the health value by 1:
			healthValue++; 
			
			//write the new health value back to memory:
			GHMemory.writeInt(healthValue,healthAddy); 
			
		}else{ //if creating a handle to the game failed
			System.out.println("Can not open Game!");
		}
	}
}
```

for a more in depth example please have a look at the example hack i have provided.

## Built With

* [JNA](https://github.com/java-native-access) - Java Native Access

## Acknowledgments

* [**Rake**](https://guidedhacking.com/members/rake.26782/) - for running [guidedhacking.com](https://guidedhacking.com/) and his great tutorials. Without him this repo would probably not exist.
