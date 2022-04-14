package toyproject.juniorforum.exception;

import lombok.Getter;

@Getter
public class RestApiException extends RuntimeException {
    public RestApiException(String message) {
        super(message);
    }
}
