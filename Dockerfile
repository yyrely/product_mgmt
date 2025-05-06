FROM openjdk:8-jdk-alpine

WORKDIR /app

# 复制你的jar包到镜像中
COPY ./product-mgmt.jar app.jar

# 使用 ENTRYPOINT 来设置固定的JVM参数和执行命令
ENTRYPOINT ["java", "-Xms512m", "-Xmx512m", "-Duser.timezone=Asia/Shanghai", "-jar", "app.jar"]

# 使用 CMD 提供默认的应用参数，这些可以在运行容器时被覆盖
CMD ["--jasypt.encryptor.password=123456"]