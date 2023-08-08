# Software-Engineering-DAO-Project

This is a Java project that provides an implementation of the DAO interface for managing Game entities using JDBC technology. The GameDAOImpl class implements the GameDAO interface and provides methods to create, retrieve, update, and delete Game objects from a database.

The class contains SQL statements that are used to perform various operations on the database. The create method inserts a new Game object into the database, while the retrieve method returns a Game object with a given ID. The update method modifies the properties of an existing Game object, and the delete method removes a Game object with a given ID from the database.

This project also includes methods to retrieve a count of all Game objects in the database and to retrieve a list of Game objects with a given title. The retrieveByTitle method searches for Game objects that match the given title within a specific date range.

The GameDAOImpl class uses PreparedStatement objects to execute SQL statements and retrieve data from the database. It also uses JDBC exceptions to handle errors that occur during database operations.

This project provides a useful framework for developers who want to create applications that manage Game objects using a JDBC database.

*Important: This code is owned by the University of Texas at Dallas. The project involved implementing our own code in addiiton to the code that was given to us.*
