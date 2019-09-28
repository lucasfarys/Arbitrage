package pl.coderslab.app.dto.bittrex;

public class DataBittrexDTO {
    private boolean success;
    private String message;
    private JsonBittrexResult result;
    public DataBittrexDTO() {
    }

    public JsonBittrexResult getResult() {
        return result;
    }

    public void setResult(JsonBittrexResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}