package hello.objectprinciple.test0702;

import java.util.List;

public class CallCollector {
    //private CsvReader csvReader;
    private Reader reader;

    public CallCollector(Reader reader) {
        this.reader = reader;
    }

    public CallHistory collect(String phone) {
        CallHistory history = new CallHistory(phone);

        List<Call> calls = reader.read();

        for(Call call : calls) {
            if (call.from().equals(phone)) {
                history.append(call);
            }
        }

        return history;
    }
}