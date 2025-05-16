# 📘 Java Streams — Kompletna Notatka

Strumienie (Streams) w Javie to potężny mechanizm umożliwiający deklaratywne przetwarzanie danych.

---

## 🎯 Czym jest `Stream`?

Strumień to sekwencja danych, która umożliwia:
- filtrowanie (`filter`)
- transformację (`map`)
- redukcję (`reduce`)
- sortowanie (`sorted`)
- zbieranie (`collect`)

Nie modyfikuje oryginalnych struktur danych — działa „na przepływie”.

---

## 📦 Tworzenie strumieni

### Ze zwykłej kolekcji:
```java
List<String> lista = List.of("a", "b", "c");
lista.stream();
```

### Z tablicy:
```java
Arrays.stream(new int[] {1, 2, 3});
```

### Ze zbioru (`Set`):
```java
Set<Integer> zbior = Set.of(1, 2, 3);
zbior.stream();
```

### Z mapy (specjalnie):
```java
Map<String, Integer> mapa = Map.of("A", 1, "B", 2);
mapa.entrySet().stream();
```

---

## 🔁 Operacje pośrednie (nie uruchamiają jeszcze strumienia)

| Metoda | Działanie |
|--------|-----------|
| `filter(Predicate)` | filtrowanie elementów |
| `map(Function)` | przekształcanie elementów |
| `flatMap(Function)` | spłaszczanie zagnieżdżonych kolekcji |
| `distinct()` | usuwanie duplikatów |
| `sorted()` | sortowanie |
| `limit(n)` | ograniczenie do `n` elementów |
| `skip(n)` | pomija `n` pierwszych elementów |

---

## ✅ Operacje końcowe (uruchamiają strumień)

| Metoda | Działanie |
|--------|-----------|
| `collect()` | zbieranie wyników |
| `forEach()` | wykonanie akcji dla każdego elementu |
| `reduce()` | redukcja do jednej wartości |
| `count()` | zliczanie elementów |
| `min()`, `max()` | znajdowanie ekstremum |
| `anyMatch()`, `allMatch()` | sprawdzenie warunków logicznych |

---

## 🎯 Przykłady

### 🔹 Filtrowanie i mapowanie
```java
List<String> wynik = lista.stream()
    .filter(s -> s.startsWith("K"))
    .map(String::toUpperCase)
    .toList();
```

### 🔹 Sumowanie liczb
```java
int suma = List.of(1,2,3,4).stream()
    .mapToInt(Integer::intValue)
    .sum();
```

### 🔹 Przetwarzanie mapy
```java
Map<String, Integer> mapa = Map.of("a", 1, "b", 2);
mapa.entrySet().stream()
    .filter(e -> e.getValue() > 1)
    .forEach(e -> System.out.println(e.getKey()));
```

### 🔹 Zbieranie do mapy
```java
Map<Integer, String> wynik = lista.stream()
    .collect(Collectors.toMap(String::length, s -> s, (s1, s2) -> s1));
```

---

## ⚠️ Uwaga na pułapki

- Stream działa **tylko raz** – po wykonaniu operacji końcowej nie możesz użyć go ponownie.
- `map()` zmienia wartości, `filter()` je wybiera.
- `collect()` kończy strumień i zwraca wynik.
- Strumienie są **leniwe** – nie wykonują się dopóki nie dasz operacji końcowej.

---

## 🚀 Parallel Streams

```java
lista.parallelStream()
    .map(...)
    .filter(...)
    .collect(...)
```

- Przetwarza dane równolegle (multithreading)
- Używaj tylko dla dużych zbiorów danych (>10k)

---

## 🧪 Redukcje (`reduce()`)

```java
int wynik = List.of(1, 2, 3).stream()
    .reduce(0, (a, b) -> a + b);
```

- pierwszy argument = wartość początkowa
- `(a, b)` = funkcja łącząca

---

## 📦 Collectors

```java
.collect(Collectors.toList())
.collect(Collectors.toSet())
.collect(Collectors.joining(", "))
.collect(Collectors.groupingBy(String::length))
.collect(Collectors.partitioningBy(s -> s.length() > 3))
```

---

## 🔥 TL;DR Stream Flow

```java
kolekcja.stream()
    .filter(...)
    .map(...)
    .sorted()
    .collect(Collectors.toList());
```

---

## ✅ Kiedy używać Stream API?

| Kiedy | Używaj Stream |
|-------|----------------|
| Przetwarzanie danych z list, zbiorów, map | ✔️ |
| Filtrowanie i transformacje | ✔️ |
| Czytelność i krótszy kod | ✔️ |
| Duże zbiory danych (parallelStream) | ✔️ |
| Potrzebujesz modyfikować oryginalną kolekcję | ❌ NIE (Stream nie modyfikuje!) |

---

## 🧠 Dobre praktyki

- Stosuj `Stream` do logiki przetwarzania, nie do `set()` i `add()`
- Unikaj `forEach` jeśli chcesz coś zebrać — lepiej `collect`
- Rozbij długi stream na mniejsze, jeśli tracisz czytelność
- Unikaj `parallelStream` bez pomiaru wydajności

---

🐺 SIGMA level unlocked.