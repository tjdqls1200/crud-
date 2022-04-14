package toyproject.juniorforum.exception;

public class ReplyNotFoundException extends RuntimeException {

    public ReplyNotFoundException(int replyId) {
        super(String.format("reply id [%d] not found", replyId));
    }
}
