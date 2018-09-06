
docker kill $(docker container ls -a -q)
docker rmi -f $(docker image ls flavio/* -q)
