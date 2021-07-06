# kube-nginx-ingress
A simple k8s ingress demo with nginx ingress controller. Uses a configurable default backend page.

Steps:

1) Install nginx ingress controller in your k8s cluster(See here https://github.com/ratulb/k8s-easy-install how to quickly launch a k8s cluster on a single/multi node(s) with single/multi master(s) - fronted by envoy/haproxy or nginx load balancer).
https://kubernetes.github.io/ingress-nginx/deploy/#bare-metal

2) Check out this repository - git clone https://github.com/ratulb/kube-nginx-ingress.git

3) cd kube-nginx-ingress/
 
4) kubectl apply -f demo-svc-deploy.yaml

5) kubectl apply -f default-http-backend.yaml

6) kubectl apply -f demo-ingress.yaml

7) Find the NodePort port - kubectl -n ingress-nginx get svc

8) Put the entry '127.0.0.1 demo.ingress.example' in /etc/hosts

9) curl demo.ingress.example:[NodePort port]/service1[service2, xxx]

10) demo.ingress.example:[NodePort port]/xxxx - should show response from default-backend-service
