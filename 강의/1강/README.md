# 예제 만들기

## 프로젝트 생성

### Spring initializer

* https://start.spring.io/
* 프로젝트 선택
    * Project: Gradle - Groovy
    * Language: Java 17
    * Spring Boot: 3.0.3
* Project Metadata
    * Group: hello
    * Artifact: spring-core-advanced-1
    * Packaging: Jar
* Dependencies
    * Spring Web, Lombok

## 예제 프로젝트 만들기 - V0

### 예제

#### OrderRepository V0

```java
@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        this.sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.info("Thread Sleep Interrupted", e);
        }
    }
}
```

* `@Repository`
    * 컴포넌트 스캔의 대상이 된다. 따라서 스프링 빈으로 자동 등록된다.
* `sleep(1000)`
    * 리포지토리는 상품을 저장하는데 약 1초 정도 걸리는 것으로 가정하기 위해 1초 지연을 주었다. (1000ms)
* 예외가 발생하는 상황도 확인하기 위해 파라미터 `itemId`의 값이 `ex`로 넘어오면 `IllegalStateException` 예외가 발생하도록 했다.

#### OrderService V0

```java
@Service
@RequiredArgsConstructor
public class OrderServiceV0 {
    private final OrderRepositoryV0 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
```

* `@Service`
    * 컴포넌트 스캔의 대상이 된다.

#### OrderController V0

```java
@RestController
@RequestMapping("/v0")
@RequiredArgsConstructor
public class OrderControllerV0 {
    private final OrderServiceV0 orderService;

    @GetMapping("/request")
    public String request(
            @RequestParam String itemId
    ) {
        orderService.orderItem(itemId);
        return itemId;
    }
}
```

* `@RestController`
    * 컴포넌트 스캔과 스프링 Rest 컨트롤러로 인식된다.

## 로그 추적기 - 요구사항 분석

## 로그 추적기 V1 - 프로토타입 개발

## 로그 추적기 V1 - 적용

## 로그 추적기 V2 - 파라미터로 동기화 개발

## 로그 추적기 V2 - 적용
