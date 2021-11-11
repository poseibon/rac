package org.poseibon.rac.rowauth.interceptor;


import com.mysql.cj.MysqlConnection;
import com.mysql.cj.Query;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.protocol.ServerSession;

import java.util.Properties;
import java.util.function.Supplier;

/**
 * 自定义拦截器-方法拦截器，基于spring aop
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class MysqlAuthInterceptor implements QueryInterceptor {
    @Override
    public QueryInterceptor init(MysqlConnection conn, Properties props, Log log) {
        return null;
    }

    @Override
    public <T extends Resultset> T preProcess(Supplier<String> sqlSupplier, Query interceptedQuery) {
        String sql = sqlSupplier.get();
        return null;
    }

    @Override
    public boolean executeTopLevelOnly() {
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public <T extends Resultset> T postProcess(Supplier<String> sql, Query interceptedQuery, T originalResultSet, ServerSession serverSession) {
        return null;
    }
}
