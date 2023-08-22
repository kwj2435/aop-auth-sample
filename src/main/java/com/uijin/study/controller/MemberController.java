package com.uijin.study.controller;

import com.uijin.study.annotation.ApiAuthentication;
import com.uijin.study.repository.ApiAuthGroupEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MemberController {

    @GetMapping
    @ApiAuthentication(apiId = "TEST")
    public void test() {
        System.out.printf("권한 인증 성공");
    }
}
