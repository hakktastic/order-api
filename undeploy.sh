echo "------------------------------------------------------------------------"
echo " Undeploy Order API"
echo "------------------------------------------------------------------------"

echo "------------------------------------------------------------------------"
echo " Deleting Container images"
echo "------------------------------------------------------------------------"

echo "----------------------- Deleting Cart Service --------------------------"
docker image rm hakktastic/demo-cart-service:0.0.1

echo "----------------------- Deleting Order Service -------------------------"
docker image rm hakktastic/demo-order-service:0.0.1

echo "----------------------- Deleting Product Service -----------------------"
docker image rm hakktastic/demo-product-service:0.0.1

echo "----------------------- Deleting Report Service ------------------------"
docker image rm hakktastic/demo-report-service:0.0.1

echo "------------------------------------------------------------------------"
echo " Deleting Kubernetes deployments"
echo "------------------------------------------------------------------------"

echo "----------------------- Deleting Cart Service -------------------------"
kubectl delete -f cart/deployment.yaml

echo "----------------------- Deleting Order Service ------------------------"
kubectl delete -f order/deployment.yaml

echo "----------------------- Deleting Product Service ----------------------"
kubectl delete -f product/deployment.yaml

echo "----------------------- Deleting Report Service -----------------------"
kubectl delete -f report/deployment.yaml