# GPT2 Service in Scala 
A service to generate texts with GPT-2 written in Scala with Tensorflow Java and Play. This service was tested with following dependencies:

- Scala 2.12.2
- sbt 0.13.18
- Python 3.7.9
- wget  1.20.3 built on darwin19.0.0
- curl 7.64.1 (x86_64-apple-darwin20.0)

## Getting started

**Get code and current production model**
```
https://github.com/NewsPipe/gpt2-service-scala.git
cd gpt2-service-scala
```
**Run tests**
```
sh setup_test.sh
sbt test
```

**Start GPT-2 service on localhost:9000** 
```
sh setup.sh
sbt run
```


## Available Requests
With cURL we can define requests to our service. Below are some request examples that are supported. Additionally, the assumption is that GPT-2 service can be reached at 'localhost:9000/predict':
### Get all available models

```
curl -X GET \
  -H "Content-Type: application/json" \
  -v "http://localhost:9000/models" 
```

This will give you a list with all models:

```
["tfmodel", "dummy_model", "boom"]
```

### Get current model

With cURL we can define a POST request to our service. The GPT-2 service can be reached at 'localhost:9000/predict':

```
curl -X GET \
  -H "Content-Type: application/json" \
  -v "http://localhost:9000/model" 
```

This will give you the name of the current model:

```
"tfmodel"
```

### Set model

```
curl -X PUT \
  -H "Content-Type: application/json" \
  -v "http://localhost:9000/model/1" 
```

If successful it will return the model name:
```
dummy_model
```

### Generate text with current model

```
curl -X POST \
  -H "Content-Type: application/json" \
  -v "http://localhost:9000/predict" \
  -d '{"text":"Hi I just met you"}'
```

This will give you a JSON with the generated text:

```
{"prediction": "..."}
```

