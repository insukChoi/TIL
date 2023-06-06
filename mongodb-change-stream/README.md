# [Spring Mongodb Reference](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#change-streams)

## Change Stream
* Change Stream support is only possible for replica sets or for a sharded cluster

Change Streams can be consumed with both, the imperative and the reactive MongoDB Java driver. It is highly recommended to use the reactive variant, as it is less resource-intensive. However, if you cannot use the reactive API, you can still obtain change events by using the messaging concept that is already prevalent in the Spring ecosystem.

It is possible to watch both on a collection as well as database level, whereas the database level variant publishes changes from all collections within the database. When subscribing to a database change stream, make sure to use a suitable type for the event type as conversion might not apply correctly across different entity types. In doubt, use Document.

# Run Mongodb ReplicaSet
$ npm install run-rs -g
$ run-rs 5.0.6 --dbpath '/opt/homebrew/var/mongodb'