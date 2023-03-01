echo "------------------------------------------------------------------------"
echo " Build Order API"
echo "------------------------------------------------------------------------"

mvn clean
mvn package

echo "------------------------------------------------------------------------"
echo " Building Container images"
echo "------------------------------------------------------------------------"

echo "----------------------- Building Cart Service --------------------------"

docker build -t hakktastic/demo-cart-service:0.0.1 cart/.
docker push hakktastic/demo-cart-service:0.0.1


echo "----------------------- Building Order Service -------------------------"

docker build -t hakktastic/demo-order-service:0.0.1 order/.
docker push hakktastic/demo-order-service:0.0.1


echo "----------------------- Building Product Service -----------------------"

mvn clean package -DskipTests
docker build -t hakktastic/demo-product-service:0.0.1 product/.
docker push hakktastic/demo-product-service:0.0.1


echo "----------------------- Building Report Service ------------------------"

mvn clean package -DskipTests
docker build -t hakktastic/demo-report-service:0.0.1 report/.
docker push hakktastic/demo-report-service:0.0.1