package com.agmednet.judi.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * Created by pshynin on 1/11/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
@Entity
@Table(name = "eventtype", schema = "bonita")
@SuppressWarnings("serial")
public class EventTypeEntity {
    private String name;
    private String description;

    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trial_id")
    private TrialEntity trial;

    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trialmetadataforform")
    private TrialMetadataEntity trialmetadataForForm;
}
