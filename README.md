# NeverNote
This is a REST API Developed for performing CRUD operations on a Note.

Base URI: http://localhost:8080/

This API provides the following resources mapped to the respective HTTP methods which supports creation, deletion of Notebooks and to retrieve a specific Notebook or all Notebooks.

HTTP Method	Available URI	Summary
POST	/notes	To create a notebook
GET	/notes/{notebookName}	To get a specific notebook detail
GET	/notes	To get the details of all the notebooks
DELETE	/notes/{notebookName}	To delete a specific notebook
Example Model to create a new notebook using POST method and then use GET method to see the contents:

{
  "name": "string"
  "description": "string",
}
This API also provides the following resources mapped to the respective HTTP methods which supports CRUD operations for notes for a specified Notebook.

HTTP Method	Available URI	Summary
POST	/notes/{noteName}/notes	To create a note in the specified notebook
GET	/notes/{noteName}/notes/{title}	To get a specific note detail in the specified notebook
GET	/notes/{noteName}/notes	To get all the notes associated to the specified notebook
GET	/notes/{noteName}/{tag}	Given a notebook, get filtered list of notes that contain the given tag string
PUT	/notes/{noteName}/notes/{title}	To update a specific note associated with the given notebook
DELETE	/notes/{noteName}/notes/{title}	To delete a specific note associated with the given notebook
Example Model to create a new note using POST method for a specific Notebook

{
  "title": "string",
  "body": "string",
  "tags": ["string", "string", "string"]
  "createdTime": null,
  "lastModified": null
}
Here is the example model that can be seen when GET method is used:

"tags" - This is a string Array
"createdTime" - This is set with the LocalDateTime value when a new note is created
"lastModified" - This is set with the LocalDateTime value when a note is updated
{
  "title": "string",
  "body": "string",
  "tags": [ "string", "string", "string" ], 
  "createdTime": "LocalDateTime",
  "lastModified": null
}
Tools, technologies used for developing this API:

Java 1.8.0_171
Maven
Mongo
Spring framework 2.0.3
JUnit for unit tests
Spring Tool Suite – 3.9.4
Postman HTTP client to test the REST calls
