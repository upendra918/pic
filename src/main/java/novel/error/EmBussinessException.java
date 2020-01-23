package novel.error;

public enum EmBussinessException implements CommonException {
    FILE_NOT_EXIST(10001, "the file does not exist"),
    UNKNOWN_ERROR(10002, "unkown error")
    ;
    private Integer exceptionCode;
    private String exceptionMsg;

    EmBussinessException(Integer exceptionCode, String exceptionMsg){
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public Integer getExceptionCode() {
        return this.exceptionCode;
    }

    @Override
    public String getExceptionMsg() {
        return this.exceptionMsg;
    }

    @Override
    public CommonException setExceptionMsg(String Msg) {
        this.exceptionMsg = Msg;
        return this;
    }


}