package strello.mvc;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import strello.service.StrelloService;

@Controller
public class HomeController {

    private StrelloService strelloService;

    @Autowired
    public void setService(StrelloService svc) {
        this.strelloService = svc;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(ModelMap model) {

        model.addAttribute("tasks", strelloService.getAllTasks());

        // DateTool for date formatting in velocity templates
        // See *.vm for $date.format(...)
        model.put("date", new DateTool());

        return "home";
    }

}
