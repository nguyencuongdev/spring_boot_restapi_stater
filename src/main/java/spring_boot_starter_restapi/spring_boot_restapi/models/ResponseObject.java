package spring_boot_starter_restapi.spring_boot_restapi.models;

public class ResponseObject {
    private String message;
    private Object data;
    private String status;

    public ResponseObject() {
    }

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                '}';
    }
}
