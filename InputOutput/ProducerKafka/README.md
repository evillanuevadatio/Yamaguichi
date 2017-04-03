# Producer Kafka



#### Crear Topic
## No Cluster
Cuando se crea un topic y no se esta en cluster no se puede definir el factor de replicación mas de 1, (triste)

```
> kafka-topics --zookeeper localhost:2181 --create --topic chartrix --partitions=3 --replication-factor=1
```

## Cluster
```
> kafka-topics --zookeeper brocker1:2181,brocker2:2181 --create --topic chartrix --partitions=3 --replication-factor=3
```

## Listar Topic
```
> kafka-topics --zookeeper localhost:2181 --list
```











