# 🛒 Shopping Mall API

Spring Boot 기반 쇼핑몰 백엔드 API 프로젝트입니다.

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Gradle

## 🚀 Features

### 상품
- 상품 생성 / 조회 / 수정 / 삭제 (CRUD)

### 주문
- 상품 ID 기반 주문 생성
- 주문 조회 시 상품 이름 포함
- 상품 이름 변경 시 주문에도 반영 (연관관계 설계)

## 💡 Key Design
- DTO를 활용하여 Entity 직접 노출 방지
- Order와 Product를 ManyToOne으로 설계
- 상품 정보 변경 시 주문에도 반영되도록 구현