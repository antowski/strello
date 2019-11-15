package strello.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private long id;
    private Date StartDate, EndDate;
    private String Assignee;
    private String Summary;

    public Task(long id, Date startDate, Date endDate, String assignee, String summary) {
        this.id = id;
        this.StartDate = startDate;
        this.EndDate = endDate;
        this.Assignee = assignee;
        this.Summary = summary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getAssignee() {
        return Assignee;
    }

    public void setAssignee(String assignee) {
        Assignee = assignee;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    @Override
    public String toString() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        return String.format(
                "Task{" +
                        "   id=%d" +
                        "   , StartDate=%s" +
                        "   , EndDate=%s" +
                        "   , Assignee=%s" +
                        "   , Summary=%s" +
                        "}"
                , this.id
                , formatter.format(this.StartDate)
                , formatter.format(this.EndDate)
                , this.Assignee
                , this.Summary);
    }

}
