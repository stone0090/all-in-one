kubectl get pod -A
kubectl get deployment -A | grep aio | awk '{print $2}' | xargs kubectl -n aio delete deployment
kubectl get pod -A
echo '-------------------'
kubectl get svc -A
kubectl get svc -A | grep aio | awk '{print $2}' | xargs kubectl -n aio delete svc
kubectl get svc -A
echo '-------------------'