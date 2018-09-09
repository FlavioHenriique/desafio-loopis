
docker kill $(docker container ls -a -q)
docker rmi -f $(docker image ls loopis/* -q)



docker kill $(docker container ls -a -q)
docker rmi -f $(docker image ls flavio/* -q)

sudo docker rm -f $(sudo docker ps -aq --filter name=apiloopis)
sudo docker rm -f $(sudo docker ps -aq --filter name=bancoloopis)
