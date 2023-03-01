# 템플릿 메서드 패턴과 콜백 패턴

## 템플릿 메서드 패턴 - 시작

### 핵심 기능 vs 부가 기능

핵심 기능은 해당 객체가 제공하는 고유의 기능이다.
예를 들어서 orderService 의 핵심 기능은 주문 로직이다.

메서드 단위로 보면 `orderService.orderItem()`의 핵심 기능은 주문 데이터를 저장하기 위해
리포지토리를 호출하는 `orderRepository.save(itemId)` 코드가 핵심 기능이다.

부가 기능은 핵심 기능을 보조하기 위해 제공되는 기능이다.
예를 들어서 로그 추적 로직, 트랜잭션 기능이 있다.
이러한 부가 기능은 단독으로 사용되지는 않고, 핵심 기능과 함께 사용된다.
예를 들어서 로그 추적 기능은 어떤 핵심 기능이 호출되었는지 로그를 남기기 위해 사용한다.
그러니까 핵심 기능을 보조하기 위해 존재한다

### 변하는 것과 변하지 않는 것을 분리

좋은 설계는 변하는 것과 변하지 않는 것을 분리하는 것이다.
여기서 핵심 기능 부분은 변하고, 로그 추적기를 사용하는 부분은 변하지 않는 부분이다.
이 둘을 분리해서 모듈화해야 한다.

**템플릿 메서드 패턴**(Template Method Pattern)은 이런 문제를 해결하는 디자인 패턴이다.

## 템플릿 메서드 패턴 - 예제 1

### 예제

#### TemplateMethodTest

```java
@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직 2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
```

#### 실행 결과

```
비즈니스 로직 1 실행
resultTime = 2
비즈니스 로직 2 실행
resultTime = 0
```

### 분석

* 변하는 부분: 비즈니스 로직
* 변하지 않는 부분: 시간 측정

이제 이 둘을 분리해보자.

## 템플릿 메서드 패턴 - 예제 2

## 템플릿 메서드 패턴 - 예제 3

## 템플릿 메서드 패턴 - 적용 1

## 템플릿 메서드 패턴 - 적용 2

## 템플릿 메서드 패턴 - 정의

## 전략 패턴 - 시작

## 전략 패턴 - 예제 1

## 전략 패턴 - 예제 2

## 전략 패턴 - 예제 3

## 템플릿 콜백 패턴 - 시작

## 템플릿 콜백 패턴 - 예제

## 템플릿 콜백 패턴 - 적용
