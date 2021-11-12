package org.poseibon.rac.rowauth.enums;

import org.poseibon.rac.rowauth.strategy.StrategyTypeParser;
import org.poseibon.rac.rowauth.strategy.vo.FixStrategyInfo;
import org.poseibon.rac.rowauth.strategy.vo.AllStrategyInfo;
import org.poseibon.rac.rowauth.strategy.vo.UserPropertyStrategyInfo;
import org.poseibon.common.constant.SeparationChar;
import org.poseibon.common.utils.Strings2;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 策略类型枚举
 *
 * @author qingchuan
 * @date 2020/12/11
 */
public enum StrategyExpressEnum {
    ALL(StrategyTypeEnum.ALL, "#All[]", expression -> new AllStrategyInfo()),
    USER_PROPERTY(StrategyTypeEnum.USER_PROPERTY,
            "#UserProperty\\[(\\w+?),(\\w+?),(\\w+?)\\[(\\w+?),(\\w+?)\\]\\]",
            expression -> {
                Pattern pattern = Pattern.compile("#UserProperty\\[(\\w+?),(\\w+?),(\\w+?)\\[(\\w+?),(\\w+?)\\]\\]");
                Matcher matcher = pattern.matcher(expression);
                if (matcher.find()) {
                    return new UserPropertyStrategyInfo().propertyType(matcher.group(1))
                            .propertyName(matcher.group(2)).entityEnName(matcher.group(3))
                            .entityPropertyType(matcher.group(4))
                            .entityPropertyName(matcher.group(5));
                }
                return new UserPropertyStrategyInfo();
            }),
    DIMENSION(StrategyTypeEnum.DIMENSION, "#Dimension\\[(\\w+?),(\\w+?)\\]",
            expression -> new AllStrategyInfo()),
    FIX(StrategyTypeEnum.FIX, "#Fix\\[(\\w+?)\\]", expression -> {
        Pattern pattern = Pattern.compile("#Fix\\[(\\w+?)\\]");
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            // 获取用户属性类型
            String values = matcher.group(1);
            // 扩展属性的话，从扩展属性数据中获取
            if (Strings2.isEmpty(values)) {
                return new FixStrategyInfo(Strings2.toStringList(values, SeparationChar.COMMA));
            }
        }
        return new FixStrategyInfo();
    });

    /**
     * 类型
     */
    private StrategyTypeEnum type;
    /**
     * 策略表达式前缀
     */
    private String pattern;
    /**
     * 解析器
     */
    private StrategyTypeParser parser;
    /**
     * 枚举map
     */
    public static final Map<Integer, StrategyExpressEnum> VALUE_TEXT = new HashMap<>();

    /**
     * 静态初始化
     */
    static {
        for (StrategyExpressEnum item : values()) {
            VALUE_TEXT.put(item.getType().getValue(), item);
        }
    }

    /**
     * 根据值获取对应的枚举信息
     *
     * @param value 枚举值
     * @return 枚举信息
     * @throws IllegalArgumentException 枚举值不存在抛出异常
     */
    public static StrategyExpressEnum of(Integer value) {
        if (!VALUE_TEXT.containsKey(value)) {
            throw new IllegalArgumentException(String
                    .format("can't parse enum value %s, because it doesn't existed", value));
        }
        return VALUE_TEXT.get(value);
    }

    StrategyExpressEnum(StrategyTypeEnum type, String pattern, StrategyTypeParser parser) {
        this.type = type;
        this.pattern = pattern;
        this.parser = parser;
    }

    public StrategyTypeEnum getType() {
        return type;
    }

    public String getPattern() {
        return pattern;
    }

    public StrategyTypeParser getParser() {
        return parser;
    }

    /**
     * 枚举值是否存在
     *
     * @param value 枚举值
     * @return true 存在 ，false 不存在
     */
    public static boolean hasEnum(Integer value) {
        return VALUE_TEXT.containsKey(value);
    }
}
