<a id="informe-top"></a>
<br>
<div align="center">

<h1 align="center">POOB vs Zombies</h1>

  <p align="center">
    Informe Pruebas Dinamicas
    <br />
  </p>
</div>


</br>
</br>



## RESUMEN
Este informe presenta los resultados del análisis de cobertura de código realizado con JaCoCo en un proyecto gestionado con Maven 
desde el entorno de desarrollo IntelliJ IDEA. El objetivo del análisis fue medir qué porcentaje del código fuente está cubierto por 
pruebas automatizadas y asegurar que las pruebas sean lo suficientemente robustas para detectar posibles defectos.
En nuestro caso, se nos dio la tarea de alcanzar almenos un 80% en solo covertura.

<br>

## Desarrollo
### PDM en Maven
Se configuró el plugin de JaCoCo en el archivo pom.xml de Maven para habilitar el seguimiento de cobertura de código de forma automática 
al ejecutar las pruebas. Los resultados de cobertura se generaron en un informe HTML, detallando la cobertura de métodos, líneas y ramas 
por cada clase del proyecto. Esta configuración es esencial para mantener un estándar de calidad en el código y asegurarse de que el
porcentaje de cobertura se mantenga dentro de los umbrales definidos en el equipo de desarrollo.

<br>

### Generacion del informe
Para generar el informe de cobertura de código con JaCoCo, primero se configura el plugin de JaCoCo en el archivo pom.xml de Maven,
especificando la fase de ejecución (generalmente prepare-agent o test). Luego, se ejecuta el comando mvn test en la consola, lo que 
activa la ejecución de las pruebas junto con el seguimiento de cobertura de JaCoCo. Al finalizar, se genera un informe en el formato 
especificado en la carpeta *target/site/jacoco*, que contiene detalles sobre la cobertura de líneas, métodos y ramas del 
código. Este flujo permite integrar de forma automática la verificación de cobertura en el ciclo de pruebas de Maven, contribuyendo 
a mantener un estándar de calidad en el proyecto. Muy importante tener en cuenta que esta covertura se aplica unicamente al dominio.

<br>

### Analisis del informe
Los resultados de cobertura arrojados por JaCoCo indican que se alcanzó un porcentaje de cobertura general del 94% a nivel de línea,
mientras que la cobertura de ramas fue del 87%. Estas métricas nos permiten identificar las áreas del código que no fueron ejercitadas 
durante las pruebas y, por lo tanto, requieren revisión adicional.

![Errores generales](/documents/Screenshots/covert1.png)

Tras un pequeño análisis, fuimos capaces de identificar pequeñas falencias en el código que nos evitarían probarlo como es debido. Sin embargo, al revisar más en profundidad, encontramos errores como los siguientes: fragmentos de código que nunca se podían probar, pero que sin estos eran necesarios para que el código corriera de forma correcta, pues al fin y al cabo prevenían errores inesperados en el caso que ocurrían.


![Errores generales](/documents/Screenshots/covert2.png)

Asi mismo, lo que nos baja la cobertura general, son los métodos que por razones de tiempo no alcanzaron a ser completamente funcionales (fueron los de persistencia más que nada). Por lo que las pruebas quedan con resultados como los que se pueden ver a continuación.

![Errores generales](/documents/Screenshots/covert3.png)

<br>

## Conclusión
Tras el corto análisis que pudimos realizar, podemos decir que nuestro código resulto con una gran cobertura de toda la capa de dominio. 
En gran medida, ayudo el gran diseño de clases y la calidad de código basado en estándares de calidad y patrones de diseño, razón por 
la cual la cobertura previa a los ajustes realizados ya era suficientemente alta. Finalmente, podemos decir que nuestro código en 
términos de dominio está perfectamente completo y funcional, indicando así que los fallos que se puedan presentar sean errores inesperados 
o de componentes GUI.
