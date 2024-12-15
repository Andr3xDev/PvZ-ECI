<a id="informe-top"></a>
<br>
<div align="center">

<h1 align="center">Poob vs Zombies</h1>

  <p align="center">
    Informe de diseño
    <br />
  </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#resume">Resume</a>
    </li>
    <li>
      <a href="#gui">GUI components</a>
    </li>
    <li>
      <a href="#board">Board</a>
    </li>
    <li>
      <a href="#zombies">Zombies</a>
      <ul>
        <li><a href="#basic">Basic</a></li>
        <li><a href="#coneHead">Cone head</a></li>
        <li><a href="#buckedHead">Bucked head</a></li>
        <li><a href="#brainstein">Brainstein</a></li>
        <li><a href="#eciZombie">ECI Zombie</a></li>
      </ul>
    </li>
    <li>
      <a href="#plants">Plants</a>
      <ul>
        <li><a href="#peaShooter">Peashooter</a></li>
        <li><a href="#wallnut">Wallnut</a></li>
        <li><a href="#sunFlower">Sunflower</a></li>
        <li><a href="#potato">Potato mine</a></li>
        <li><a href="#eciPlant">ECI Plant</a></li>
      </ul>
    </li>
    <li>
      <a href="#others">Other elements</a>
      <ul>
        <li><a href="#shovel">Shovel</a></li>
        <li><a href="#mowel">Mowel</a></li>
      </ul>
    </li>
  </ol>
</details>

<br>
<br>


## Resume
*This game is a reimagining of the classic cult game "Plants vs. Zombies." It is a project inspired by this work, utilizing concepts such as object-oriented programming to bring it to life*

<br>

## GUI components
*Descripción de los componentes gráficos.*

<br>

## Board
*The board is essentially a matrix of objects that houses instances of classes implementing the "Unit" interface.*

<br>

## Zombies
*The "Zombie" is an abstract class that implements the "Unit" interface. This class serves as the parent class for all types of zombies and contains all of their general behaviors.*

<br>

### Basic
*The basic zombie is the simplest type of zombie, as it only walks and consumes plants in its path. Being the most basic zombie, it has low stats:

Damage: 100
Health: 100
Cost: 100*

<br>

### Cone head
*The Conehead zombie wears a cone on its head, which provides it with greater resistance compared to the basic zombie.
Damage: 100
Health: 380
Cost: 150*

<br>

### Bucked head
*Este zombi lleva una cubeta en la cabeza que le proporciona una gran protección.
Damage: 100
Health: 800
Cost: 200*

<br>

### Brainstein
*This special zombie, instead of moving, remains stationary in its position and generates 25 brains every 20 seconds, providing new resources that the player can use to place other types of zombies on the board.
Damage: 0
Health: 300
Cost: 50*

<br>

### ECI Zombie
*This zombie has the ability to shoot POOmBas (projectiles similar to those of the Peashooter) every 3 seconds.
Damage: 50
Health: 200
Cost: 250*

<br>

## Plants
*The "Plant" class is an abstract class that implements the "Unit" interface, serving as the base for all plant types. It contains their behaviors and attributes.*

<br>

### Peashooter
*The Peashooter was the first attacking plant to appear in the original 2009 game. It is a plant that fires peas every 1.5 seconds, which deal damage to zombies.
Damage: 20
Health: 300
Cost: 100*

<br>

### Wallnut
*The Wall-nut was the first defensive plant unlocked in the original 2009 game. It acts as a shield for the player's other plants, blocking the zombies' advance to buy time and allow offensive plants to eliminate them.
Damage: 0
Health: 4000
Cost: 50*

<br>

### Sunflower
*The Sunflower was the second plant to appear in the original 2009 game. It is a plant capable of generating suns over time, which the player can use to place more plants on the board.
Damage: 0
Health: 300
Cost: 50*

<br>

### Potato mine
*The Potato Mine is a bomb plant that, after being placed on the board, takes 14 seconds to activate. Once activated, it explodes and eliminates the zombie it comes into contact with. If it is not yet active, zombies can eat it without it exploding.
Damage: 9999999
Health: 100
Cost: 25*

<br>

### ECI Plant
*This plant generates a larger sun in the same position where it is placed, and the sun will have a value of 50 units.
Damage: 0
Health: 150
Cost: 75*

<br>

## Other elements

### Shovel
*The Shovel allows the player to remove plants from the board. It can be used at any time and has no usage cost. Once a plant is removed from the board using the Shovel, the suns invested in it are not refunded.*

<br>

### LawnMowel
*The Lawn Mowers are activated if a zombie reaches their position, eliminating all zombies in that row (one by one) from left to right. The Lawn Mowers do not regenerate.*
