FROM node:latest

WORKDIR /usr/src/app


COPY . /usr/src/app


RUN npm install -g serve
RUN npm ci --only=production
RUN npm run build

EXPOSE 3000

CMD sh /usr/src/app/start.sh
