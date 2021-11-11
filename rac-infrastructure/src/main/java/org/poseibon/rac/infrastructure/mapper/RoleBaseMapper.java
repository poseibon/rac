package org.poseibon.rac.infrastructure.mapper;

import org.poseibon.rac.infrastructure.po.RolePo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoleBaseMapper {
    @Delete({
        "delete from tb_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into tb_role (en_name, cn_name, ",
        "biz_line_id, remark, ",
        "create_time, create_user_id, ",
        "update_time, update_user_id, ",
        "deleted)",
        "values (#{enName,jdbcType=VARCHAR}, #{cnName,jdbcType=VARCHAR}, ",
        "#{bizLineId,jdbcType=BIGINT}, #{remark,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{createUserId,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{updateUserId,jdbcType=BIGINT}, ",
        "#{deleted,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(RolePo record);

    @InsertProvider(type=RoleSqlSupporter.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(RolePo record);

    @Select({
        "select",
        "id, en_name, cn_name, biz_line_id, remark, create_time, create_user_id, update_time, ",
        "update_user_id, deleted",
        "from tb_role",
        "where id = #{id,jdbcType=BIGINT} and deleted = 0"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="en_name", property="enName", jdbcType=JdbcType.VARCHAR),
        @Result(column="cn_name", property="cnName", jdbcType=JdbcType.VARCHAR),
        @Result(column="biz_line_id", property="bizLineId", jdbcType=JdbcType.BIGINT),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    RolePo selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleSqlSupporter.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RolePo record);

    @Update({
        "update tb_role",
        "set en_name = #{enName,jdbcType=VARCHAR},",
          "cn_name = #{cnName,jdbcType=VARCHAR},",
          "biz_line_id = #{bizLineId,jdbcType=BIGINT},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "update_user_id = #{updateUserId,jdbcType=BIGINT},",
          "deleted = #{deleted,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(RolePo record);
}