package org.poseibon.rac.rowauth.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限类型枚举
 *
 * @author qingchuan
 * @date 2020/12/11
 */
public enum DataAccessFieldEnum {
    ID("id", "id"),
    BIZ_LINE_ID("bizLineId", "biz_line_id"),
    DECENTRALIZED_CONTROL_ID("decentralizedControlId", "decentralized_control_id");

    DataAccessFieldEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * 类型
     */
    private String value;
    /**
     * 描述
     */
    private String text;

    /**
     * 枚举map
     */
    public static final Map<String, DataAccessFieldEnum> VALUE_TEXT = new HashMap<>();

    /**
     * 静态初始化
     */
    static {
        for (DataAccessFieldEnum item : values()) {
            VALUE_TEXT.put(item.getValue(), item);
        }
    }

    /**
     * 根据值获取对应的枚举信息
     *
     * @param value 枚举值
     * @return 枚举信息
     * @throws IllegalArgumentException 枚举值不存在抛出异常
     */
    public static DataAccessFieldEnum of(String value) {
        if (!VALUE_TEXT.containsKey(value)) {
            throw new IllegalArgumentException(String
                    .format("can't parse enum value %s, because it doesn't existed", value));
        }
        return VALUE_TEXT.get(value);
    }

    /**
     * 枚举值是否存在
     *
     * @param value 枚举值
     * @return true 存在 ，false 不存在
     */
    public static boolean hasEnum(String value) {
        return VALUE_TEXT.containsKey(value);
    }

    /**
     * 校验是否相等
     *
     * @param value 枚举值
     * @return 是否和自身相等
     */
    public boolean equals(String value) {
        if (!VALUE_TEXT.containsKey(value)) {
            throw new IllegalArgumentException(String
                    .format("can't parse enum value %s, because it doesn't existed", value));
        }
        return getValue().equals(value);
    }
}
