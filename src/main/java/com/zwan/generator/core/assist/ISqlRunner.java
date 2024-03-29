package com.zwan.generator.core.assist;


import com.zwan.generator.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author yuxiaobin
 * @since 2018/2/7
 */
public interface ISqlRunner {

    String INSERT = "com.baomidou.mybatisplus.core.mapper.SqlRunner.Insert";
    String DELETE = "com.baomidou.mybatisplus.core.mapper.SqlRunner.Delete";
    String UPDATE = "com.baomidou.mybatisplus.core.mapper.SqlRunner.Update";
    String SELECT_LIST = "com.baomidou.mybatisplus.core.mapper.SqlRunner.SelectList";
    String SELECT_OBJS = "com.baomidou.mybatisplus.core.mapper.SqlRunner.SelectObjs";
    String COUNT = "com.baomidou.mybatisplus.core.mapper.SqlRunner.Count";
    String SQL_SCRIPT = "${sql}";
    String SQL = "sql";

    boolean insert(String sql, Object... args);

    boolean delete(String sql, Object... args);

    boolean update(String sql, Object... args);

    List<Map<String, Object>> selectList(String sql, Object... args);

    List<Object> selectObjs(String sql, Object... args);

    Object selectObj(String sql, Object... args);

    int selectCount(String sql, Object... args);

    Map<String, Object> selectOne(String sql, Object... args);

    IPage<Map<String, Object>> selectPage(IPage<?> page, String sql, Object... args);
}
