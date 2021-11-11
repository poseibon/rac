package org.poseibon.rac.application.service;

import org.poseibon.rac.application.converter.BizLineEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.BizLineEntity2SimpleRdoConverter;
import org.poseibon.rac.application.converter.BizLineSimpleRpo2EntityConverter;
import org.poseibon.rac.domain.entity.BizLineEntity;
import org.poseibon.rac.domain.service.BizLineDomainService;
import org.poseibon.rac.domain.service.UserDomainService;
import org.poseibon.rac.rowauth.annotation.ReadAuth;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.base.PaginationRpo;
import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.poseibon.rac.sdk.rdo.bizline.BizLineSimpleRdo;
import org.poseibon.rac.sdk.rpo.bizline.BizLineComplexRdo;
import org.poseibon.rac.sdk.rpo.bizline.BizLineSimpleRpo;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务线应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class BizLineAppService {
    @Resource
    private BizLineDomainService bizLineService;
    @Resource
    private UserDomainService userService;

    /**
     * 查询业务线列表数据
     *
     * @param record 分页查询参数
     * @return 业务线列表数据
     */
    @ReadAuth
    public PaginationRdo<BizLineComplexRdo> listPage(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的业务线列表
        Pagination<BizLineEntity> pagination = bizLineService.listPage(record.getPageNo(),
                record.getPageSize(), record.getSearchVal());
        return BizLineEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(pagination);
    }

    /**
     * 查询授权的业务线列表
     *
     * @return 业务线列表数据
     */
    @ReadAuth
    public List<BizLineSimpleRdo> listAuthBizLine() {
        return BizLineEntity2SimpleRdoConverter.INSTANCE.toRdoList(bizLineService.listAuthBizLine());
    }

    /**
     * 创建业务线
     *
     * @param currentUserId 登录用户ID
     * @param record        业务线实体
     */
    @WriteAuth
    public void create(Long currentUserId, BizLineSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        bizLineService.create(currentUserId, BizLineSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }



    /**
     * 更新业务线
     *
     * @param currentUserId 登录用户ID
     * @param record        业务线实体
     */
    @WriteAuth
    public void edit(Long currentUserId, BizLineSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        bizLineService.edit(currentUserId, BizLineSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除业务线
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, BizLineSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        bizLineService.delete(currentUserId, record.getId());
    }


}
