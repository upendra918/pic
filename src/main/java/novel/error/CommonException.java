package novel.error;

public interface CommonException {
    Integer getExceptionCode();
    String getExceptionMsg();
    CommonException setExceptionMsg(String Msg);
}
