import java.util.*;
import java.text.*;
public class Homework {
    String hw_subject = null;
    Date due_date;   
    String hw_description = null;
    boolean complete = false;
    public Homework(String subject, Date date, String description) {
        hw_subject = subject;
        due_date = date;
        hw_description = description;
        complete = false;
    }
    public void setSubject(String subject) {
        hw_subject = subject;
    }
    public String getSubject() {
        return hw_subject;
    }
    public void setDescription(String description) {
        hw_description = description;
    }
    public String getDescription() {
        return hw_description;
    }
    public void setDueDate(Date date) {
        due_date = date;
    }
    public Date getDueDate() {
        return due_date;
    }
    public void setComplete() {
        complete = true;
    }
    public boolean getComplete() {
        return complete;
    }
}