# newsgen

![](.github/imgs/dashboard.gif)

Newsgen is a responsive web application for generating German newspaper articles. The entire stack consists of a React frontend and several Scala Play services. At the moment following services are available:
- [gpt2-service-scala](https://github.com/NewsPipe/gpt2-service-scala): A service to generate texts. It is written in Scala by using the frameworks Tensorflow Java and Play
- ...

The whole application is dockerized and can be started with docker-compose. A deployment to a Kubernetes cluster is in progress.

## Getting started
**Download code and go to project folder:**
```
git clone https://github.com/NewsPipe/newsgen.git
cd newsgen
docker-compose up
```


## TODO
- Add kubernetes config
- Code documentation refinement 
- Lookup React standards and refactor frontend code
- CI/CD
- Replace BPE encoder python code with more efficient scala code
