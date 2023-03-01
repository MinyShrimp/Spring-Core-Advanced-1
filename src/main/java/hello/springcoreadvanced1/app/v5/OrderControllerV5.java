package hello.springcoreadvanced1.app.v5;

import hello.springcoreadvanced1.trace.callback.TraceTemplate;
import hello.springcoreadvanced1.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v5")
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/request")
    public String request(
            @RequestParam String itemId
    ) {
        return template.execute(
                "OrderController.request()",
                () -> {
                    orderService.orderItem(itemId);
                    return itemId;
                }
        );
    }
}
