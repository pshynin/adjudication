package com.agmednet.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pshynin on 1/6/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
@Entity
@javax.persistence.Table(name = "eventinstance", schema = "bonita")
public class EventInstanceEntity extends BaseEntity implements Serializable {
    @XmlTransient
    private static final long serialVersionUID = 1L;
    private Date completed;
    private Integer timesCompleted = 0;
    private Boolean inTransition = false;
    private Boolean adjudicatorsSelected = false;
    private Boolean committee = false;
    private Boolean disabled = false;
    private Boolean uploadsdisabled = false;
    private Boolean migrated = false;
    private Integer adjudicationPhase;
    private Integer reviewPhase;


    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trial_id")
    private TrialEntity trial;

    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trialsite_id")
    private TrialSiteEntity trialSite;

    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "trialsubject_id")
    private TrialSubjectEntity trialSubject;

    @XmlTransient
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "eventtype_id")
    private EventTypeEntity eventType;

    private String aeid;

    private String eventTerm;

    private Date onsetDate;

    private Date meetingDate;

    private Long completedProcessInstanceId;

    private String status;

    private String state;

    private boolean onHold;
}