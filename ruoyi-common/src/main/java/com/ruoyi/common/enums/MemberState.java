package com.ruoyi.common.enums;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */
public enum MemberState {
    IN_ORGANIZATION(0L, "仍在组织"),
    LEFT_ORGANIZATION(1L, "离开组织");

    private final long code;
    private final String description;

    MemberState(long code, String description) {
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

