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
AL abrir el archivo que nos permte tener una visualizaicion grafica mejorada, logramos ver que


Sin embargo, en nuestro caso decidimos ver exactamente
que linea mandaba el error, encontrando lo siguiente:

![Codigo malo](/documents/Screenshots/PMDC.png)




<br>

## Conclusión
Pese a los errores arrojados en el test de PMD, no fue posible saber con exactitud el cómo se solucionaban, pues al estar relacionados con la GUI 
no es muy clara la solución óptima. Por otro lado, tras los pequeños ajustes al revisar el indicador integrado en IntelliJ, finalmente la gran 
mayoría de clases están exentas al marcado de errores, recomendaciones o advertencias, pues los cambios realizados eran mínimos y sencillos de 
solucionar. Finalmente, podemos decir que nuestro proyecto cumplió con todos los estándares de buenas prácticas y calidad de código, lo cual se 
ve reflejado en el buen rendimiento, extensibilidad y mantenimiento de este mismo.
