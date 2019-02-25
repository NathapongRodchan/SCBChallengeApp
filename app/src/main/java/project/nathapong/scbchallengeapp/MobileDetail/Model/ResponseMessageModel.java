package project.nathapong.scbchallengeapp.MobileDetail.Model;

public class ResponseMessageModel {

    private String reason;
    private boolean error;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
