package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.poseibon.common.converter.Entity2RdoConverter;
import org.poseibon.common.page.Pagination;

/**
 * entity-rdo扩展转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public interface Entity2RdoExtConverter<F, T> extends Entity2RdoConverter<F, T> {

    /**
     * 分页dto
     *
     * @param pagination 分页对象
     * @return 分页dto
     */
    @Mappings({
            @Mapping(target = "dataList", expression = "java(toRdoList(pagination.getDataList()))")
    })
    PaginationRdo<T> toPaginationRdo(Pagination<F> pagination);
}
