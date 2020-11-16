# GPT2 Service in Scala
A service to generate texts with GPT-2 written in Scala with Tensorflow Java and Play. This service was tested with following dependencies:

- Scala 2.12.2
- sbt 0.13.18
- Python 3.7.9
- wget  1.20.3 built on darwin19.0.0
- curl 7.64.1 (x86_64-apple-darwin20.0)

## Getting started

**Download model and run tests**
```
sh setup_dev.sh
sbt test
```

**Download models and start GPT-2 service on localhost:9000**
```
sh setup.sh
sbt run
```

## Run in production
Create a `.env` file with the domain on which the GPT-2 service is available.
```
SERVER_DOMAIN=localhost:9000
```
**Note:** If you want to change the port, then you have to adjust the `docker-compose.yml` file.

## Available Requests
With cURL we can define requests to our service. Below are some request examples that are supported. Additionally, the assumption is that GPT-2 service can be reached at 'localhost:9000/predict':
### Get all available models

```
curl -X GET \
  -v "http://localhost:9000/gpt2/models"
```

This will give you a list with all models:

```
tfmodel,dummy_model,boom
```

### Get current model

With cURL we can define a POST request to our service. The GPT-2 service can be reached at 'localhost:9000/predict':

```
curl -X GET \
  -v "http://localhost:9000/gpt2/model"
```

This will give you the name of the current model:

```
tfmodel
```

### Set model

```
curl -X PUT \
  -v "http://localhost:9000/gpt2/model/boom"
```

If successful and model exist, it will return the model name:
```
boom
```

### Generate text with current model

```
curl -X POST \
  -H "Content-Type: text/plain" \
  -v "http://localhost:9000/gpt2/predict" \
  -d "Hi I just met you"
```

This will give you a JSON with the generated text:

```
Hi I just met you ...
```
