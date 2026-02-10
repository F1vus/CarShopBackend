# Backend – instrukcja uruchomienia

## Opis

Niniejszy dokument opisuje sposób uruchomienia części backendowej projektu.  
Backend został zaimplementowany w technologii **Java Spring Boot** i wykorzystuje **bazę danych PostgreSQL** uruchamianą w kontenerze Docker.

---

## Wymagania systemowe

Do poprawnego uruchomienia projektu wymagane są:

- **Java** - 21
- **Maven**
- **Docker**
- **Docker Compose**

---

## Uruchomienie środowiska bazodanowego

Projekt wykorzystuje bazę danych **PostgreSQL**, która uruchamiana jest przy pomocy **Docker Compose**.

W pierwszym kroku należy przejść do **głównego katalogu projektu**, w którym znajduje się plik `docker-compose.yml`, a następnie wykonać polecenie:

```bash
docker compose up
```

> **Uwaga (tryb developerski):**  
> Baza danych PostgreSQL domyślnie korzysta z portu **5432**.  
> W trybie developerskim należy upewnić się, że port ten nie jest aktualnie zajęty przez inny proces lub inną instancję PostgreSQL.

Baza danych tworzona jest automatycznie podczas uruchamiania kontenerów i nie wymaga ręcznego tworzenia schematu ani tabel.

## Uruchomienie aplikacji backendowej

Po poprawnym uruchomieniu kontenerów Dockera można uruchomić aplikację backendową opartą o Spring Boot.

Projekt wykorzystuje Maven, dlatego aplikację można uruchomić za pomocą polecenia:

```bash
mvn spring-boot:run
```

Alternatywnie aplikację można uruchomić bezpośrednio z poziomu środowiska IDE (np. IntelliJ IDEA) jako aplikację Spring Boot.