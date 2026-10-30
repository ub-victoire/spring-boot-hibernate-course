# Spring boot Learning

**Explaination of the spring boot structure after the initialization**

- `src/main/java/com/victoire/springboothibernatecourse/`
	
	Our Java source code and main application class.
- `src/main/resources/`
	
	Application configuration and resources.
- `src/test/java/` 

	Automated tests.
- `pom.xml`
	
	Maven project configuration and dependencies.
- `mvnw`

	Maven Wrapper for Linux/macOS. Lets you run Maven 
commands without installing Maven separately.


	eg: ./mvnw spring-book:run

- `mvnw.cmd`

	 The same Maven Wrapper, but for Windows Command Prompt.
- `HELP.md`
	
	A Markdown file containing project setup instructions,
 useful commands, and troubleshooting tips.


`@SpringBootApplication` marks the main Spring Boot application
class and enables key framework setup.

## How Spring actually work

### 1. First, what problem does Spring solve?

Imagine you're building a task-management application. You might
write three java classes:

- **TaskController** — receives requests from the user.

- **TaskService** — contains the application's task-related logic.

- **TaskRepository** — handles storing and retrieving tasks.

Without Spring, you might connect them manually using `new`:

```java
TaskReposotory repository = new TaskRepository();
TaskService service = new TaskService(repisitory);
TaskController controller = new TaskController(service);
``` 

That is how classes are getting connected to each other and then 
you can be able to access other classes' methods

However,as an application grows, manually creating and connecting
every object becomes harder. You must manage object creation,
dependencies, configuration, and sometimes object lifecycles
 yourself

- Spring helps manage those objects and connect them for you


### 2. Inversion of Control (IoC)

IoC stanf for **Inversion of Control**

in plain Java, you often decide when to create an object:

```Java
	TaskService service = TaskService();
```

With Spring, you can instead tell Spring which objects your 
application needs, and Spring manages their creating and availability.


So, **Application classes** (Describe what your application needs) ↔️  Spring takes responsibility for managing objects then **Spring IoC** Create, configures, and manages Spring objects

IoC is the broader principle. Dependency injection is one important way spring implements it.

###. What is a Spring bean?

A **bean** is an object that is managed by the Spring  IoC container

Not every Java object is automatically a bean.

eg: 

```code
TaskService service = new TaskService();
```
This creates an ordinary Java Object, Spring does not 
automatically manage it just because the application uses Spring Boot.

To make a class eligible for automatic detection, you can use annotations such as `@Service`

```java
import org.springframework.stereotype.Service;

@Service
public class TaskService{
	public String getMessage() {
		return "Spring is managing this service";
	}
}
```

The `@Service` annotation tells Spring that this class represents a service component.

When component scanning discovers it, Spring can register and manage an instance as a bean.

Other annotations

- `@Component`a general-purpose component
- `@Service` a service or business-logic component.
- `@Reposotory` a persistence-related component.
- `@Controller` and `@RestController` Web-layer components.

Remember **a bean is the managed object; an annotation can help Spring discover the class from which to create it.**

### 4. Dependency Injection (DI)

Dependency Injection means **an object receives the other objects it depends on instead of creating then itself.**

Our `TaskController` needs a `TaskService`. Rather than creating the service with `new TaskService()`, We can ask Spring to provide it 

It means **Spring Container** provides the TaskService bean

In spring, one of the clearest ways to do this is **Contructor injection.**

```java	
public class TaskController{

	// We're declares a field that hold the dependency
	private final TaskService taskService;	

	// This is the constructor that receives the dependency
	public TaskController(TaskService taskService){
		// this line saves the received object
		this.taskService = taskService;
	}
}
```

When Spring manages `TaskController` and finds a suitable `TaskService` bean, it can pass that bean into  the constructor.

- **Why is this useful?** Your classes depend on the services 
they need, while Spring handles connecting the managed objects. 
Constructor  injection also makes dependencies explicit and 
makes testing. 

### Revision

1. What is a Spring bean?

	It is an onbject managed by the Spring IoC container

2. What does Inversion of Control mean?

	Spring takes responsibility for managing objects that your application needs

3. What is dependency injection?

	Is a way for a class to receive the objects it depends on

4. Why did we use contructor injection?
	
	It makes the class's required dependency explicit and lets Spring provide it.

5. What does `@service` do in our example?

	Marks a service component that Spring can discover and manage

6. What should /hello return after both classes are added?

	Hello! Spring dependency  injection works


