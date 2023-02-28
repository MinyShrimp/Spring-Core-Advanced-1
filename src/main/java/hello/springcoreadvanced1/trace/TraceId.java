package hello.springcoreadvanced1.trace;

import lombok.Getter;

import java.util.UUID;

/**
 * 로그 추적기의 트랜잭션 ID와 깊이 표현
 */
@Getter
public class TraceId {
    /**
     * 현재 Trace의 UUID
     */
    private final String id;

    /**
     * 현재 Trace의 깊이
     */
    private final int level;

    /**
     * 새로운 Trace 생성
     */
    public TraceId() {
        this.id = this.createId();
        this.level = 0;
    }

    /**
     * 내부 호출용 생성자
     */
    private TraceId(String ID, int LEVEL) {
        this.id = ID;
        this.level = LEVEL;
    }

    /**
     * 새로운 UUID 생성
     */
    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * @return 다음 LEVEL Trace
     */
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    /**
     * @return 이전 LEVEL Trace
     */
    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    /**
     * @return LEVEL == 0
     */
    public boolean isFirstLevel() {
        return level == 0;
    }
}
