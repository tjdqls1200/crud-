package toyproject.juniorforum.exception;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException (int boardId) {
        super(String.format("board id [%d] not found", boardId));
    }
}
