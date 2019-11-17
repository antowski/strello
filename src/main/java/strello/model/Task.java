package strello.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String assignee;
    private String summary;

    public Task() {
    }

    public Task(long id, LocalDate startDate, LocalDate endDate, String assignee, String summary) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    /*
     * important for tests
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        return (this.id == ((Task) obj).id);
    }

    /*
     * important for tests
     */
    @Override
    public int hashCode() {
        return java.lang.Math.toIntExact(this.id);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
