package hello.springcoreadvanced1.app.v2;

import hello.springcoreadvanced1.trace.TraceStatus;
import hello.springcoreadvanced1.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/request")
    public String request(
            @RequestParam String itemId
    ) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");

            // 로직 시작
            orderService.orderItem(status.getTraceId(), itemId);
            // 로직 종료

            trace.end(status);
            return itemId;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
