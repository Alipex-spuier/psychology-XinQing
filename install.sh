#!/bin/sh
if [ "$1" == "true" ]; then
git clone git@gitee.com:boliang2190/psychology.git
cd psychology
sudo docker run -itd --name nginx spuieralipex/nginx:v1 /bin/bash
sudo docker cp nginx:/var/www/ ./
sudo docker stop nginx&&sudo docker rm nginx
fi

sudo docker-compose down --rmi local
# Navigate to ./backend directory
cd ./psychology-backend/backend || { echo "Failed to change directory to ./psychology-backend/backend"; exit 1; }

# Execute mvn install
echo "Executing mvn install in ./psychology-backend/backend..."
mvn install
if [ $? -ne 0 ]; then
  echo "mvn install failed."
  exit 1
fi

# Execute mvn package
echo "Executing mvn package in ./psychology-backend/backend..."
mvn package
if [ $? -ne 0 ]; then
  echo "mvn package failed."
  exit 1
fi

# Navigate back to the original directory
cd - || { echo "Failed to change back to the original directory"; exit 1; }

# Execute docker-compose up -d
echo "Executing sudo docker-compose up -d..."
sudo docker-compose up -d
if [ $? -ne 0 ]; then
  echo "docker-compose up failed."
  exit 1
fi

echo "Script executed successfully."

