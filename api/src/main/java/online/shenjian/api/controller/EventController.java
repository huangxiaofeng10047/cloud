package online.shenjian.api.controller;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {
    
    @Value("${api.key}")
    private String apiKey;
    
    @GetMapping(value = "/streamAsk", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamAsk(String q) throws Exception {
        
        Generation gen = new Generation();
        
        // 创建用户消息对象
        Message userMsg = Message
                .builder()
                .role(Role.USER.getValue())
                .content(q)
                .build();
        
        // 创建QwenParam对象，设置参数
        QwenParam param = QwenParam.builder()
                .model(Generation.Models.QWEN_PLUS)
                .messages(Arrays.asList(userMsg))
                .resultFormat(QwenParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .enableSearch(true)
                .apiKey(apiKey)
                // get streaming output incrementally
                .incrementalOutput(true)
                .build();
        
        // 调用生成接口，获取Flowable对象
        Flowable<GenerationResult> result = gen.streamCall(param);
        
        // 将Flowable转换成Flux<ServerSentEvent<String>>并进行处理
        return Flux.from(result)
                // add delay between each event
                .delayElements(Duration.ofMillis(1000))
                .map(message -> {
                    String output = message.getOutput().getChoices().get(0).getMessage().getContent();
                    System.out.println(output); // print the output
                    return ServerSentEvent.<String>builder()
                            .data(output)
                            .build();
                })
                .concatWith(Flux.just(ServerSentEvent.<String>builder().comment("").build()))
                .doOnError(e -> {
                    if (e instanceof NoApiKeyException) {
                        // 处理 NoApiKeyException
                    } else if (e instanceof InputRequiredException) {
                        // 处理 InputRequiredException
                    } else if (e instanceof ApiException) {
                        // 处理其他 ApiException
                    } else {
                        // 处理其他异常
                    }
                });
    }
    
    @GetMapping(value = "test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> testEventStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Event " + sequence + " at " + LocalTime.now());
    }
}
