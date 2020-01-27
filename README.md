# NeverNote

This is a take home assignment by KNIME located at Austin TX. 

This is a RESTAPI service to perform CRUD operations on a given note. Every note is a private resource to user who creates it and can perform Create, Update, Read or Delete. 

## Getting Started

Clone this repository into your local or host machinne using this link https://github.com/venkateshreddypala/NeverNote.git

### Prerequisites

What things you need to install the software and how to install them

```
Java
Maven
MongoDB
Docker
```

### Installing

You can install using the docker


```
Docker compose-up -d
```

And for manual installation witout you can download Java 1.8 and greater.

```
./src mvn clean build
```

### Test
Use postman to test the rest end points

```
> HTTP Method	Available URI	Summary
- POST	/notes	To create a notebook
- GET	/notes/{notebookName}	To get a specific notebook detail
- GET	/notes	To get the details of all the notebooks
- DELETE	/notes/{notebookName}	To delete a specific notebook
```

### URI Method summary

Example Model to create a new notebook using POST method and then use GET method to see the contents:

```

{
  "name": "string"
  "description": "string",
}
```
This API also provides the following resources mapped to the respective HTTP methods which supports CRUD operations for notes for a specified Notebook.

```
- POST	/notes/{noteName}/notes	To create a note in the specified notebook
- GET	/notes/{noteName}/notes/{title}	To get a specific note detail in the specified notebook
- GET	/notes/{noteName}/notes	To get all the notes associated to the specified notebook
- GET	/notes/{noteName}/{tag}	Given a notebook, get filtered list of notes that contain the given tag string
- PUT	/notes/{noteName}/notes/{title}	To update a specific note associated with the given notebook
- DELETE	/notes/{noteName}/notes/{title}	To delete a specific note associated with the given notebook
```

Example model to create a POST method  
```
- {
 -  "title": "string",
  - "body": "string",
  - "tags": ["string", "string", "string"]
  - "createdTime": null,
  - "lastModified": null
}

```
Example model outputs the following using GET method
```
- "tags" - This is a string Array
- "createdTime" - This is set with the LocalDateTime value when a new note is created
- "lastModified" - This is set with the LocalDateTime value when a note is updated

```

## Deployment

> Base URI: http://localhost:8080/

## Built With
* [Java] (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)- Java Platform
* [Maven](https://maven.apache.org/) - Dependency Management
* [Mongo](https://www.mongodb.com/)- NoSQL Database
* [Postman](https://www.getpostman.com/)- HTTP client to test the REST calls


## Authors

* **Venkatesh Pala** - *Initial work* 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

