package com.ruoyi.common.enums;

/**
 * <p>
 * 组织成员类型
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */
public enum MemberType {
    MEMBER(0L, "成员"),
    STUDENT(1L, "学员");

    private final long code;
    private final String description;

    MemberType(long code, String description) {
        this.code = code;
        this.description = description;
    }

    public long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


}
