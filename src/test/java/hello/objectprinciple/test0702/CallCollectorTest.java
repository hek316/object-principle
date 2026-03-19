package hello.objectprinciple.test0702;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CallCollectorTest {

    @Test
    public void collect() {
        CallCollector callCollector = new CallCollector(new JsonReader("calls.json"));
        CallHistory history = callCollector.collect("010-1111-2222");

        Assertions.assertThat(history.callDuration()).isEqualTo(159);
    }

}