package strello.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import strello.service.StrelloService;

@SuppressWarnings("WeakerAccess")
@Controller
@RequestMapping(value = "/newTask")
public class CreateTaskController {

    private StrelloService strelloService;

    @Autowired
    public void setService(StrelloService svc) {
        this.strelloService = svc;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(method = RequestMethod.GET)
    public String createNewTask(Model model) {
        return "new";
    }

}
