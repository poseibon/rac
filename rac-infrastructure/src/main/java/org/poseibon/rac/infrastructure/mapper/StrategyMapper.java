package org.poseibon.rac.infrastructure.mapper;

import org.poseibon.rac.infrastructure.po.StrategyPo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.Collection;
import java.util.List;

/**
 * 访问策略dao层接口
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategyMapper extends StrategyBaseMapper {
    /**
     * 查找可用的数据列表
     *
     * @return 列表数据
     */
    @Select({
            "<script>",
            "select",
            "id, biz_line_id, en_name, cn_name, type, expression, priority, remark, create_time, ",
            "create_user_id, update_time, update_user_id, deleted",
            "from tb_strategy",
            "where deleted = 0 and biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "<if test='searchVal != null and searchVal != \"\"'> ",
            "and (en_name like concat(#{searchVal, jdbcType=VARCHAR},'%') ",
            "or cn_name like concat(#{searchVal, jdbcType=VARCHAR},'%')) ",
            "</if>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "biz_line_id", property = "bizLineId", jdbcType = JdbcType.BIGINT),
            @Result(column = "en_name", property = "enName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cn_name", property = "cnName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "expression", property = "expression", jdbcType = JdbcType.VARCHAR),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.INTEGER)
    })
    List<StrategyPo> listPage(@Param("bizLineId") Long bizLineId, @Param("searchVal") String searchVal);

    /**
     * 根据业务线查询
     *
     * @param bizLineId 业务线ID
     * @return 列表
     */
    @Select({
            "select",
            "id, biz_line_id, en_name, cn_name, type, expression, priority, remark, create_time, ",
            "create_user_id, update_time, update_user_id, deleted",
            "from tb_strategy",
            "where deleted = 0 and biz_line_id = #{bizLineId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "biz_line_id", property = "bizLineId", jdbcType = JdbcType.BIGINT),
            @Result(column = "en_name", property = "enName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cn_name", property = "cnName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "expression", property = "expression", jdbcType = JdbcType.VARCHAR),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.INTEGER)
    })
    List<StrategyPo> listByBizLineId(@Param("bizLineId") Long bizLineId);


    /**
     * 查询业务线下指定英文名的记录
     *
     * @param bizLineId 业务线ID
     * @param enName    英文名
     * @param id        ID
     * @return 列表
     */
    @Select({
            "<script>",
            "select",
            "id, biz_line_id, en_name, cn_name, type, expression, priority, remark, create_time, ",
            "create_user_id, update_time, update_user_id, deleted",
            "from tb_strategy",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "and en_name = #{enName,jdbcType=VARCHAR} and deleted = 0 ",
            "<if test='id != null'>",
            "and id != #{id,jdbcType=BIGINT}",
            "</if>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "biz_line_id", property = "bizLineId", jdbcType = JdbcType.BIGINT),
            @Result(column = "en_name", property = "enName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cn_name", property = "cnName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "expression", property = "expression", jdbcType = JdbcType.VARCHAR),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.INTEGER)
    })
    StrategyPo queryByEnName(@Param("bizLineId") Long bizLineId, @Param("enName") String enName, @Param("id") Long id);


    /**
     * 查询业务线下指定中文名的记录
     *
     * @param bizLineId 业务线ID
     * @param cnName    中文名
     * @param id        ID
     * @return 列表
     */
    @Select({
            "<script>",
            "select",
            "id, biz_line_id, en_name, cn_name, type, expression, priority, remark, create_time, ",
            "create_user_id, update_time, update_user_id, deleted",
            "from tb_strategy",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "and cn_name = #{cnName,jdbcType=VARCHAR} and deleted = 0 ",
            "<if test='id != null'>",
            "and id != #{id,jdbcType=BIGINT}",
            "</if>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "biz_line_id", property = "bizLineId", jdbcType = JdbcType.BIGINT),
            @Result(column = "en_name", property = "enName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cn_name", property = "cnName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "expression", property = "expression", jdbcType = JdbcType.VARCHAR),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.INTEGER)
    })
    StrategyPo queryByCnName(@Param("bizLineId") Long bizLineId, @Param("cnName") String cnName,
                             @Param("id") Long id);


    /**
     * 根据ID查询
     *
     * @param bizLineId 业务线ID
     * @param id        ID
     * @return 访问策略记录
     */
    @Select({
            "select",
            "id, biz_line_id, en_name, cn_name, type, expression, priority, remark, create_time, ",
            "create_user_id, update_time, update_user_id, deleted",
            "from tb_strategy",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT} and id = #{id,jdbcType=BIGINT} and deleted = 0"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "biz_line_id", property = "bizLineId", jdbcType = JdbcType.BIGINT),
            @Result(column = "en_name", property = "enName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cn_name", property = "cnName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "expression", property = "expression", jdbcType = JdbcType.VARCHAR),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.INTEGER)
    })
    StrategyPo queryById(@Param("bizLineId") Long bizLineId, @Param("id") Long id);

    /**
     * 根据ID查询
     *
     * @param bizLineId 业务线ID
     * @param idList    id列表
     * @return 策略列表
     */
    @Select({
            "<script>",
            "select",
            "id, biz_line_id, en_name, cn_name, type, expression, priority, remark, create_time, ",
            "create_user_id, update_time, update_user_id, deleted",
            "from tb_strategy",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT} and deleted = 0 ",
            "and id in (",
            "<foreach collection='idList' item='item' index='index' separator=','>",
            " #{item,jdbcType=BIGINT}",
            "</foreach>) ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "biz_line_id", property = "bizLineId", jdbcType = JdbcType.BIGINT),
            @Result(column = "en_name", property = "enName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "cn_name", property = "cnName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER),
            @Result(column = "expression", property = "expression", jdbcType = JdbcType.VARCHAR),
            @Result(column = "priority", property = "priority", jdbcType = JdbcType.INTEGER),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "deleted", property = "deleted", jdbcType = JdbcType.INTEGER)
    })
    List<StrategyPo> listByIds(@Param("bizLineId") Long bizLineId, @Param("idList") Collection<Long> idList);
}
