# Description
 [![Build Status](https://travis-ci.org/bpedroso/machine-destroyer.svg?branch=master)](https://travis-ci.org/bpedroso/machine-destroyer)

Service to consume machine resources. Read a text with 5k letters and add to memory.

![alt text](dickvigarista-stamp.png)


Increase the consume of memory to the power passed in the path. If empty,  1k*text size.

### Paths
| Feature  | Path                     |
| -------- | ------------------------ |
| Memory   | /destroy/memory/{power}  |
| Memory   | /destroy/memory/clean    |
| CPU      | /destroy/cpu/{power}     |


# Docker Hub
docker pull bpedroso/machine-destroyer

--> Because hub.docker.com is the default registry, you do not need to set the registry URL value.

# Running on Maven

## 1 - build a docker image and get the image id

mvn docker:build -X |grep 'Successfully built'|awk '{print $NF}'


## 2 - Use the image id to tag an image

docker tag \[OPTIONS\] <IMAGE> \[REGISTRYHOST/\]\[USERNAME/\]NAME\[:TAG\]  # Tag <image> for upload to registry

docker tag 05ef89eb5c42 bpedroso/machine-destroyer:latest


## 3 - Login on your registry

docker login -u $REGISTRY_USERNAME -p $REGISTRY_PASSWORD \[REGISTRYHOST\]


## 4 - Push the image to your registry

docker push hub.docker.com/r/bpedroso/machine-destroyer:latest
