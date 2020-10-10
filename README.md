# Notix
It is a web application that is designed to help organize student work. It allows you to create notes, and save the dates of events such as exams or seminars. 

## Table of contents
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [ToDo](#ToDO)
* [Inspirations](#Inspirations)

## Technologies
### Backend
* Java 8
* Spring
* Hibernate
* JUnit 5
* Project Lombok
* Maven

### Frontend
* React.js
* React Router
* Sass
* Material-UI

## Setup
```bash
cd ./Backend
mvnw clean install
java -jar notix/target/app-1.0-SNAPSHOT-exec.jar
```

## Features
* Clean, modern UI
* RWD
* Creating user accounts
* JWT-based Authentication
* Creating rich notes using wysiwyg text editor


## ToDo
- [x] Align buttons and inputs color
- [x] Cache subject and event data
- [x] Change stub events to real ones, made by user
- [x] Add event calendar
- [x] Add possibility to change user data

## Inspirations
https://baas.amsterdam/

