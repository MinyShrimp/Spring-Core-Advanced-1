package hello.springcoreadvanced1.app.v3;

import hello.springcoreadvanced1.trace.TraceStatus;
import hello.springcoreadvanced1.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");

            // 로직 시작
            orderRepository.save(itemId);
            // 로직 종료

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}