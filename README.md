# Description

Service to consume machine resources. Read a text with 5k letters and add to memory.

![alt text](dickvigarista-stamp.png)


Increase the consume of memory to the power passed in the path. If empty,  1k*text size. 
/destroy/memory/{power}

# 1 - build a docker image and get the image id

mvn docker:build -X |grep 'Successfully built'|awk '{print $NF}'| tr -d '\\n'


# 2 - Use the image id to tag an image

docker tag \[OPTIONS\] IMAGE\[:TAG\] \[REGISTRYHOST/\]\[USERNAME/\]NAME\[:TAG\]
docker tag 05ef89eb5c42 hub.docker.com/r/bpedroso/machine-destroyer:latest


# 3 - Login on your registry

docker login -u $REGISTRY_USERNAME -p $REGISTRY_PASSWORD https://hub.docker.com/


# 4 - Push the image to your registry

docker push hub.docker.com/r/bpedroso/machine-destroyer:latest