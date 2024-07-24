FROM node:10.24.1
ADD . /app

WORKDIR /app

ENTRYPOINT ["sh", "/app/start.sh"]
