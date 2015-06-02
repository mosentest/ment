package hemu.ment.core.entity.settings;

import hemu.ment.core.entity.Enterprise;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by muu on 2015/5/25.
 */
@Entity
@Table(name = "t_tab_menu", schema = "ment_core")
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public class TabMenu implements Serializable {

    private static final long serialVersionUID = 1511088599099068676L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "priority")
    private int priority;

    @Column(name = "important")
    private boolean important;

    public TabMenu() {}

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
