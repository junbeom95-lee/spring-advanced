package org.example.expert.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    //auth & user
    EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    NOT_FOUND_SIGNUP_USER(HttpStatus.BAD_REQUEST, "가입되지 않은 유저입니다."),
    MISS_MATCHES_PASSWORD(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호입니다."),
    INVALID_AUTH_PARAMETER(HttpStatus.BAD_REQUEST, "@Auth와 AuthUser 타입은 함께 사용되어야 합니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "User not found"),
    NOT_MATCH_OLD_PASSWORD_NEW_PASSWORD(HttpStatus.BAD_REQUEST, "새 비밀번호는 기존 비밀번호와 같을 수 없습니다."),
    NOT_FOUND_USER_ROLE(HttpStatus.BAD_REQUEST,"유효하지 않은 UerRole"),

    //manager
    MISS_MATCHES_MANAGER(HttpStatus.BAD_REQUEST,"일정을 생성한 유저만 담당자를 지정할 수 있습니다."),
    NOT_FOUND_MANAGER(HttpStatus.BAD_REQUEST,"Manager not found"),
    REGISTER_MANAGER_ME(HttpStatus.BAD_REQUEST, "일정 작성자는 본인을 담당자로 등록할 수 없습니다."),

    //jwt
    NOT_FOUND_TOKEN(HttpStatus.FORBIDDEN, "Not Found Token"),

    //weather
    EXTERNAL_API_FAIL(HttpStatus.BAD_GATEWAY, "외부 API 호출에 실패했습니다."),
    NOT_FOUND_WEATHER_DATA(HttpStatus.BAD_REQUEST, "날씨 데이터가 없습니다."),
    NOT_FOUND_TODAY_WEATHER(HttpStatus.BAD_REQUEST, "오늘에 해당하는 날씨 데이터를 찾을 수 없습니다."),

    //comment

    //todo
    NOT_FOUND_TODO(HttpStatus.BAD_REQUEST, "Todo not found"),
;
    private final HttpStatus status;
    private final String message;

    ExceptionCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
