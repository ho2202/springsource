package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.SampleDTO;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/sample")
    public void getSample(Model model) {
        log.info("sample 페이지 요청");
        model.addAttribute("name", "hong");

        SampleDTO sampleDTO = SampleDTO.builder().id(1l).first("hong").last("dong").regDateTime(LocalDateTime.now())
                .build();
        model.addAttribute("dto", sampleDTO);

        List<SampleDTO> list = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            sampleDTO = SampleDTO.builder().id(i).first("hong" + i).last("dong" + i).regDateTime(LocalDateTime.now())
                    .build();
            list.add(sampleDTO);
        }
        model.addAttribute("list", list);
        model.addAttribute("title", "This is a text");
        model.addAttribute("now", new Date());
        model.addAttribute("price", 123000);
        model.addAttribute("option", Arrays.asList("AAA", "BBB", "CCC"));
    }

    @GetMapping("/ex1")
    public void getEx(String param1, int param2) {
        log.info("파라미터 확인 p1:{} p2:{}", param1, param2);
    }

    @GetMapping("/content")
    public void getContent() {
    }

}