- `@springBootConfiguration` is a convenient annotation that combines three important Spring annotation
	- `@SpringBootConfiguration` Identifies the class as a source  of Spring configuration.
	- `@EnableAutoConfiguration` lets Spring Boot configure parts of the application based on the available dependencies and settings.
	- `@Component` tells Spring to discover eligible components in the relevant packages.

You don't need to write all the three annotations separately for a normal application.

By default, component scanning starts from the package containing the main application class and includes its subpackages.

### Then how does Spring know which object to inject?

back on this constructor

```java
public GreetingController(GreetingService greetingService){
	this.greetingService = greetingService;
}
```

Spring sees that `GreetingController` requires a `GreetingService`. Since `GreetingService` is registered as a bean, Spring can supply it


### process

1. **Spring discovers** `@Service` this Finds GreetingService then
2. **Spring registers a bean** this Manages the service object
3. **Spring injects the dependency** Passes the service into the controller constructor.

This is called **automatic dependency injection**

So, some developers use the `@Authowired` on constructor that inject a dependecy

- eg:

```java
@Autowired
public GreetingController(GreetingService greetingService){
	this.greetingService = greetingService;
}
``` 
**Notice:** In a class with a single constructor, Spring can perfom constructor injection without the annotation @Autowired. That is why our original example works

### How long does a bean live?

By default, Spring-managed beans have **Singleton scope.**

In this context, `singleton` means Spring normally creates one managed instance of that bean per application context and reuses it when needed.

For example, `GreetingService` doesn't need to be recreated every time somebody visits `/hello`.

- One important practical rule: because a singleton service may be shared between requests, avoid storing request-specific, changing data in ordinary mutable fields inside a service.  


### Revisiom

1. What helps Spring discover GreetingService?

	The `@Service` annotation and component scanning
2. Why does our controller work without `@Autowired`

	Spring can inject the dependency throught its single constructor
3. What does singleton scope mean here?

	Spring normally manages one instance of the bean per application context
4. Where does default component scanning begin?
	
	From the package containing the main application class and its subpackages

 
`git checkout -b lesson`
## Lesson 3 Spring Boot Configuration and project Structure

### 1. Understand Project's structure

- `src/main/java` This is our Java source code: the main application class, controllers, services, and eventually repositories and entities.
- `src/main/resource` Configuration files and other resources that  your application needs.
- `application.properties` A place to configure settings such as the server port and application name.
- `src/test/java/` Automated tests that help verify your application behaves as expected.

setting something in the application.properties 

```properties
app.greeting= Hello from application.propertise!
```

Next , update the GreetingService.java:

```java
package com.victoire.spring_boot_hibernate_course.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// marks the class as a service component.
@Service
public class GreetingService {

	@Value ("${app.greeting}")
	private String greeting;
	public String getGreeting(){
		return greeting;
	}
}

```
Here's what the new part means:

**This tells Spring: "Find the configuration property named app.greeting and put its value into the greeting variable."**
- `@Value("${app.greeting}")` tells Spring to read the setting named `app.greeting`
- Spring injects that value into the `greeting` field
- `getGreeting()` returns the configured message

### Important distinction

- Java classes define application behavior.
- Configuration files hold adjhustablle application settings.
- `target` contains generated output and should not be edited manually


### Revision 

1. Where does application.properties normally live?

	`src/main/resource`
2. What does server.port=8081 do?

	Configures the embedded web server to use port 8081
3. What does @value("${app.greeting}")do?
	
	Injects the configured app.greeting value
4. Why put the greeting in configuration

	So the message can be changed without editing the service logic
5. What is the target directory for?

	Generated build output such as compiled classes

git checkout -b lesson4
## Lesson 4  layered architecture

- 1. **Controller**: Receives HTTP requests and returns HTTP responses
- 2. **Service**: Implements application rules and coordinates the work
- 3. **Repository**: Handles saving, retrieving, updating, and deleting data
- 4. **Entity**: Represents the data we want to persist, such as a task

This is a common layered architecture. The diagram is a conceptual guide: an entity is a data model used by the persistence layer, not a service that performs work.

**Controller-> Service-> Repository-> Persistence mechanism**

The entity represents the data being handled. JPA and Hibernate help map entity objects to database records.

### 1 Understand the four layer

- 1. **Controller**: the entry point

Receives HTTP requests and returns a responses

eg: `POST /api/tasks/` receives a request to create a task.

- 2. **Service**: the business rules

