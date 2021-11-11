package org.poseibon.rac.application.service;

import org.poseibon.rac.application.converter.DictionaryEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.DictionaryEntity2SimpleRdoConverter;
import org.poseibon.rac.application.converter.DictionarySimpleRpo2EntityConverter;
import org.poseibon.rac.domain.entity.DictionaryEntity;
import org.poseibon.rac.domain.service.DictionaryDomainService;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.poseibon.rac.sdk.rdo.base.PaginationRpo;
import org.poseibon.rac.sdk.rdo.dictionary.DictionarySimpleRdo;
import org.poseibon.rac.sdk.rdo.dictionary.DictionaryComplexRdo;
import org.poseibon.rac.sdk.rpo.dictionary.DictionarySimpleRpo;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class DictionaryAppService {
    @Resource
    private DictionaryDomainService dictionaryDomainService;

    /**
     * 查询字典列表数据
     *
     * @param record 分页查询参数
     * @return 字典列表数据
     */
    public PaginationRdo<DictionaryComplexRdo> listPage(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的字典列表
        Pagination<DictionaryEntity> pagination = dictionaryDomainService.listPage(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getSearchVal());
        return DictionaryEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(pagination);
    }

    /**
     * 查询授权的字典列表
     *
     * @return 字典列表数据
     */
    public List<DictionarySimpleRdo> listByBizLineId(DictionarySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return DictionaryEntity2SimpleRdoConverter.INSTANCE.toRdoList(dictionaryDomainService
                .listByBizLineId(record.getBizLineId()));
    }

    /**
     * 创建字典
     *
     * @param currentUserId 登录用户ID
     * @param record 字典实体
     */
    @WriteAuth
    public void create(Long currentUserId, DictionarySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dictionaryDomainService.create(currentUserId, DictionarySimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新字典
     *
     * @param currentUserId 登录用户ID
     * @param record 字典实体
     */
    @WriteAuth
    public void edit(Long currentUserId, DictionarySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dictionaryDomainService.edit(currentUserId, DictionarySimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除字典
     *
     * @param currentUserId 登录用户ID
     * @param record 记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, DictionarySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dictionaryDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }


    /**
     * 根据英文名查询字典信息
     *
     * @param record 记录
     * @return 字典信息
     */
    public DictionaryComplexRdo queryByEnName(DictionarySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return DictionaryEntity2ComplexRdoConverter.INSTANCE.toRdo(dictionaryDomainService
                .queryByEnName(record.getBizLineId(), record.getEnName()));
    }
}
