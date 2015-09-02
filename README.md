# Simulador de la Vuelta Ciclista

Este es mi primer proyecto empleando Programación Orientada a Objetos en Java.

Descripción
-----------
Este proyecto es un simulador de carreras de ciclismo.

En un primer momento se permite al usuario elegir el número de participantes de la carrera, y con esto ya se muestra la consola, la interfaz gráfica y los primeros mensajes de ayuda.

Después se permite al usuario la modificación de elementos de la carrera (inclinación de las pendientes, posición de las curvas y velocidad a la que se deben tomar, o velocidad del viento) mediante comandos por consola, o con la lectura "sin interupción" de un fichero externo. Esto establecerá las condiciones de la carrera para todos los participantes. Según se van modificando los distintos parámetros, estos se ven reflejados en un gráfico en el que se puede ver la evolución de la carrera.

Además podemos ver todos los parametros de cada ciclista/bicicleta: fuerza empleada, cadencia de pedalada, relación de transmisión elegida, pendiente sobre la que se encuentra, etc. Estos parámetros, junto a las características de la bicicleta, serán los que determinen la velocidad que se adquiere en cada momento. Estos parámetros pueden ser modificados a su vez mediante comandos por consola o mediante los botones visibles en la interfaz gráfica.

El desarrollo de la carrera y el avance de cada ciclista se puede ver en el gráfico mencionado anteriormente.

¡El ciclista que acabe primero (y no se salga de una curva o se quede sin fuerzas) será el ganador!

Objetivos
---------

- El objetivo principal es conocer los principios de la POO y avanzar en el aprendizaje de los conceptos específicos de este paradigma (clases, objetos, clases abstractas, herencia, interfaces, polimorfismo, abstracción... etc).
- Otro objetivo importante es aprender y aplicar algunos patrones de diseño básicos como pueden ser MVC, Singleton, Command, o Factory.
- Como objetivo transversal se encuentra el de afianzar los conceptos de los distintos factores de calidad del software, y llevarlos a la práctica en un proyecto de cierta complejidad.


Discusión
--------

La propuesta de solución para el problema planteado (un simulador de carreras de ciclismo) es posiblemente una
aceptable implementación de la misma. Esto se debe principalmente a varios factores:

1. La aplicación tiene una aceptable documentación, no sólo la que nos ofrece
JavaDoc, si no también un código bien autodocumentado. Hecho que favorece la
facilidad de uso y escalabilidad. Además cada uno de los métodos y clases está
comentada con comentarios de JavaDoc, por lo que la generación de la documentación
es prácticamente automática.

2. La aplicación posee un sistema de pruebas automático que, si bien es
imposible que contenga todas las pruebas posibles, posee las principales pruebas que
se le pueden hacer a las clases respectivas. Para ello se ha empleado JUnit
(integrado con Eclipse) que nos permite agrupar dichas pruebas y además nos ahorra
bastante tiempo gracias a la realización más simple de las pruebas (que nos muestra los
posibles errores y fallos del código.

3. La organización de la clase Principal y el resto de las clases tiene posibilidad de
ampliarse, debido a su limitado acoplamiento.

4. La organización e indentación del código hace que sea claro y legible, y esto
ayuda al fácil mantenimiento del software, además de las ventajas del punto 1.
Otro punto que también ayuda a la legibilidad y fácil mantenimiento es la
utilización de variables autoexplicativas, de las cuales no es necesario aportar
información para saber lo que son y para qué se utilizan.

5. La implementación de la clase RelojSimple es una gran solución, debido a su gran escalabilidad y mantenimiento, por el buen empleo de la herencia entre las clases que la conforman.

6. La aplicación posee una gran usabilidad. Característica imprescindible para que
el usuario pueda hacer un buen uso de la misma.

7. La aplicación sigue los principios físicos del MRUA en una bicicleta, y se han estudiado diversos elementos que influyen en la misma (radio de las ruedas, marchas, platos, piñones, y la relación de transmisión, cadencia de pedalada, peso de la bicicleta, fuerza aplicada por el ciclista, y factores externos como la aceleración del viento, o inclinación de la carretera...).

-------------------------------------------------------
Lin M. Dotor © 2013
