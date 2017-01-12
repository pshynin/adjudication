package com.agmednet.judi.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pshynin on 1/11/17.
 */
@Entity
@Table(name = "trial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
@SuppressWarnings("serial")
public class TrialEntity {

    @Column(name = "name")
    private String trialName;

    @Column(name = "originalmetadata")
    private Boolean originalMetadata;


    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    @Column(name = "joincode")
    private String joinCode;

    @Column(name = "telerad")
    private boolean telerad = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "synchtimestamp")
    private Date synchTimestamp;

    @Column(name = "integrationtypeenum")
    private Long integrationTypeEnum;

    @Column(name = "redactioncertmessage")
    private String redactionCertMessage;

    @Column(name = "productbitmask")
    private Long productBitmask;

    @XmlTransient
    @OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "trial")
    private List<TrialSubjectEntity> trialSubjects = new ArrayList<TrialSubjectEntity>();

    @Column(name = "locked")
    private boolean locked;

    @XmlTransient
    @Column(name = "daconfigattachment_id")
    private Long daconfigAttachmentId;

    @XmlTransient
    @Column(name = "daformattachment_id")
    private Long daformAttachmentId;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trial")
    private List<EventTypeEntity> eventTypes;

    @Column(name = "requireadduserconfirmation")
    private boolean requireAddUserConfirmation;

    @Column(name = "blockformedits")
    private boolean blockformEdits;

    @Column(name = "hubspotid")
    private String hubspotId;

    @Column(name = "cloudenabled")
    private boolean cloudEnabled;

    @Column(name = "clouddomainname")
    private String cloudDomainName;

    @Column(name = "modalitiesintrial")
    private String modalitiesInTrial;

    @Column(name = "informonly")
    private boolean informOnly;

    @Column(name = "defaultnotifications")
    private Long defaultNotifications;

    @Column(name = "hasredactabledocuments")
    private boolean hasRedactableDocuments;

    @Column(name = "protocolconfirmationrequired")
    private boolean protocolConfirmationRequired;

    @Column(name = "protocolconfirmationtext")
    private String protocolConfirmationText;

    @Column(name = "subjectregex")
    private String subjectRegex;

    @Column(name = "subjectregexmessage")
    private String subjectRegexMessage;

    private Character delimiter;
}
