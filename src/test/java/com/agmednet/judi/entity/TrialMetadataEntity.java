package com.agmednet.judi.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by pshynin on 1/11/17.
 */
@Entity
@Table(name = "trialmetadata", schema = "bonita")
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class TrialMetadataEntity {
    @XmlTransient
    @OneToOne(cascade =
            {})
    @JoinColumn(name = "trial_id")
    private TrialEntity trial;

    @Column(name = "name")
    private String name;

    @Column(name = "regex")
    private String regex;

    @Column(name = "sortorder")
    private int sortOrder;

    @Column(name = "displayname")
    private String displayname;

    @Column(name = "guidancetext")
    private String guidanceText;

    @Column(name = "isDate")
    private boolean isDate;

    @Column(name = "dateformat")
    private String dateFormat;

    @XmlTransient
    @OneToMany(cascade =
            {}, fetch = FetchType.LAZY, mappedBy = "trialMetadata")
    private List<EventMetadataEntity> eventMetadatas;
}
