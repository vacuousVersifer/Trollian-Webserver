# syntax=docker/dockerfile:1

FROM ubuntu:22.04
LABEL authors="ollie"

RUN apt update
RUN apt install openjdk-17-jdk -y

COPY build.gradle build.gradle
COPY gradlew gradlew
COPY gradle gradle
COPY src/ src/

RUN ./gradlew build -x processTestAot