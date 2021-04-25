from adoptopenjdk/openjdk14
COPY ./target/ReadFileApp-1.0-SNAPSHOT.jar app.jar
COPY example.txt example.txt
ENTRYPOINT ["java","-jar","app.jar"]
CMD [""]