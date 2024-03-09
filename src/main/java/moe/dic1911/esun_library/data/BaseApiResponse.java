package moe.dic1911.esun_library.data;

public class BaseApiResponse {
    private int status = 0;
    private String message = null;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
