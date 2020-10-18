# MongoDB

This repository contains Docker composite files for starting a MongoDB instance with Mongo Express support. To get started, you need to create an .env file:

```
MONGO_ROOT_USER=devroot
MONGO_ROOT_PASSWORD=devroot
MONGOEXPRESS_LOGIN=dev
MONGOEXPRESS_PASSWORD=dev
```

Afterwards  start the databases with Docker-Compose:
```
docker-compose up
```

If you need to change the port, adjust the command line inside the yaml files
