package dev.hdprojects.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    private HttpParser httpParser;

    @BeforeAll
    public void beforeClass() {
        //httpParser = new HttpParser(null);
    }

    @Test
    void parseHttpRequest() throws IOException {
        //httpParser.parseHttpRequest(generateValidTestCase());
    }

    private InputStream generateValidTestCase() {
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.30 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-US,en;q=0.9\r\n" +
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
}