Decides what the application should do

eg: Reject a task if its title is blank.

- 3. **Repository**: data access

Provides operations for saving and retrieving data.

eg: save a task or find all tasks in the database

- 4. **Entity**: the data model

Represents the information we want to store.

eg: a task has an ID, a title, and a completion status

A request normally travels down the layers, and the response travels back up:

	HTTP request

	    ⬇️

	Controller

	    ⬇️

	Service

	    ⬇️
	
	Repository
	
	    ⬇️
	
	Database
	
	    ⬇️
	
	Repository -> Service -> Controller
	
	    ⬇️
	
	HTTP Response

The entity is the data model passed through the relavant layers; it is not itself  another service or a separate step that executes code.

**Why not put everythig in the controller?**

Imagine a controller that receives requests, validates business rules, writes SQL, saves data, and constructs responses.

It might work initially, but it becomes difficult to test and maintain. With layers, each class has a clearer responsibility, and you can change database access without rewriting your HTTP endpoints.

`Post`	`/api/tasks`	Create a task
`GET`	`/api/tasks`	Retrieve all tasks
`GET`	`/api/tasks{id}`Retrieve one task

*Spring Data JPA and an in-memory H2 database* allow persistence  without installing and configuring MySQL or ProsgreSQL

One Important distinction from the previous lessons:

- Spring Boot manages application components
- Spring Data JPA provides repository functionality
- JPA defines the persistence mapping API
- Hibernate is the ORM(Object-Relational Mapping) provider that perfoms the Object-to-relationsl mapping in this setup.
- H2 is the database that stores our records while the application runs 
	
- **Spring Data JPA(Java Persistence Apis)**: A Spring framework tool that makes it easier to save, retrieve, update, and delete data in a database using Java.

- **In-memory H2 database**: A lightweight database that stores data temporarily in memory, mainly for testing and development. The data is usually lost when the application stops.

Why these dependencies

- `spring-boot-starter-data-jpa` brings in Spring Data JPA and Hibernate persistence infrastructure.
- `h2` provides the in-memory database driver

let's talk about this `application.propertise`

| Property | Meaning |
| --- | --- |
| `spring.datasource.url` | connects to an in-memory H2 database named `taskdb` |
| `spring.datasource.username` | Uses H2;s defaul `sa` user |
| `spring.datasource.password` | Empty password for this local learning database |
| `spring.jpa.hibernate.ddl-auto=update` | Lets Hibernate update the database schemaa based on mapped entities. |
| `spring.jpa.show-sql=true` | Prints SQL statements generated by Hibernate |

**Important**: An in-memory H2 database normally loses its data when the application shuts down.

```java
// Defines the package where this Task class belongs.
package com.victoire.spring_boot_hibernate_course.entity

// Imports the @Entity annotation, which marks this class as a database entity.
import jakarta.persitence.Entity;

//Imports the @GeneratedValue annotation, which generates primary key values.
import jakarta.persistence.GeneratedValue;

// Imports GenerationType, which specifies how primary key values are generated.
import jakarta.persistence.GeneratedType;

// Imports the @Id annotation, which marks a field as the primary key.
import jakarta.persistence.Id;

// @Entity tells JPA that this class represents a database table.
@Entity

public class Task {
	// @Id marks the id field as the primary key of the table.
	@Id

	// @GeneratedValue tells JPA to generate the ID automatically.
	// @GenerationType.IDENTITY means the database generates the ID,
	// typecally using an auto-increment column.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Represents the title column in the database table.
	// By default, JPA maps this field to a column name "title".
	private String title;

	// Represents the completed column in the database table.
	// A boolean stores either true or false.
	private boolean completed;

	// Non-argument constructor.
	// JPA requires a no-argument constructor to create entity objects.
	public Task(){
	
	}

	// Parameterized constructor.
	// Allows you to create a Task with a title and completion status.

	public Task(String title, boolean completed){
		this.title = title; // Assigns the given title to the object's title field.
		this.completed = completed; // Assigns the given status to the completed field.
	}

	// Getter: returns the task's title.
	public String getTitle(){
		return title;
	}

	// Setter: changes the task's title.
	public String setTitle(String title){
		this.title = title;
	}	

	// Getter: returns whether the task is completed.
	// The isComplete() naming convention is used for boolean fields.
	public boolean isCompleted(){
		return completed;
	}

	//Setter: changes the task's completion status.
	public void setCompleted(boolean completed){
		this.completed = completed;
	}
} 
```
