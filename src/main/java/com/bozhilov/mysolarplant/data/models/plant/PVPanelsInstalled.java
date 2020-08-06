package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="pv_panels_installed")
public class PVPanelsInstalled extends BaseEntity {
    private PVPanel pvPanel;
    private Integer panelsCount;
    private Double totalPower;

    @NotEmpty(message = Constants.PANELS_NON_EMPTY_MESSAGE)
    @ManyToOne
    @JoinColumn(name="panel_id", referencedColumnName = "id")
    public PVPanel getPvPanel() {
        return pvPanel;
    }

    public void setPvPanel(PVPanel pvPanel) {
        this.pvPanel = pvPanel;
    }

    @Min(value = 1,message = Constants.INVALID_PANELS_COUNT)
    public Integer getPanelsCount() {
        return panelsCount;
    }

    public void setPanelsCount(Integer panelsCount) {
        this.panelsCount = panelsCount;
    }

    public Double getTotalPower() {
        return pvPanel.getPower()*getPanelsCount();
    }

    public void setTotalPower(Double totalPower) {
        this.totalPower = totalPower;
    }
}
