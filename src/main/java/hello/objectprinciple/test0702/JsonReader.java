package hello.objectprinciple.test0702;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class JsonReader implements Reader {
    private String path;

    public JsonReader(String path) {
        this.path = path;
    }

    record CallHistoryRecord(List<CallRecord> calls) {
        record CallRecord(String from, String to, LocalDateTime start, LocalDateTime end) {}
    }

    public List<Call> read() {
        List<String> lines = readLines(path);
        return parse(lines);
    }

    private List<String> readLines(String path) {
        try {
            return Files.readAllLines(Path.of(ClassLoader.getSystemResource(path).toURI()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<Call> parse(List<String> lines) {
        CallHistoryRecord history = parseJson(lines);
        return history.calls().stream().map(call -> new Call(call.from, call.to, TimeInterval.of(call.start, call.end))).collect(Collectors.toList());
    }

    private CallHistoryRecord parseJson(List<String> lines) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            String json = lines.stream().collect(Collectors.joining());
            JsonNode node = mapper.readTree(json.getBytes());

            return mapper.treeToValue(node, CallHistoryRecord.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}