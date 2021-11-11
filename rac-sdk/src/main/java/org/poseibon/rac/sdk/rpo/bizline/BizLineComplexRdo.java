package org.poseibon.rac.sdk.rpo.bizline;

import org.poseibon.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 业务线传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class BizLineComplexRdo extends BaseComplexRdo {
    /**
     * 分级控制
     */
    private Integer decentralizedControl;
    /**
     * 分级管控英文名
     */
    private String decentralizedControlEnName;

    public Integer getDecentralizedControl() {
        return decentralizedControl;
    }

    public void setDecentralizedControl(Integer decentralizedControl) {
        this.decentralizedControl = decentralizedControl;
    }

    public String getDecentralizedControlEnName() {
        return decentralizedControlEnName;
    }

    public void setDecentralizedControlEnName(String decentralizedControlEnName) {
        this.decentralizedControlEnName = decentralizedControlEnName;
    }
}
