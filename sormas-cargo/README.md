## 0-pain SORMAS Dev-Setup

## Prepare

```
cd sormas-base && mvn install
cd sormas-severlibs && mvn install
```

### Start DB

```
cd sormas-cargo && docker compose up -d
```

## Run

```
cd sormas-cargo && mvn cargo:run
```

## Visit

http://localhost:6080/sormas-ui