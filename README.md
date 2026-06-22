# Actividad 3 - Pricing Service

Microservicio de estimacion de tarifas. Actividad K8S: Docker, Helm, Kubernetes, ArgoCD y CI/CD.

**Wolfran Alirio Pinzon Murillo  - 0000393439**
**Henry Julian Salazar Salcedo  - 0000396117**
**Carlos Alberto Zambrano Braydi  - 0000395349**
**Pedro David Ramos Salamanca  - 0000403935**
**Jorge Antonio Vidal Orozco  - 0000393815**

Grupo 5



**Repositorio:** https://github.com/jscodeup/actividad3-pricing-service  
**Imagen Docker:** `hjsalazar9/pricing-service`

## Endpoints

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | `/health` | Estado del servicio |
| GET | `/api/pricing/estimate?city=bogota` | Tarifa estimada |

## Estructura

```
├── src/                    # Microservicio Spring Boot
├── Dockerfile
├── helm/pricing-service/   # Chart Helm (values + override dev)
├── argocd/application.yaml # App ArgoCD
└── .github/workflows/      # Pipeline CI/CD
```

## 1. Correr en local (IntelliJ o Maven)

```bash
mvn spring-boot:run
curl http://localhost:8080/health
curl "http://localhost:8080/api/pricing/estimate?city=bogota"
```

## 2. Docker

```bash
docker build -t hjsalazar9/pricing-service:local .
docker run -p 8080:8080 hjsalazar9/pricing-service:local
```

## 3. Kubernetes con Helm

```bash
helm install pricing ./helm/pricing-service -f ./helm/pricing-service/values-dev.yaml
kubectl get pods
kubectl port-forward svc/pricing-service 8080:8080
curl http://localhost:8080/health
```

### values.yaml vs values-dev.yaml

- **values.yaml:** 2 replicas, valores por defecto de produccion.
- **values-dev.yaml:** override para desarrollo (1 replica, pull Always).

## 4. ArgoCD

```bash
kubectl create namespace argocd
helm repo add argo https://argoproj.github.io/argo-helm
helm install argocd argo/argo-cd -n argocd
kubectl apply -f argocd/application.yaml
```

UI: `kubectl port-forward svc/argocd-server -n argocd 8081:443` → https://localhost:8081

## 5. Pipeline CI/CD

En cada push a `main`, GitHub Actions construye y sube la imagen a Docker Hub.

Secrets en GitHub (Settings → Secrets → Actions):

- `DOCKER_USERNAME` = hjsalazar9
- `DOCKER_PASSWORD` = token de Docker Hub (no la contrasena)

## Video de entrega

Mostrar: codigo → Docker → Helm/K8s → ArgoCD → pipeline en verde.
