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
*Poob vs Zombies es una versión mejorada y adaptada al paradigma de la programación orientada a objetos del clásico juego Plants vs Zombies. Este proyecto utiliza un tablero principal (board) que sirve como base para almacenar diferentes tipos de unidades. En este tablero, se implementan métodos específicos que permiten ejecutar las acciones correspondientes de cada una de las unidades, garantizando una interacción dinámica y eficiente entre los elementos del juego.
<br>

## GUI components
*Descripción de los componentes gráficos.*

<br>

## Board
*El tablero, o board, es esencialmente una matriz de objetos del tipo Unit, el cual está definido como una interfaz. Esto permite que múltiples clases que representan diferentes unidades del juego implementen su propio comportamiento y características específicas, manteniendo la flexibilidad y escalabilidad del sistema.*

<br>

## Zombies
*El zombie es una clase abstracta que implementa la interfaz Unit. Esta clase define el hilo principal encargado de gestionar las acciones básicas de los zombies, como caminar y atacar. Además, incluye los atributos necesarios para modelar el comportamiento y las características fundamentales de cada tipo de zombie.*

<br>

### Basic
*El Basic Zombie es la clase más elemental de los zombies. Es una clase sencilla que únicamente cuenta con un constructor básico para instanciar al zombie en una posición específica del tablero. Su funcionalidad se limita a ejecutar el hilo de ataque y movimiento heredado de su clase padre, sin agregar comportamientos adicionales.

Damage: 100
Health: 100
Cost: 100*

<br>

### Cone head
*El Conehead Zombie es una variante del zombie que, al igual que su contraparte básica, no añade funcionalidades adicionales. Sigue ejecutando los hilos de ataque y movimiento definidos en su clase padre. Sin embargo, la principal diferencia radica en que, al tener un cono en la cabeza, cuenta con atributos de vida y daño superiores.
Damage: 100
Health: 380
Cost: 150*

<br>

### Bucked head
*El Buckethead Zombie, siendo el hermano mayor de los tres, mantiene una funcionalidad idéntica a las otras variantes. Sin embargo, gracias a su cubo en la cabeza, sus atributos de vida son significativamente mayores, lo que también incrementa su costo en el juego..
Damage: 100
Health: 800
Cost: 200*

<br>

### Brainstein
*El Brainstein Zombie es la primera variante que introduce una mecánica diferente. A diferencia de sus predecesores, su hilo principal de movimiento se ve interrumpido al llegar a la casilla número 8 del tablero. Además, no cuenta con un método de ataque convencional. En lugar de ello, al alcanzar la casilla mencionada, su hilo ejecuta un método de recolección de cerebros, que se suman al atributo correspondiente en el juego principal. Esto lo convierte en un zombie estratégico con un impacto indirecto en el desarrollo del juego.
Damage: 0
Health: 300
Cost: 50*

<br>

### ECI Zombie
*El ECIZombie introduce una nueva mecánica al poder disparar a las plantas a distancia. Esto se logra mediante un método que ejecuta un hilo encargado de crear, a intervalos regulares, un proyectil llamado POOmBaa. Este proyectil, a su vez, contiene un hilo interno que gestiona su movimiento y la detección de colisiones. Al impactar contra una planta, POOmBaa ejecuta un método que inflige daño.

El hilo de movimiento del ECIZombie permanece igual que el de su clase padre, lo que le permite desplazarse normalmente mientras dispara a distancia.
Damage: 50
Health: 200
Cost: 250*

<br>

## Plants
*La clase Plant es abstracta y sirve como base para la creación de todos los tipos de plantas en el juego. Esta clase contiene los atributos necesarios para que las plantas puedan funcionar correctamente dentro del juego, como su posición en el tablero, su salud, su costo, y otros parámetros específicos que serán utilizados por las clases derivadas.*

<br>

### Peashooter
*La clase Peashooter es una planta esencial para el juego, ya que tiene la capacidad de disparar a los zombies a distancia. Esta funcionalidad se logra mediante un hilo que, de manera periódica, crea proyectiles de la clase Pea. Estos proyectiles, a su vez, contienen un hilo interno que gestiona su movimiento a lo largo del tablero, además de detectar colisiones con los zombies. Al chocar con un zombie, el proyectil inflige daño a este.
Damage: 20
Health: 300
Cost: 100*

<br>

### Wallnut
*La planta WallNut es una planta defensiva cuya única función es resistir las oleadas de zombies. Debido a esto, no cuenta con métodos internos ni funcionales adicionales, más allá de tener altos puntos de vida, lo que le permite mantenerse en pie durante un largo período frente a los ataques de los zombies.
Damage: 0
Health: 4000
Cost: 50*

<br>

### Sunflower
*La Sunflower es una planta generadora de soles, diseñada específicamente para producir recursos durante el juego. Implementa la interfaz Runnable, lo que le permite tener un hilo principal. Este hilo se encarga de generar un sol cada cierto tiempo, el cual se suma al tablero. Cuando la Sunflower muere, ejecuta un método que detiene su hilo, dejando de generar soles.
Damage: 0
Health: 300
Cost: 50*

<br>

### Potato mine
*La PotatoMine es una planta que, después de 14 segundos, se activa y explota, causando un gran daño a los zombies cercanos. Para implementar esta funcionalidad, se utilizó un scheduler que gestiona la cuenta regresiva de 14 segundos. Además, esta planta posee un método de activación que, una vez transcurrido el tiempo, cambia un atributo que la pone en estado activo. Esto permite que, al recibir daño, ejecute el método explode, el cual inflige 99999 puntos de daño a los zombies en colisión.
Damage: 9999999
Health: 100
Cost: 25*

<br>

### ECI Plant
*El ECIPlant es una planta generadora de soles, diseñada para producir soles de mayor valor en comparación con el Sunflower. Al igual que esta última, implementa la interfaz Runnable para tener un hilo principal, el cual le permite generar un sol más grande a intervalos regulares. Este sol se suma al tablero, aumentando el atributo de soles disponibles. Al morir, la ECIPlant ejecuta un método que detiene su hilo, dejando de generar soles. La principal diferencia con el Sunflower es que el ECIPlant genera soles de mayor valor, lo que contribuye con una cantidad superior al atributo de soles.
Damage: 0
Health: 150
Cost: 75*

<br>

## Other elements

### Shovel
*La Shovel se encarga de eliminar una planta del tablero. Al ejecutar su función en la interfaz gráfica (GUI), se selecciona automáticamente, lo que permite que, internamente, se ejecute el método deletePlant sobre la planta elegida. Este método elimina la planta del tablero, retirándola de la partida.*

<br>

### LawnMowel
*Las LawnMowers fueron creadas para ejecutarse en una matriz diferente, ya que no implementan la interfaz de Unit. Estas podadoras, al detectar un zombie en colisión, avanzan a través de su propia matriz. Sin embargo, van validando poco a poco que los zombies que se encuentran frente a ellas sean eliminados. Esto se logra mediante un hilo interno que constantemente elimina zombies al frente de la podadora, al mismo tiempo que le permite moverse a lo largo del tablero.*
