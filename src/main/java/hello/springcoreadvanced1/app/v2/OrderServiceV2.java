package hello.springcoreadvanced1.app.v2;

import hello.springcoreadvanced1.trace.TraceId;
import hello.springcoreadvanced1.trace.TraceStatus;
import hello.springcoreadvanced1.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");

            // 로직 시작
            orderRepository.save(status.getTraceId(), itemId);
            // 로직 종료

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
