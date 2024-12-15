<a id="informe-top"></a>
<br>
<div align="center">

<h1 align="center">POOB vs Zombies</h1>

  <p align="center">
    Informe PDM
    <br />
  </p>
</div>


</br>
</br>



## RESUMEN
Este informe detalla los resultados de un análisis de código estático llevado a cabo utilizando la herramienta PMD y apoyado por el integrado en el 
entorno de desarrollo IntelliJ IDEA. El objetivo del análisis fue detectar problemas comunes en el código fuente, mejorar la calidad y 
asegurar el cumplimiento con las mejores prácticas de programación. Lo cual consideramos exitosos debido a varias razones.

<br>

## Desarrollo
### PDM en Maven
Se optó por usar **PDM** de Maven en lugar de un plugin específico debido a su capacidad para realizar análisis estático del 
código de manera más eficiente y completa. PMD ayuda a identificar problemas de calidad del código, como malas prácticas de programación, 
violaciones de convenciones y errores potenciales, lo que permite mejorar la mantenibilidad y la legibilidad del código. Además, 
su integración con herramientas como Maven facilita la ejecución automatizada y la generación de informes detallados sobre el estado del código.
En nuestro caso, únicamente queremos que aparezcan los errores críticos o que tienen una falencia en los ítems mencionados.

<br>

### Generacion del informe
Para comenzar, suponemos que primero se configura el plugin de PMD en el archivo `pom.xml` de Maven, especificando la fase de ejecución 
y el formato del informe. Luego, se ejecuta el comando `mvn pmd:pmd` en la consola, lo que activa el análisis estático del código 
durante la fase de construcción configurada. El informe se genera en el formato especificado, ya sea en la consola o en 
archivos dentro de la carpeta `target/pmd`, dependiendo de la configuración del plugin. Este proceso permite integrar el 
análisis de calidad del código de manera automática en el flujo de trabajo de Maven.

<br>

### Analisis del informe
Al abrir el archivo que nos permite tener una visualización gráfica mejorada, logramos ver que nos arrojó múltiples instancias del mismo fallo crítico  en muchas clases. Este se identificaba con el error `ConstructorCallsOverridiableMethod`. Este  se dispara cuando detecta una muy mala práctica de programación y diseño de visibilidad de clases y elementos.

![Codigo malo](/documents/Screenshots/pmd1.png)
![Codigo malo](/documents/Screenshots/pmd2.png)

Si investigamos el problema en la wiki de PMD con la implementación de Maven, podemos ver que este ocurre al dejar la opción de que en algún momento, si se llega a heredar la clase, pueda sobrecargar o sobreescribir métodos esenciales para la representación visual. De este modo,  se considera mala práctica al arriesgar la seguridad y la estabilidad del producto final.

![Codigo malo](/documents/Screenshots/pmd3.png)

Para solucionar este único problema, simplemente se restringieron las clases con el atributo `final`. De este modo, aseguramos que al no poderse heredar no hay posibilidades de que algo pueda realizar alguna acción de recarga o sobre escritura de los métodos existentes de la GUI. Tras ese cambio en todas las clases marcadas, se volvió a generar el informe, obteniendo resultados más que satisfactorios, pues no hubo errores de ningún tipo.

![Codigo malo](/documents/Screenshots/pmd4.png)


Además, si realizamos el análisis con base en la herramienta de análisis estático de IntelliJ, logramos ver que tampoco tenemos errores más allá de las recomendaciones que nos otorga en sentido de diccionario y mejorar aspectos como el logg.

![Codigo malo](/documents/Screenshots/pmd5.png)



<br>

## Conclusión
Pese a los errores arrojados en el test de PMD, no fue posible saber con exactitud el cómo se solucionaban, pues al estar relacionados con la GUI 
no es muy clara la solución óptima. Por otro lado, tras los pequeños ajustes al revisar el indicador integrado en IntelliJ, finalmente la gran 
mayoría de clases están exentas al marcado de errores, recomendaciones o advertencias, pues los cambios realizados eran mínimos y sencillos de 
solucionar. Finalmente, podemos decir que nuestro proyecto cumplió con todos los estándares de buenas prácticas y calidad de código, lo cual se 
ve reflejado en el buen rendimiento, extensibilidad y mantenimiento de este mismo.
