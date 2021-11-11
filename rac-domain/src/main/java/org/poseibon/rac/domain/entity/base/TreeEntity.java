package org.poseibon.rac.domain.entity.base;

import org.poseibon.rac.domain.common.constant.SystemConstant;
import org.poseibon.rac.domain.common.validator.BizAssert;
import org.apache.commons.lang3.StringUtils;
import org.poseibon.common.constant.SeparationChar;
import org.poseibon.common.utils.Strings2;

import java.util.List;

/**
 * 树结构
 *
 * @author qingchuan
 * @date 2020/12/19
 */
public abstract class TreeEntity extends BaseEntity {
    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 路径path
     */
    private String parentPath;
    /**
     * 子节点数量
     */
    private Long childCount;
    /**
     * 是否有子节点
     */
    private Boolean leaf;
    /**
     * 是否有子节点
     */
    private Boolean hasChild;

    /**
     * 获取字典树节点的path
     *
     * @return 当前字典节点path
     */
    public String getPath() {
        return StringUtils.trimToEmpty(this.parentPath) + SeparationChar.SLASH + this.getId();
    }

    /**
     * 填充path
     *
     * @param parentNode 父节点
     */
    public void completePath(TreeEntity parentNode) {
        this.setParentId(parentNode == null ? SystemConstant.DEFAULT_ID_VALUE : parentNode.getId());
        this.setParentPath(parentNode == null ? SystemConstant.ROOT_PATH : parentNode.getPath());
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        BizAssert.PARAM_EMPTY_ERROR.notNull(parentId, "父节点ID");
        this.parentId = parentId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        BizAssert.PARAM_EMPTY_ERROR.notNull(parentId, "父节点路径");
        BizAssert.DATA_TOO_LONG_1000_ERROR.isTrue(parentPath.length() < SystemConstant.MIN_COLUMN_LENGTH, "父节点路径");
        this.parentPath = parentPath;
    }

    public Long getChildCount() {
        return childCount;
    }

    public void setChildCount(Long childCount) {
        this.hasChild = (childCount != null && childCount > 0);
        this.leaf = !hasChild;
        this.childCount = childCount;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public Integer getLevel() {
        return getPathIds().size();
    }

    public List<Long> getPathIds() {
        List<Long> pathIds = Strings2.toLongList(getPath(), SeparationChar.SLASH);
        if (pathIds.contains(SystemConstant.DEFAULT_ID_VALUE)) {
            pathIds.remove(SystemConstant.DEFAULT_ID_VALUE);
        }
        return pathIds;
    }
}
