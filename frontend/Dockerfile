FROM  node:lts-stretch

WORKDIR /
COPY . ./
RUN npm install
RUN npm run build
RUN npm install -g serve

ENTRYPOINT serve -l 5000 -s build
