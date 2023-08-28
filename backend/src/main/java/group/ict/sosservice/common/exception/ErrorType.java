package group.ict.sosservice.common.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    NOT_FOUND_MEMBER(2001, "존재하지 않는 회원입니다."),
    INVALID_MEMBER_NAME(2002, "올바르지 않은 이름입니다."),
    INVALID_MEMBER_EMAIL(2003, "올바르지 않은 이메일 형식입니다.");

    private final int code;

    private final String message;

    ErrorType(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
