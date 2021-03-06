package org.poseibon.rac.infrastructure.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.poseibon.common.page.Pagination;
import org.poseibon.rac.domain.entity.DimensionNodeEntity;
import org.poseibon.rac.domain.entity.UserEntity;
import org.poseibon.rac.domain.repository.UserRepository;
import org.poseibon.rac.infrastructure.converter.DimensionNodePo2EntityConverter;
import org.poseibon.rac.infrastructure.converter.UserEntity2PoConverter;
import org.poseibon.rac.infrastructure.converter.UserPo2EntityConverter;
import org.poseibon.rac.infrastructure.mapper.UserDimensionNodeMapper;
import org.poseibon.rac.infrastructure.mapper.UserMapper;
import org.poseibon.rac.infrastructure.mapper.UserRoleMapper;
import org.poseibon.rac.infrastructure.po.DimensionNodePo;
import org.poseibon.rac.infrastructure.po.UserPo;
import org.poseibon.rac.infrastructure.po.UserRolePo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户存储类
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Repository
public class UserMybatisRepository implements UserRepository {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserDimensionNodeMapper userDimensionNodeMapper;

    @Override
    public Pagination<UserEntity> listPage(Long currentUserId, Integer pageNo, Integer pageSize,
                                           Long bizLineId, String searchVal) {
        try (Page page = PageHelper.startPage(pageNo, pageSize)) {
            List<UserPo> poList = userMapper.listPage(bizLineId, currentUserId, searchVal);
            List<UserEntity> entityList = UserPo2EntityConverter.INSTANCE.toEntityList(poList);
            Pagination<UserEntity> pagination =
                    new Pagination(pageNo, pageSize, page.getTotal());
            pagination.setDataList(entityList);
            return pagination;
        }
    }

    @Override
    public List<Long> listUserRoleId(Long userId) {
        List<UserRolePo> userRolePoList = userRoleMapper.listUserRoleId(userId);
        return userRolePoList.stream().map(input -> input.getRoleId()).collect(Collectors.toList());
    }

    @Override
    public List<DimensionNodeEntity> listUserDimensionNodes(Long userId) {
        List<DimensionNodePo> dimensionNodePoList = userDimensionNodeMapper.listDimensionNodes(userId);
        return DimensionNodePo2EntityConverter.INSTANCE.toEntityList(dimensionNodePoList);
    }

    @Override
    public List<DimensionNodeEntity> listUserDimensionNodes(Long userId, Long bizLineId, Long dimensionId) {
        List<DimensionNodePo> dimensionNodePoList = userDimensionNodeMapper
                .listDimensionNodeByUser(userId, bizLineId, dimensionId);
        return DimensionNodePo2EntityConverter.INSTANCE.toEntityList(dimensionNodePoList);
    }


    @Override
    public UserEntity queryById(Long id) {
        UserPo record = userMapper.selectByPrimaryKey(id);
        return UserPo2EntityConverter.INSTANCE.toEntity(record);
    }

    @Override
    public Boolean hasSameEnName(String enName, Long id) {
        return userMapper.queryByEnName(enName, id) != null;
    }

    @Override
    public UserEntity queryByEnName(String enName) {
        UserPo record = userMapper.queryByEnName(enName, null);
        return UserPo2EntityConverter.INSTANCE.toEntity(record);
    }

    @Override
    public void bindRoles(Long currentUserId, Long userId, List<Long> roleIds) {
        userRoleMapper.deleteByUserId(userId);
        userRoleMapper.bindRoles(currentUserId, userId, roleIds);
    }


    @Override
    public void bindDimensionNodes(Long currentUserId, Long userId, List<Long> dimensionNodeIds) {
        userDimensionNodeMapper.deleteByUserId(userId);
        userDimensionNodeMapper.bindDimensionNodes(currentUserId, userId, dimensionNodeIds);
    }

    @Override
    public void insert(UserEntity record) {
        userMapper.insertSelective(UserEntity2PoConverter.INSTANCE.toPo(record));
    }

    @Override
    public void edit(UserEntity record) {
        userMapper.updateByPrimaryKeySelective(UserEntity2PoConverter.INSTANCE.toPo(record));
    }

    @Override
    public void delete(UserEntity record) {
        userMapper.updateByPrimaryKeySelective(UserEntity2PoConverter.INSTANCE.toPo(record));
    }

}
