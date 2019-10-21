# Minipascal

Requisitos: JFlex, Java CUP, Guava, JSON Simple y preferiblemente IntelliJ IDEA:
1. Clonar el repositorio y entrar en la carpeta
```
git clone https://github.com/canozo/noheliascript.git
cd noheliascript
```

2. Correr el JFlex
```
jflex src/minipascal/lexer/minipascal.flex
```

3. Correr el CUP
```
cd src/minipascal/cup_parser
java -classpath C:\CUP\java-cup-11b.jar. java_cup.Main < minipascal.cup
```

4. Agregar el runtime de Java CUP, JSON Simple y Guava a IntelliJ:
```
Click Derecho en el Proyecto abajo de 'Project'
Module Settings
Dependencies
Add
JARs or Directories
Seleccionar 'java-cup-11b-runtime.jar'
Seleccionar 'guava-28.1-jre.jar'
Seleccionar 'json-simple-1.1.1.jar'
```
