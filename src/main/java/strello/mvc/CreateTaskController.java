package strello.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import strello.model.Task;
import strello.service.StrelloService;

import javax.validation.Valid;

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
        model.addAttribute("task", new Task());
        return "taskEdit";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String saveTaskFromForm(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "taskEdit";
        }

        strelloService.saveTask(task);

        return "redirect:/home";

    }

}
