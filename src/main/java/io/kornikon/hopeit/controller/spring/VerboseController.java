package io.kornikon.hopeit.controller.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
class VerboseController {

    private final ApplicationContext context;

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping("/beanlist")
    public List<String> loadedBeansDump() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        return Arrays.asList(beanNames);
    }


    @RequestMapping("/")
    public String getEndPointsInView(Model model) {
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream().map(rmi -> {
            return "<pre>"
                    + addEndpoint("    path: ", rmi.getPatternsCondition())
                    + addEndpoint("  method: ", rmi.getMethodsCondition())
                    + "</pre>";
        }).collect(Collectors.joining("<br>"));
    }

    private String addEndpoint(String s, Object o) {
        String str = o.toString();
        if (str.length() > 1) {
            str = str.substring(1, str.length() - 1);
        }
        return s + str + "<br>";
    }
}


