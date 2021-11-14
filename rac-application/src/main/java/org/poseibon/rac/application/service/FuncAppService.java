package org.poseibon.rac.application.service;

import org.poseibon.rac.application.converter.FuncEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.FuncSimpleRpo2EntityConverter;
import org.poseibon.rac.domain.entity.FuncEntity;
import org.poseibon.rac.domain.service.FuncDomainService;
import org.poseibon.rac.rowauth.annotation.ReadAuth;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.func.FuncComplexRdo;
import org.poseibon.rac.sdk.rpo.func.FuncSimpleRpo;
import org.poseibon.common.tree.TreeBuilder;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class FuncAppService {
    @Resource
    private FuncDomainService funcDomainService;


    /**
     * 查询子功能
     *
     * @param record 查询参数
     * @return 功能列表数据
     */
    @ReadAuth
    public List<FuncComplexRdo> listByParentId(FuncSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的功能列表
        List<FuncEntity> funcEntityList = funcDomainService.
                listByParentId(record.getBizLineId(), record.getParentId());
        return FuncEntity2ComplexRdoConverter.INSTANCE.toRdoList(funcEntityList);
    }


    /**
     * 查询子功能
     *
     * @param record 查询参数
     * @return 功能列表数据
     */
    @ReadAuth
    public List<FuncComplexRdo> listByBizLineId(FuncSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的功能列表
        List<FuncEntity> funcEntityList = funcDomainService.
                listByBizLineId(record.getBizLineId(), record.getSearchVal());
        List<FuncComplexRdo> funcComplexDtoList = FuncEntity2ComplexRdoConverter.INSTANCE.toRdoList(funcEntityList);
        return TreeBuilder.buildTree(funcComplexDtoList);
    }

    /**
     * 创建功能
     *
     * @param currentUserId 登录用户ID
     * @param record 功能实体
     */
    @WriteAuth
    public void create(Long currentUserId, FuncSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        funcDomainService.create(currentUserId, FuncSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新功能
     *
     * @param currentUserId 登录用户ID
     * @param record 功能实体
     */
    @WriteAuth
    public void edit(Long currentUserId, FuncSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        funcDomainService.edit(currentUserId, FuncSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除功能
     *
     * @param currentUserId 登录用户ID
     * @param record 记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, FuncSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        funcDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }

}
