echo "------------------------------------------------------------------------"
echo " Build Order API"
echo "------------------------------------------------------------------------"

echo "------------------------------------------------------------------------"
echo " Building Container images"
echo "------------------------------------------------------------------------"

echo "----------------------- Building Cart Service --------------------------"
cd cart
mvn clean package -DskipTests
docker build -t hakktastic/demo-cart-service:0.0.1 .
docker push hakktastic/demo-cart-service:0.0.1
cd ..

echo "----------------------- Building Order Service -------------------------"
cd order
mvn clean package -DskipTests
docker build -t hakktastic/demo-order-service:0.0.1 .
docker push hakktastic/demo-order-service:0.0.1
cd ..

echo "----------------------- Building Product Service -----------------------"
cd product
mvn clean package -DskipTests
docker build -t hakktastic/demo-product-service:0.0.1 .
docker push hakktastic/demo-product-service:0.0.1
cd ..

echo "----------------------- Building Report Service ------------------------"
cd product
mvn clean package -DskipTests
docker build -t hakktastic/demo-report-service:0.0.1 .
docker push hakktastic/demo-report-service:0.0.1
cd ..
