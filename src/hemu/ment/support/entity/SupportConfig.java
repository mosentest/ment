package hemu.ment.support.entity;

import hemu.ment.core.entity.Enterprise;
import hemu.ment.core.entity.settings.SettingsEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by muu on 2015/5/25.
 */
@Entity
@Table(name = "t_support_config", schema = "ment_core")
public class SupportConfig implements Serializable, SettingsEntity {

    private static final long serialVersionUID = 5045081332771172769L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public void setDefaultSettings(Properties properties) {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
