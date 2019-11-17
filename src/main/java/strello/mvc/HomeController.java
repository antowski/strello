package strello.mvc;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import strello.service.StrelloService;

import java.time.LocalDate;

@SuppressWarnings("WeakerAccess")
@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private StrelloService strelloService;

    @Autowired
    public void setService(StrelloService svc) {
        this.strelloService = svc;
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(method = RequestMethod.GET)
    public String getTasksByFilter(ModelMap model
            , @RequestParam(value = "assignee", required = false) String assignee
            , @RequestParam(value = "startDate", required = false) LocalDate startDate
            , @RequestParam(value = "endDate", required = false) LocalDate endDate) {

        model.addAttribute("tasks", strelloService.getFilteredTasks(assignee, startDate, endDate));
        model.addAttribute("assignees", strelloService.getUniqueAssignees());

        model.addAttribute("filterAssignee", assignee);
        model.addAttribute("filterStartDate", startDate);
        model.addAttribute("filterEndDate", endDate);

        // DateTool for date formatting in velocity templates
        // See *.vm for $date.format(...)
        model.addAttribute("date", new DateTool());

        return "home";
    }

}
