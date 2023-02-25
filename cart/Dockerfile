FROM alpine:latest
RUN apk add --no-cache openjdk17-jre-headless
EXPOSE 8081

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","nl.hakktastic.order.api.cart.CartApplication"]