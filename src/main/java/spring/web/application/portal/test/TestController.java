package spring.web.application.portal.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test-rest/")
public class TestController {
    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public RespModel test(@QueryParam("text") String text) {
        List<String> items = new ArrayList<>();

        items.add("item1");
        items.add("item2");

        return new RespModel(text, items);
    }
}
