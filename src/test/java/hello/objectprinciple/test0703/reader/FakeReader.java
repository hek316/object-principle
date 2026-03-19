package hello.objectprinciple.test0703.reader;

import hello.objectprinciple.test0703.calls.Call;
import hello.objectprinciple.test0703.calls.Reader;

import java.util.List;

public class FakeReader implements Reader {
    private List<Call> calls;

    public FakeReader(Call ... calls) {
        this.calls = List.of(calls);
    }

    @Override
    public List<Call> read() {
        return calls;
    }
}
