package com.agmednet.judi.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Created by pshynin on 1/11/17.
 */
@Entity
@Table(name = "trialsite")
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class TrialSiteEntity {

    @Column(name = "clinicaltrialsiteid")
    private String clinicalTrialSiteId;

    @Formula("trim(LEADING '0' FROM clinicalTrialSiteId)")
    private String clinicalTrialSiteIdWithoutLeadingZeros;

    @Column(name = "clinicaltrialsitename")
    private String clinicalTrialSiteName;

    @Column(name = "enabled")
    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "synchtimestamp")
    private Date synchTimestamp;

    @Column(name = "telerad")
    private boolean telerad;

    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {CascadeType.ALL})
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne(fetch = FetchType.LAZY, cascade =
            {CascadeType.ALL})
    @JoinColumn(name = "trial_id")
    private TrialEntity trial;

    @Column(name = "defaultfortrial")
    private boolean defaultForTrial;

    @Column(name = "ctmdeleted")
    private boolean ctmDeleted;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "SUBJECTSITEXREF", joinColumns =
            {@JoinColumn(name = "site_id")}, inverseJoinColumns =
            {@JoinColumn(name = "subject_id")})
    private List<TrialSubjectEntity> trialSubjectList;

    public String getClinicalTrialSiteId() {
        return clinicalTrialSiteId;
    }

    public void setClinicalTrialSiteId(String clinicalTrialSiteId) {
        this.clinicalTrialSiteId = clinicalTrialSiteId;
    }

    public String getClinicalTrialSiteIdWithoutLeadingZeros() {
        return clinicalTrialSiteIdWithoutLeadingZeros;
    }

    public void setClinicalTrialSiteIdWithoutLeadingZeros(String clinicalTrialSiteIdWithoutLeadingZeros) {
        this.clinicalTrialSiteIdWithoutLeadingZeros = clinicalTrialSiteIdWithoutLeadingZeros;
    }

    public String getClinicalTrialSiteName() {
        return clinicalTrialSiteName;
    }

    public void setClinicalTrialSiteName(String clinicalTrialSiteName) {
        this.clinicalTrialSiteName = clinicalTrialSiteName;
    }
}
