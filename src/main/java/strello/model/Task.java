package strello.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private long id;
    private Date startDate;
    private Date endDate;
    private String assignee;
    private String summary;

    public Task(){
    }

    public Task(long id, Date startDate, Date endDate, String assignee, String summary) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignee = assignee;
        this.summary = summary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
                , formatter.format(this.startDate)
                , formatter.format(this.endDate)
                , this.assignee
                , this.summary);
    }

}
