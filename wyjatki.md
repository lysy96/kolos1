
# Interfejsy (`interface`)

Interfejsy służą do definiowania zbiorów metod, które muszą być zaimplementowane w klasach. Są sposobem na osiągnięcie polimorfizmu i rozdzielenie deklaracji od implementacji.

## Podstawowy przykład

```java
public interface Vehicle {
    void start();
    void stop();
}
```

```java
public class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Ruszamy!");
    }

    @Override
    public void stop() {
        System.out.println("Zatrzymujemy się.");
    }
}
```

## `default` i `static` w interfejsach (od Javy 8)

```java
public interface MusicPlayer {
    default void play() {
        System.out.println("Odtwarzanie muzyki...");
    }

    static void info() {
        System.out.println("To jest interfejs odtwarzacza.");
    }
}
```

# Wyjątki (`try-catch`, `throw`, `throws`)

Wyjątki to sposób obsługi błędów w czasie działania programu.

## Obsługa wyjątku

```java
try {
    int wynik = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Błąd: " + e.getMessage());
}
```

## Rzucanie wyjątku

```java
public void divide(int a, int b) throws ArithmeticException {
    if (b == 0) {
        throw new ArithmeticException("Nie dziel przez zero!");
    }
    System.out.println(a / b);
}
```

## Własny wyjątek

```java
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}
```

## Przykład użycia własnego wyjątku

```java
public class Demo {
    public static void main(String[] args) {
        try {
            checkAge(15);
        } catch (MyException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }
    }

    static void checkAge(int age) throws MyException {
        if (age < 18) {
            throw new MyException("Musisz mieć co najmniej 18 lat.");
        }
    }
}
```

Gotowe na kolosa i na repo 😎

# Enumy (`enum`)

Enumy służą do definiowania zbioru stałych wartości. Można im przypisywać własne pola, metody, konstruktory.

## Prosty enum

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY;
}
```

## Enum z polem i metodą

```java
public enum Level {
    EASY(1), MEDIUM(2), HARD(3);

    private int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

# Polimorfizm i rzutowanie

## Polimorfizm – jeden typ, wiele zachowań

```java
class Animal {
    public void sound() {
        System.out.println("Zwierzę wydaje dźwięk");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Hau hau");
    }
}
```

```java
Animal myDog = new Dog();
myDog.sound(); // "Hau hau"
```

## Rzutowanie (casting) i `instanceof`

```java
if (myDog instanceof Dog) {
    ((Dog) myDog).sound();
}
```

# Słowa kluczowe: `this`, `super`, `static`, `final`

```java
public class Example {
    private static int counter = 0;
    private final String name;

    public Example(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("Nazywam się " + this.name);
    }

    public static void increment() {
        counter++;
    }
}
```

```java
public class SubExample extends Example {
    public SubExample(String name) {
        super(name); // wywołanie konstruktora nadrzędnego
    }
}
```

# Pakiety i importy

## Definiowanie pakietu

```java
package mypackage;
```

## Importowanie klas

```java
import java.util.Scanner;
```

## Użycie importowanych klas

```java
public class MyClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
}
```

# Wzorce projektowe (podstawowe)

## Singleton

Jeden obiekt danej klasy w całej aplikacji.

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

## Factory

Zwraca różne obiekty w zależności od parametrów.

```java
public interface Shape {
    void draw();
}

public class Circle implements Shape {
    public void draw() { System.out.println("Rysuję koło"); }
}

public class Square implements Shape {
    public void draw() { System.out.println("Rysuję kwadrat"); }
}

public class ShapeFactory {
    public static Shape getShape(String type) {
        if (type.equals("circle")) return new Circle();
        if (type.equals("square")) return new Square();
        return null;
    }
}
```

## Builder

Służy do tworzenia skomplikowanych obiektów krok po kroku.

```java
public class Car {
    private String engine;
    private int wheels;

    public static class Builder {
        private String engine;
        private int wheels;

        public Builder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.engine = this.engine;
            car.wheels = this.wheels;
            return car;
        }
    }
}
```

