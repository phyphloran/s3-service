# 📸 S3 Photo Storage Service

---

## 🚀 Features

- ✅ Upload images to an S3-compatible storage (MinIO)  
- ✅ File validation: type, size, integrity  
- ✅ Custom annotations for convenient validation  
- ✅ Generation of unique file names (UUID)  
- ✅ Full monitoring via Spring Boot Actuator  
- ✅ Supported formats: JPEG, JPG, PNG, BMP  
- ✅ HTTPS/HTTP support for public links  

---

## 🛠️ Technologies

- **Java 21** – programming language  
- **Spring Boot 3.5.9** – framework  
- **AWS SDK for Java 2.x** – working with S3/MinIO  
- **Spring Validation** – data validation  
- **Spring Boot Actuator** – monitoring and metrics  
- **Lombok** – reduces boilerplate code  
- **MinIO** – S3-compatible storage  

---

## 🔄 CI/CD Pipeline with GitHub Actions
The project includes an automated CI/CD pipeline that builds and deploys the service using GitHub Actions and Docker.

### Pipeline Stages:
1. Build Stage
2. Deploy Stage

[![workflow](https://github.com/phyphloran/s3-service/actions/workflows/workflow.yml/badge.svg)](https://github.com/phyphloran/s3-service/actions/workflows/workflow.yml)

---

## 🏗️ Architecture

The project follows a layered architecture with separation of concerns:

- **Controller Layer** – handles HTTP requests and responses  
- **Service Layer** – business logic for photo handling  
- **Annotations & Validation** – custom image validators  
- **Integration with S3/MinIO** – upload and store images  
- **Monitoring & Logging** – via Spring Boot Actuator and SLF4J  

![Project Architecture](https://github.com/phyphloran/s3-service/blob/main/architecture.png)
