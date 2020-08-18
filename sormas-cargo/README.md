## 0-pain SORMAS Dev-Setup

## Prepare

```
cd sormas-base && mvn install
cd sormas-severlibs && mvn install
```

### Start DB

```
cd sormas-cargo && docker-compose up -d
```

## Run
This will start and configure payara application server. 

```
cd sormas-cargo && mvn cargo:run
```

## Visit

http://localhost:8080/sormas-ui

[Payara admin](http://localhost:4848) (user: `admin`, password: `admin`)