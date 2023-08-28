package group.ict.sosservice.user.domain;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.user.exception.InvalidMemberException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Email {

    private static final Pattern EMAIL_REGEX = Pattern.compile(
        "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$");

    @Column(name = "email", nullable = false)
    private String value;

    public Email(final String value) {
        validateEmail(value);
        this.value = value;
    }

    private void validateEmail(final String email) {
        if (email.isBlank() || !isValidPattern(email)) {
            throw new InvalidMemberException(ErrorType.INVALID_MEMBER_EMAIL);
        }
    }

    private boolean isValidPattern(final String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }
}
