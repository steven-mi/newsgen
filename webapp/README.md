# newsgen webapp

## Development mode

**Install dependencies**
```
npm install
```

**Run the tests**
```
npm test
```

**Run webapp in development mode**: The page will reload if you make edits.
You will also see any lint errors in the console.
```
npm start
```

## Production mode

Create a .env file in which you annotate the domain of the GPT2 services

```
GPT2_SERVICE_DOMAIN=localhost:9000
```


### Without docker

Afterward optimize code for deployment and run app in production mode:
```
npm run build
npm install -g serve

serve -l 5000 -s build
```

### With docker

Afterward start webapp with docker-compose:
```
docker-compose up
```
