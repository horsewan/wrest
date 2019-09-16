package com.zwan.generator.core.toolkit;


import com.zwan.generator.core.exceptions.MybatisPlusException;

/**
 * 异常辅助工具类
 *
 * @author HCL
 * @since 2018-07-24
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * 返回一个新的异常，统一构建，方便统一处理
     *
     * @param msg 消息
     * @param t   异常信息
     * @return 返回异常
     */
    public static MybatisPlusException mpe(String msg, Throwable t, Object... params) {
        return new MybatisPlusException(StringUtils.format(msg, params), t);
    }

    /**
     * 重载的方法
     *
     * @param msg 消息
     * @return 返回异常
     */
    public static MybatisPlusException mpe(String msg, Object... params) {
        return new MybatisPlusException(StringUtils.format(msg, params));
    }

    /**
     * 重载的方法
     *
     * @param t 异常
     * @return 返回异常
     */
    public static MybatisPlusException mpe(Throwable t) {
        return new MybatisPlusException(t);
    }

}
