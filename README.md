Spring Boot
======

### Tworzenie nowego projektu

Nowy projekt Spring Boot możemy w bardzo prosty sposób utworzyć za pomocą jednego z dwóch narzędzi:
* utworzenie nowego projektu w zakładce `File -> New -> Project -> Spring Initializr` - *tylko w IntelliJ IDEA Ultimate*
* utworzenie nowego projektu do pobrania w formacie `*.zip` za pośrednictwem strony https://start.spring.io/

### Metoda main

Aby uruchomić aplikację Spring Boot, metoda `main` i klasa, w której się ona znajduje powinna wyglądać następująco:

```java
@SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}
```


### Tworzenie beanów

Bean to obiekt zarządzany przez kontekst Springa. Aplikację w Springu Bootcie można sobie wyobrazić jako jeden kontener,
w którym umieszczone są nasze beany i które możemy "wstrzykiwać" (czyli wykorzystywać nasze obiekty) w innych komponentach
również zawartych w kontenerze. Beanów nie musimy tworzyć samodzielnie, tj. poprzez wywołanie konstruktora - za tę czynność
odpowiada Spring Boot

Definicję beanów umieszczamy w klasie oznaczonej adnotacją `@Configuration`.

Przykład:
```java
@Configuration
public class ShapeFormatterConfiguration {

    @Bean
    public ShapeFormatter triangleFormatter() {
        return new TriangleFormatter();
    }

}
```

### Wstrzykiwanie beanów

Aby móć wykorzystać nasz bean w innym komponencie zawartym w kontekście Springa, korzystamy z adnotacji `@Autowired`.

Przykład:
```java
@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityRepository entityRepository;    

    public List<Entity> findAll() {
        return entityRepository.findAll();
    }

}
```


### Nazywanie beanów

Może zdarzyć się sytuacja, w której aplikacja Springa nie będzie wiedziała, który bean ma wstrzyknąć do naszego komponentu.
W takiej sytuacji musimy nazwać nasze beany oraz wskazać poprzez nazwę, z którego beana chcemy skorzystać. Służy do tego
adnotacja `@Qualifier`.

Przykład:

```java
@Configuration
public class ShapeFormatterConfiguration {

    @Bean
    @Qualifier("triangleFormatter")
    public ShapeFormatter triangleFormatter() {
        return new TriangleFormatter();
    }

    @Bean
    @Qualifier("squareFormatter")
    public ShapeFormatter squareFormatter() {
        return new SquareFormatter();
    }

}
```

```java
@Service
public class ShapeService {

    @Autowired
    @Qualifier("triangleFormatter")
    private ShapeFormatter shapeFormatter;
    
    public void format(Shape shape) {
        shapeFormatter.format(shape);
    }
    
}
```

### Inne przydatne adnotacje

* `@Component` - najbardziej ogólna adnotacja w Spring Boot. Klasa oznaczona taką adnotacją będzie znaleziona
i umieszczona w kontekście (kontenerze) aplikacji
* `@Repository` - adnotacja dedykowana dla klas, których zadaniem jest przechowywanie danych
* `@Service` - adnotacja dedykowana dla klas, które sa odpowiedzialne za dostarczanie usłu
* `@Controller` - adnotacja dedykowana dla klas, które reprezentują warstwę prezentacji lub API aplikacji
* `@Configuration` - adnotacja dedykowana dla klas, które zawierają w sobie definicję beanów


### Spring Boot jako REST API

Aplikacja w Spring Boot może pełnić rolę REST API. Aby stworzyć aplikację przechwytującą dane HTTP requesty, należy
dodać do pliku `pom.xml` dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Następnie należy utworzyć klasę reprezentującą nasz kontroler i oznaczyć ją adnotacją `@RestController`. W ciele tej klasy
wstrzykujemy np. beana reprezentującego serwis interesujacych nas danych i tworzymy metody, które
będą realizowane przy przechwyceniu danego żądania. 

Aby dana metoda metoda mogła przechwycić dany request, należy oznaczyć ją adnotacją `@GetMapping`, `@PostMapping`, `@DeleteMapping`, itd. w zależności od typu żadania HTTP (tj. np.
POST, PUT, GET, DELETE, PATCH, ...). Następnie jako argument adnotacji podajemy ścieżkę dostępu, czyli np. `/entity` - w tym przypadku
tak oznaczona metoda będzie mogła przechwycić requesty z `[host]:[port]/entity`.

Dodatkowo możemy ustawić ścieżkę dostępu dla całego kontrolera, jeżeli np. budujemy REST API dla różnych rodzajów danych
i chcemy wydzielić bardziej ogólne ścieżki dostępu dla każdej z nich. W tym celu korzystamy z adnotacji `@RequestMapping`, którą 
umieszczamy na klasą reprezentującą kontroler. Analogicznie jako argument podajemy ścieżkę dostępu.

Pełny przykład:
```java
@RestController
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/all")
    public List<Entity> findAll() {
        return entityService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        entityService.deleteById(id);
    }

    @PostMapping
    public void createEntity(@RequestBody Entity entity) {
        entityService.createOrUpdate(entity);
    }

    @PutMapping
    public void updateEntity()

}
```

#### @PathVariable i @RequestParam

W poniższym przykładzie widzimy użycie adnotacji `@PathVariable`. Służy ona do odczytania części ścieżki, którą
nie może być ustawiona "na sztywno" - np. ID danego entity (ponieważ dla każdego entity ID jest różne). 

Zatem w naszym przypadku,
jeżeli nasz *GET request* będzie wyglądał następująco: `localhost:8080/entity/a2`, to otrzymamy danego o entity z ID `a2`

```java
@GetMapping("/entity/{id}")
public Optional<Entity> findById(@PathVariable String id) {
    entityService.deleteById(id);
}
```

W przypadku, gdybyśmy chcieli odczytać tzw. *query parameters* danego żądania, musimy skorzystać z adnotacji `@RequestParam`.
Działanie tej adnotacji obrazuje poniższy przykład:

```java
@GetMapping("/api/v2")
public String getIdParam(@RequestParam String id) {
    return id;
}
```

Dla *GET request*: `localhost:8080/api/v2?id=a2`, otrzymamy ze strony serwera odpowiedź: `a2`.


### Spring Boot + JPA + MySQL

[w budowie]