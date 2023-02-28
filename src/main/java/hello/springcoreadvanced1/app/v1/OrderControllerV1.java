package hello.springcoreadvanced1.app.v1;

import hello.springcoreadvanced1.trace.TraceStatus;
import hello.springcoreadvanced1.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/request")
    public String request(
            @RequestParam String itemId
    ) {
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return itemId;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
