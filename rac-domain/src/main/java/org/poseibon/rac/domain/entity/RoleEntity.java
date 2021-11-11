package org.poseibon.rac.domain.entity;


import org.poseibon.rac.domain.entity.base.BaseEntity;

/**
 * 角色列表
 *
 * @author qingchuan
 * @date 2020/12/12
 */
public class RoleEntity extends BaseEntity {

    @Override
    public void validate() {
    }

    @Override
    public void create(Long currentUserId) {
        validate();
        completeForCreate(this, currentUserId);
    }

    @Override
    public void edit(Long currentUserId) {
        validate();
        completeForUpdate(this, currentUserId);
    }

    @Override
    public void delete(Long currentUserId) {
        validate();
        completeForDelete(this, currentUserId);
    }
}
