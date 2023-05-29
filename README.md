# **_KNIGHT 2D_**
<p>
<div text-align="center">
<img src="res\report\postergame.png" alt="">
</div>
</p>

# **_Table of content_**
1. [Background](#Background)
   - [Introduction](#introduction)
   - [Group members & Task allocation](#Group_members&Task_allocation)
   - [How to run project](#how-to-run-project)
2. [Motivation to create](#Motivation_to_create)
3. [Game](#Game)
   - [About the game](#About-the-game)
   - [Content](#Content)
   - [How to play](#how-to-play)
4. [Design & New Features](#Design-&-New-Features)
5. [Challenging](#Challenging)
6. [4 properties of OOP](#Related-to-the-4-properties-of-OOP)
7. [UML Diagram](#UML-Diagram)
# **1.Background**
## a. _Introduction._
<div style = "text-align: justify"><p>
This is our game project for object-oriented programming at HCMIU in semester 2 (2022 - 2023). This is a classic game based on Platform game which is very popular among people all over the world. We have edited them in a classic style with the main character being a hero who is on the way to find and defeat the dark knight. On the way, the hero encountered many difficulties such as killing monsters and traps. We hope that you will enjoy our game project</p>
</div>


## b. _Team members and task allocation._
| Number |   Name   | Student ID | Task | Contribute|
| :----: | :------: | :--------: |:----------------------------------------------:|:------------:|
|   1    |   Vo Hoai Bao   |   ITITIU21038   |Game Mechanic, GameLoop, Collision, Debugger, Git, Utilz   |      25      |
|   2    |   Thai Thanh Phat   |   ITITIU21274   |  GUI, Sound, UML, Maps, Utilz, GameStates     |      25      |
|   3    |   Nguyen Duc Duy    |   ITITIU21008   |Inputs, Entities, Utilz               |      25      |
|   4    |   Truong Dinh Toan   |   ITITIU21332   |        Objects, Levels, Utilz          |      25      |




## c. _How to run project._

1. **Step 1:** Clone the repository from GitHub.
```c
git clone https://github.com/ProjectGameOOPJava/Knight2D.git
```
2. **Step 2:** Open the game's source code folder in VS Code and check the file status.
```c
git status
```
3. **Step 3:** Run the game and chill.
   
# **2. Motivation to create game**
<div style = "text-align: justify">
<p>We aimed to create a challenging 2D game reminiscent of the classics. Our goal was to
test players' skills and determination, providing a rewarding sense of accomplishment.
The game mirrors Dark Souls' difficulty and unforgiving nature, with a dynamic combat
system and a dark fantasy world. Every decision carries weight, making it demanding yet
fair, ultimately rewarding perseverance.</p>
</div>

# **3. GAME**
## a. _About the game._
- Language: [Java](https://www.java.com/en/)
- IDEs: [VSCode](https://code.visualstudio.com/)
- Library: [JavaSwing](https://docs.oracle.com/javase/tutorial/uiswing/)
- Game Engine: [Java2D](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html)
- Sound: [JavaSound](https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/Clip.html)

## b. _Content._
<div style = "text-align: justify">
<p>Embark on a thrilling quest in Knight 2D, aiming to become a legendary knight.
Explore diverse levels (4 levels) with pixel art and retro music. Face fierce monsters
and an evil dark knight seeking to overthrow the king. Utilize your skills and bravery
to overcome challenges and prove yourself as a worthy knight. Knight 2D is based on
classic platform games.</p>
</div>

<div text-align="center"><p>Level 1</p>
<img src="res\report\s1.png" alt="">
</div>
<div text-align="center"><p>Level 2</p>
<img src="res\report\s2.png" alt="">
</div>
<div text-align="center"><p>Level 3</p>
<img src="res\report\s3.png" alt="">
</div>
<div text-align="center"><p>Level 4</p>
<img src="res\report\s4.png" alt="">
</div>

## c. _How to play._

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

- Use A, D, W to move the knight.
- Use J and I to attack the enemies.
- Use L to dodge enemy attacks and counter them.
- Watch your mana bar (yellow) below your health bar (red) as it decreases when you use I, L or jump.
- Break crates to find healing potions or mana.
- Fight the boss with I without losing health.
- Keep the boss chasing you to prevent them from healing
</div>

# **4. Design & New Features**

<div style = "text-align: justify">
<p>We utilize BufferedImage, an object that simulates image files and enables pixel color manipulation. Each pixel represents a specific game component (tile, entity, or object) with red, green, and blue channels indicating different elements. The Level class loads level data from BufferedImage, executing methods like loadLevelData, loadEntities, and loadObjects. By examining pixel colors, these methods create corresponding game elements. We obtain game resources, including images, sounds, and music, from Google, optimizing development time and effort.</p>
<p> Exciting updates include keyboard-based character control, a slashing move for attacking through the map until it collides with tiles, tutorial at the begin level, challenging bosses at the final level, and an energy system for jumping and potion-based recharging.</p>
<p>In our game, we utilized the State pattern to effectively manage various modes and transitions. By creating distinct classes for each state and employing a Gamestate class to delegate responsibilities to the current state object, we achieved a design that promotes flexibility, maintainability, and readability in our code. Moreover, the Constants class conveniently stores all game constants in a single location, adhering to the Single Responsibility pattern. This design principle promotes code readability and maintainability by ensuring each class has a specific purpose. Additionally, the Constants class organizes constants by their respective types (e.g., Projectiles, UI).</p>
</div>

# **5. Challenging**
<div style = "text-align: justify"><p>
During game development, we faced challenges in importing assets, managing the codebase with Git, fixing bugs which hard to find out, and introducing unique features. Working in pairs posed communication and participation challenges.</p>
</div>

# **6. Related to the 4 properties of OOP**
<p class="indent">
<p>Through our coursework, we have acquired a solid understanding of the four fundamental principles of object-oriented programming (OOP): encapsulation, abstraction, inheritance, and polymorphism. Drawing upon these principles, we diligently applied them in the design and development of our game project.</p>

## a. _Encapsulation._
<div style = "text-align: justify">
<p>We have implemented encapsulation in our game project using classes like Player, Enemy, and Game. Each class encapsulates specific attributes and methods, hiding them from other classes. For example, the Player class has attributes like hp, energy, position, walkSpeed, and methods like checkAttack and updateHealthBar. Getters and setters are used for secure access and modification. This promotes organization and control in managing the game's elements.</p>
</div>

## b. _Abstraction._
<div style = "text-align: justify">
<p>To capture shared characteristics among game objects, we used abstraction via abstract classes and interfaces. The StateMethod interface ensured consistent behavior across different game states. Abstract classes like Enemy encapsulated common functionality for all enemies, promoting code reuse. Abstraction improved modularity and maintainability, facilitating efficient representation of shared features and streamlined development.</p>
</div>

## c. _Inheritance._
<div style = "text-align: justify">
<p>We utilized inheritance to create subclasses, which allowed for code reuse and established a hierarchical relationship. For enemy types like Bee, Boar, and Snail, we customized behaviors by overriding specific methods. Similarly, we extended object functionality with subclasses like Potion, ObjectContainer, and Spike from GameObject class. Inheritance streamlined development and maintenance, reducing code duplication and promoting modular code. It facilitated the creation of specialized subclasses that built upon parent class attributes and methods, enhancing flexibility and customization.</p>
</div>

## d. _Polymorphism._
<div style = "text-align: justify">
<p>We also incorporated polymorphism to enhance code flexibility and generality. The EnemyManager class contains the checkEnemyHit method, which examines collisions between projectiles and enemies. Depending on the specific collision and enemy type, the method executes distinct actions. For instance, when a projectile collides with a boar, it triggers the boar's damage method with 100 damage. Similarly, if an active attackBox collides with a boar, it invokes the boar's damage method with 20 damage.</p>
</div>
</p>

<div style = "text-align: justify">
<p>Using object-oriented programming (OOP) principles in our game project brings numerous advantages, such as better code organization, improved readability, and enhanced reusability. These benefits greatly contribute to efficient development and the inclusion of diverse interactive elements, resulting in an enriched gameplay experience. In summary, the implementation of OOP principles boosts functionality and player engagement, providing a more enjoyable gaming experience.</p>
</div>

# **7. UML Diagram**

<div>
    <h3> a. Audio Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-Audio.png" alt="">
        </div>
    <br />
    <h3>b. Entities Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-Entities.png" alt="">
        </div>
    <br />
    <h3>c. Game states Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-GameStates.png" alt="">
        </div>
    <br />
    <h3>d. Inputs Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-Inputs.png" alt="">
        </div>
    <br />
    <h3>e. Levels Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-levels.png" alt="">
        </div>
    <br />
    <h3>f. Main Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-Main.png" alt="">
        </div>
    <br />
    <h3>g. Objects Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-objects.png" alt="">
        </div>
    <br />
    <h3>h. GUI Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-UI.png" alt="">
        </div>
    <br />
    <h3>i. Utilz Diagram.</h3>
        <div text-align="center">
            <img src="res\report\UML-Utilz.png" alt="">
        </div>
</div>

