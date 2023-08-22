package com.reactive.chapter15.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 오프라인 서점의 검색용 PC에서 들어오는 요청을 처리하는 Spring WebFlux 기반
 * 지점 API Server
 */
@Slf4j
@RequestMapping("/v1/books")
@RestController
public class SpringReactiveBranchOfficeController {
    private static final Map<Long, String> bookMap = new HashMap<>();

    static {
        bookMap.put(1L, "somthing1");
        bookMap.put(2L, "somthing2");
        bookMap.put(3L, "somthing3");
        bookMap.put(4L, "somthing4");
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<String> getBook(@PathVariable("book-id") long bookId)
            throws InterruptedException {
        Thread.sleep(2000);

        String book = bookMap.get(bookId);
        log.info("# book for response: {}", book);
        return Mono.just(book);
    }
}
