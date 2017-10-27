package io.kornikon.hopeit.controller.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
class BeanListController {

    private final ApplicationContext context;

    @RequestMapping("/beanlist")
    public List<String> loadedBeansDump() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        return Arrays.asList(beanNames);
    }
}


