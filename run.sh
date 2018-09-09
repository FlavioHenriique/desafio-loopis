sudo docker build -t loopis/banco .
cd rest-api/
mvn clean package

sudo docker build -t loopis/api .

sudo docker run -d --name bancoloopis -p 5433:5432 loopis/banco
sudo docker run -d  -p 8081:8080 --link bancoloopis:host-banco loopis/api
