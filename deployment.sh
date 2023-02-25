echo "------------------------------------------------------------------------"
echo " Build & Deploy Order API"
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

echo "------------------------------------------------------------------------"
echo " Deploying Container images on Kubernetes"
echo "------------------------------------------------------------------------"

echo "----------------------- Deploying Cart Service -------------------------"
kubectl apply -f cart/deployment.yaml

echo "----------------------- Deploying Order Service ------------------------"
kubectl apply -f order/deployment.yaml

echo "----------------------- Deploying Product Service ----------------------"
kubectl apply -f product/deployment.yaml

echo "----------------------- Deploying Report Service -----------------------"
kubectl apply -f report/deployment.yaml