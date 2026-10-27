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


