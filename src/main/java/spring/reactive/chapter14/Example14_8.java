package spring.reactive.chapter14;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.util.stream.Stream;

@Slf4j
public class Example14_8 {
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("using_example.txt");
        Flux.using(() -> Files.lines(resource.getFile().toPath()), Flux::fromStream, Stream::close).subscribe(log::info);
    }
}
