package com.uijin.study.aop;

import com.uijin.study.annotation.ApiAuthentication;
import com.uijin.study.entity.ApiAuthGroupEntity;
import com.uijin.study.entity.ApiInfoEntity;
import com.uijin.study.repository.ApiAuthGroupEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class ApiAuthenticationAop {

    private final ApiAuthGroupEntityRepository apiAuthGroupEntityRepository;

    @Pointcut(value = "@annotation(com.uijin.study.annotation.ApiAuthentication)")
    public void apiAuthentication() {}

    @Before("apiAuthentication()")
    public void apiAuthenticationHandle(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String apiId = signature.getMethod().getAnnotation(ApiAuthentication.class).apiId();
        HttpServletRequest request =
            ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userRole = request.getHeader("X-USER-ROLE");
        if(!StringUtils.hasLength(userRole)) {
            throw new IllegalArgumentException("Not Exist User Role");
        }

        ApiAuthGroupEntity apiAuthGroupEntity = apiAuthGroupEntityRepository.findByName(userRole)
                .orElseThrow(() -> new IllegalArgumentException("Not Exist Api Group"));
        Optional<ApiInfoEntity> first = apiAuthGroupEntity.getApiInfoEntityList().stream()
                .filter(it -> apiId.equals(it.getApiId())).findFirst();
        if(first.isEmpty()) {
            throw new IllegalArgumentException("Invalid Authentication!");
        }
    }
}
