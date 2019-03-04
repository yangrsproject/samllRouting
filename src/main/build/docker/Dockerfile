#基础镜像
FROM centos:7

#镜像维护者
MAINTAINER yangrs yangrs@aliyun.com

#定义工作文件夹
WORKDIR /app

#将Dockerfile同路径下文件拷贝到某路径下
COPY smallrouting-0.0.1-SNAPSHOT.jar /app

#安装jdk
RUN yum -y install java-1.8.0-openjdk-devel.x86_64

#提供挂载点
VOLUME /app/logs

#暴露端口
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/smallrouting-0.0.1-SNAPSHOT.jar"]