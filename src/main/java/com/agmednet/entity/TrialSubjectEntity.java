package com.agmednet.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by pshynin on 1/11/17.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "trialsubject")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class TrialSubjectEntity extends BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "synchtimestamp")
    private Date synchTimestamp;

    @Column(name = "clinicaltrialsubjectid")
    private String clinicalTrialSubjectId;

    @Column(name = "clinicaltrialsubjectreadingid")
    private String clinicalTrialSubjectReadingId;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trial_id")
    private TrialEntity trial;

    @Column(name = "ctmdeleted")
    private boolean ctmDeleted;

    @Column(name = "enabled")
    private boolean enabled;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SUBJECTSITEXREF", joinColumns =
            {@JoinColumn(name = "subject_id")}, inverseJoinColumns =
            {@JoinColumn(name = "site_id")})
    private List<TrialSiteEntity> trialSiteList;
}
