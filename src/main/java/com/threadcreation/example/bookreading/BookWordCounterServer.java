package com.threadcreation.example.bookreading;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookWordCounterServer {
    private static final String INPUT_FILE = "src/main/resources/war_and_peace.txt";
    private static final int NUMBER_OF_THREAD = 1;

    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
        startServer(text);
    }

    private static void startServer(String text) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/search", new WordCounterHandler(text));
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREAD);
        httpServer.setExecutor(executorService);
        httpServer.start();
    }

    private static class WordCounterHandler implements HttpHandler {
        private final String text;

        public WordCounterHandler(String text) {
            this.text = text;
        }
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            String[] keyValue = query.split("=");
            String action = keyValue[0];
            String word = keyValue[1];
            if (!action.equals("word")) {
                exchange.sendResponseHeaders(400, 0);
                return;
            }
            long count = countWord(word);
            byte[] response = Long.toString(count).getBytes();
            exchange.sendResponseHeaders(200, response.length);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response);
            responseBody.close();
        }

        private long countWord(String word) {
            long count = 0;
            int index = 0;
            while (index>=0) {
                index = text.indexOf(word, index);
                if (index >=0) {
                    index++;
                    count++;
                }
            }
            return count;
        }
    }
}
