package jonas.test.demo;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }
}
