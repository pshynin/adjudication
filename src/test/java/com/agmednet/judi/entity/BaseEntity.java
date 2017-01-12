package com.agmednet.judi.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pshynin on 1/11/17.
 */

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "version")
    private Date version;

    @XmlTransient
    @Transient
    private boolean created;
}
