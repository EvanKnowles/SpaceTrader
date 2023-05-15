
package za.co.knonchalant.space.domain;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Chart {

    @Expose
    private String submittedBy;
    @Expose
    private String submittedOn;

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(String submittedOn) {
        this.submittedOn = submittedOn;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "submittedBy='" + submittedBy + '\'' +
                ", submittedOn='" + submittedOn + '\'' +
                '}';
    }
}
