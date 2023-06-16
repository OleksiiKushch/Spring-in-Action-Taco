Test project "Taco" (Walls C. Spring in Action 6ed 2022)

docker network create cassandra-net
docker run --name my-cassandra --network cassandra-net -p 9042:9042 -d cassandra:latest

docker run -it --network cassandra-net --rm cassandra cqlsh my-cassandra

create keyspace tacocloud
    with replication={'class':'SimpleStrategy', 'replication_factor':1}
    and durable_writes=true;
