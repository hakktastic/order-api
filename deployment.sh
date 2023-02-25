echo "------------------------------------------------------------------------"
echo " Deploy Order API"
echo "------------------------------------------------------------------------"

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