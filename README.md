# Knight2D

<div text-align="center">
<img src="res\report\postergame.png" alt="">
</div>

# Table of content
1. [Introduction](#Introduction)
2. [Game](#Game)
3. [UML-diagram](#UML-diagram)
4. [Features](#Features)
5. [Challenges](#Challenges)
# 1.Introduction
<div style = "text-align: justify">
This is our game project for object-oriented programming at HCMIU in semester 2 (2022 - 2023). This is a classic game based on Platform game which is very popular among people all over the world. We have edited them in a classic style with the main character being a hero who is on the way to find and defeat the dark knight. On the way, the hero encountered many difficulties such as killing monsters and traps. We hope that you will enjoy our game project
</div>


## Team members and task allocation
| Number |   Name   | Student ID | Task | Contribute|
| :----: | :------: | :--------: |:----------------------------------------------:|:------------:|
|   1    |   Vo Hoai Bao   |   ITITIU21038   |Game Mechanic, GameLoop, Collision, Debugger, Git, Utilz   |      25      |
|   2    |   Thai Thanh Phat   |   ITITIU21274   |  GUI, Sound, UML, Maps, Utilz, GameStates     |      25      |
|   3    |   Nguyen Duc Duy    |   ITITIU21008   |Inputs, Entities, Utilz               |      25      |
|   4    |   Truong Dinh Toan   |   ITITIU21332   |        Objects, Levels, Utilz          |      25      |




## How to run project


1. **Step 1:** Clone the repository from GitHub.
```c
git clone https://github.com/ProjectGameOOPJava/Knight2D.git
```
2. **Step 2:** Open the game's source code folder in VS Code and check the file status.
```c
git status
```
3. **Step 3:** Run the game and chill.
   
# GAME
## About the game
- Language: [Java](https://www.java.com/en/)
- IDEs: [VSCode](https://code.visualstudio.com/)
- Library: [JavaSwing](https://docs.oracle.com/javase/tutorial/uiswing/)
- Game Engine: [Java2D](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html)
- Sound: [JavaSound](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html)

### Controls

|  Key  |   Action   |
|:-----:|:----------:|
| A  |  Move Left   |
| D  | Move Right  |
| W  | Jump  |
| J  | Attack |
| Hold J  | Ultra Attack |
| I  |   Ulti Slash   |
| L  |   Rush   |
  
<div style = "text-align: justify">
We have a total of 4 game stages, each with different difficulty. The player must destroy all monsters to get to the next level. In addition, each map we have placed wooden crates that heal or restore mana. In the final stage, the player must defeat the boss to complete the mission
</div>

<div text-align="center">
    <video width="860" height="360" controls>
        <source src="res\report\Knight_2D_video.mp4" type="video/mp4">
    </video>
</div>

<div text-align="center">
<img src="res\report\s1.png" alt="">
</div>
<div text-align="center">
<img src="res\report\s2.png" alt="">
</div>
<div text-align="center">
<img src="res\report\s3.png" alt="">
</div>
<div text-align="center">
<img src="res\report\s4.png" alt="">
</div>

# UML Diagram

<div>
    <h3>Audio Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-Audio.png" alt="">
        </div>
    <br />
    <h3>Entities Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-Entities.png" alt="">
        </div>
    <br />
    <h3>Game states Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-GameStates.png" alt="">
        </div>
    <br />
    <h3>Inputs Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-Inputs.png" alt="">
        </div>
    <br />
    <h3>Levels Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-levels.png" alt="">
        </div>
    <br />
    <h3>Main Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-Main.png" alt="">
        </div>
    <br />
    <h3>Objects Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-objects.png" alt="">
        </div>
    <br />
    <h3>GUI Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-UI.png" alt="">
        </div>
    <br />
    <h3>Utilz Diagram</h3>
        <div text-align="center">
            <img src="res\report\UML-Utilz.png" alt="">
        </div>
</div>

