# Variables
JAVAC := javac
JAVA := java

BIN_DIR := bin
LIB_DIR := ../_dependencias_
MAIN_CLASS := httpRequesterTEST

# Configuración del classpath
JARS := $(wildcard $(LIB_DIR)/*.jar)
CLASSPATH := $(LIB_DIR)/*:$(BIN_DIR)

# Encontrar todos los archivos .java recursivamente desde el directorio padre
# (asumiendo que el Makefile está en src/_test_/)
SOURCE_DIR := ../..
SOURCES := $(shell find $(SOURCE_DIR) -type f -name "*.java")
CLASSES := $(SOURCES:.java=.class)

# Reglas
.PHONY: all compile run clean

all: compile run

compile:
	@echo "Compilando fuentes..."
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -cp "$(CLASSPATH)" -d $(BIN_DIR) $(SOURCES)
	@echo "Compilación completada."

run:
	@echo "Ejecutando programa..."
	$(JAVA) -cp "$(CLASSPATH)" $(MAIN_CLASS)

clean:
	@echo "Limpiando archivos compilados..."
	@rm -rf $(BIN_DIR)
	@echo "Limpieza completada."# Variables
JAVAC=javac
JAVA=java


BIN_DIR=bin

LIB_DIR=../_dependencias_
MAIN_CLASS=httpRequesterTEST.java

JARS=$(wildcard $(LIB_DIR)/*.jar)
CLASSPATH=$(LIB_DIR)/*:$(BIN_DIR)
