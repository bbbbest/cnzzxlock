package cn.zzx.lock.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author fzh
 * @since 2017/10/3
 */
@Component("baseDao")
public class BaseDao extends NamedParameterJdbcDaoSupport {

    @Resource(name = "dataSource", type = DataSource.class)
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    protected void initTemplateConfig() {
        JdbcTemplate template = getJdbcTemplate();
        template.setLazyInit(true);
    }
}
