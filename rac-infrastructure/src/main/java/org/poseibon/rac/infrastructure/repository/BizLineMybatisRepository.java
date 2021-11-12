package org.poseibon.rac.infrastructure.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.poseibon.rac.domain.entity.BizLineEntity;
import org.poseibon.rac.domain.repository.BizLineRepository;
import org.poseibon.rac.infrastructure.converter.BizLineEntity2PoConverter;
import org.poseibon.rac.infrastructure.converter.BizLinePo2EntityConverter;
import org.poseibon.rac.infrastructure.mapper.BizLineMapper;
import org.poseibon.rac.infrastructure.po.BizLinePo;
import org.poseibon.common.page.Pagination;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务线存储类
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Repository
public class BizLineMybatisRepository implements BizLineRepository {
    @Resource
    private BizLineMapper bizLineMapper;

    @Override
    public Pagination<BizLineEntity> listPage(Integer pageNo, Integer pageSize, String searchVal) {
        try (Page page = PageHelper.startPage(pageNo, pageSize)) {
            List<BizLinePo> poList = bizLineMapper.listPage(searchVal);
            List<BizLineEntity> entityList = BizLinePo2EntityConverter.INSTANCE.toEntityList(poList);
            Pagination<BizLineEntity> pagination =
                    new Pagination(pageNo, pageSize, page.getTotal());
            pagination.setDataList(entityList);
            return pagination;
        }
    }

    @Override
    public List<BizLineEntity> listAuthBizLine() {
        return BizLinePo2EntityConverter.INSTANCE.toEntityList(bizLineMapper.listAuthBizLine());
    }

    @Override
    public BizLineEntity queryById(Long id) {
        BizLinePo record = bizLineMapper.selectByPrimaryKey(id);
        return BizLinePo2EntityConverter.INSTANCE.toEntity(record);
    }

    @Override
    public Boolean hasSameEnName(String enName, Long id) {
        return bizLineMapper.queryByEnName(enName, id) != null;
    }

    @Override
    public Boolean hasSameCnName(String cnName, Long id) {
        return bizLineMapper.queryByCnName(cnName, id) != null;
    }

    @Override
    public void insert(BizLineEntity record) {
        bizLineMapper.insertSelective(BizLineEntity2PoConverter.INSTANCE.toPo(record));
    }

    @Override
    public void edit(BizLineEntity record) {
        bizLineMapper.updateByPrimaryKeySelective(BizLineEntity2PoConverter.INSTANCE.toPo(record));
    }


    @Override
    public void delete(BizLineEntity record) {
        bizLineMapper.updateByPrimaryKeySelective(BizLineEntity2PoConverter.INSTANCE.toPo(record));
    }

    @Override
    public BizLineEntity queryByEnName(String enName) {
        BizLinePo bizLinePo = bizLineMapper.queryByEnName(enName, null);
        return BizLinePo2EntityConverter.INSTANCE.toEntity(bizLinePo);
    }
}
