cubesolver
=============
What is this?
-------------
This is a program which will teach people how to solve the Rubik's cube.

How is it written?
-------------
* Java
* Swing (GUI)
* LWJGL (animation)

A LWJGL Display is created which displays the animated cube. This is contained inside a Canvas. 
Also inside this Canvas is a Swing JPanel (CubePanelTemplate) which displays the GUI.

What can it do so far?
-------------
* Turn all faces of the cube in both directions using the keyboard
* Parse text into turns
* Solve some stages of the cube
* Switch between stages in the GUi

What will it do?
-------------
* Solve all stages
* Have lots more information: cube notation etc
* Have proper lighting
* Have extra animations: e.g. a bar showing what moves have been performed, highlight some cubes