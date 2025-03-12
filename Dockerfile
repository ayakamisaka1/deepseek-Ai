# 使用 OpenJDK 21 作为基础镜像
FROM openjdk:21

WORKDIR /app

# 将外部构建的 JAR 文件复制到镜像中
COPY target/dream-0.0.1-SNAPSHOT.jar /app/dream-0.0.1-SNAPSHOT.jar

# 暴露端口
EXPOSE 9890

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/dream-0.0.1-SNAPSHOT.jar"]
