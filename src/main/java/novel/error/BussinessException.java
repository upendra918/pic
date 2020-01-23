package novel.error;

public class BussinessException extends Exception implements CommonException {
    private CommonException commonException;

    public BussinessException(CommonException commonException){
        super();
        this.commonException = commonException;
    }

    public BussinessException(CommonException commonException, String newMsg){
        super();
        commonException.setExceptionMsg(newMsg);
        this.commonException = commonException;
    }
    @Override
    public Integer getExceptionCode() {
        return this.commonException.getExceptionCode();
    }

    @Override
    public String getExceptionMsg() {
        return this.commonException.getExceptionMsg();
    }

    @Override
    public CommonException setExceptionMsg(String Msg) {
        this.commonException.setExceptionMsg(Msg);
        return this.commonException;
    }
}
