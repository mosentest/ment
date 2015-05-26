package hemu.ment.support.entity;

import hemu.ment.core.entity.Enterprise;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by muu on 2015/5/25.
 */
@Entity
@Table(name = "t_support_ticket", schema = "ment_support")
public class SupportTicket implements Serializable {

    private static final long serialVersionUID = 5673275588737976025L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;


}
