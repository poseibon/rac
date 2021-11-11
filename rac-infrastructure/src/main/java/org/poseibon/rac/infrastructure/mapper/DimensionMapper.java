package org.poseibon.rac.infrastructure.mapper;

import org.poseibon.common.auth.AuthInfo;
import org.poseibon.rac.infrastructure.po.DimensionPo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * 维度dao层接口
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionMapper extends DimensionBaseMapper {
    /**
     * 查找可用的数据列表
     *
     * @return 列表数据
     */
    @Select({
            "<script>",
            "select",
            "id, en_name, cn_name, biz_line_id, node_type_id, use_ext_property, remark, create_time, create_user_id, ",
            "update_time, update_user_id, deleted",
            "from tb_dimension",
            "where deleted = 0 and biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "<if test='searchVal != null and searchVal != \"\"'> ",
            "and (en_name like concat(#{searchVal, jdbcType=VARCHAR},'%') ",
            "or cn_name like concat(#{searchVal, jdbcType=VARCHAR},'%')) ",
            "</if>",
            "<if test='authInfo != null'>",
            "and ${authInfo.dbFieldName} in (",
            "<foreach item='item' collection='authInfo.authList' separator=','>",
            " #{item,jdbcType=BIGINT} ",
            " </foreach>)",
            "</if>",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="en_name", property="enName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cn_name", property="cnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="biz_line_id", property="bizLineId", jdbcType=JdbcType.BIGINT),
            @Result(column="node_type_id", property="nodeTypeId", jdbcType=JdbcType.BIGINT),
            @Result(column="use_ext_property", property="useExtProperty", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    List<DimensionPo> listPage(@Param("bizLineId") Long bizLineId,
                               @Param("searchVal") String searchVal,
                               @Param("authInfo") AuthInfo authInfo);


    /**
     * 根据业务线查询
     *
     * @param bizLineId 业务线ID
     * @return 列表
     */
    @Select({
            "<script>",
            "select",
            "id, en_name, cn_name, biz_line_id, node_type_id, use_ext_property, remark, create_time, create_user_id, ",
            "update_time, update_user_id, deleted",
            "from tb_dimension",
            "where deleted = 0 and biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "<if test='authInfo != null'>",
            "and ${authInfo.dbFieldName} in (",
            "<foreach item='item' collection='authInfo.authList' separator=','>",
            " #{item,jdbcType=BIGINT} ",
            " </foreach>)",
            "</if>",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="en_name", property="enName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cn_name", property="cnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="biz_line_id", property="bizLineId", jdbcType=JdbcType.BIGINT),
            @Result(column="node_type_id", property="nodeTypeId", jdbcType=JdbcType.BIGINT),
            @Result(column="use_ext_property", property="useExtProperty", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    List<DimensionPo> listByBizLineId(@Param("bizLineId") Long bizLineId,
                                      @Param("authInfo") AuthInfo authInfo);

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
            "id, en_name, cn_name, biz_line_id, node_type_id, use_ext_property, remark, create_time, create_user_id, ",
            "update_time, update_user_id, deleted",
            "from tb_dimension",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "and en_name = #{enName,jdbcType=VARCHAR} and deleted=0",
            "<if test='id != null'>",
            "and id != #{id,jdbcType=BIGINT}",
            "</if>",
            "</script>"

    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="en_name", property="enName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cn_name", property="cnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="biz_line_id", property="bizLineId", jdbcType=JdbcType.BIGINT),
            @Result(column="node_type_id", property="nodeTypeId", jdbcType=JdbcType.BIGINT),
            @Result(column="use_ext_property", property="useExtProperty", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    DimensionPo queryByEnName(@Param("bizLineId") Long bizLineId,
                              @Param("enName") String enName, @Param("id") Long id);

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
            "id, en_name, cn_name, biz_line_id, node_type_id, use_ext_property, remark, create_time, create_user_id, ",
            "update_time, update_user_id, deleted",
            "from tb_dimension",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "and cn_name = #{cnName,jdbcType=VARCHAR} and deleted=0",
            "<if test='id != null'>",
            "and id != #{id,jdbcType=BIGINT}",
            "</if>",
            "</script>"

    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="en_name", property="enName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cn_name", property="cnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="biz_line_id", property="bizLineId", jdbcType=JdbcType.BIGINT),
            @Result(column="node_type_id", property="nodeTypeId", jdbcType=JdbcType.BIGINT),
            @Result(column="use_ext_property", property="useExtProperty", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    DimensionPo queryByCnName(@Param("bizLineId") Long bizLineId,
                              @Param("cnName") String cnName, @Param("id") Long id);


    /**
     * 根据ID查询
     *
     * @param bizLineId 业务线ID
     * @param id        ID
     * @return 维度记录
     */
    @Select({
            "select",
            "id, en_name, cn_name, biz_line_id, node_type_id, use_ext_property, remark, create_time, create_user_id, ",
            "update_time, update_user_id, deleted",
            "from tb_dimension",
            "where biz_line_id = #{bizLineId,jdbcType=BIGINT}",
            "and id = #{id,jdbcType=BIGINT} and deleted=0"

    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="en_name", property="enName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cn_name", property="cnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="biz_line_id", property="bizLineId", jdbcType=JdbcType.BIGINT),
            @Result(column="node_type_id", property="nodeTypeId", jdbcType=JdbcType.BIGINT),
            @Result(column="use_ext_property", property="useExtProperty", jdbcType=JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    DimensionPo queryById(@Param("bizLineId") Long bizLineId, @Param("id") Long id);
}
