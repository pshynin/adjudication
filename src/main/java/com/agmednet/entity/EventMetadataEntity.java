package com.agmednet.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pshynin on 1/11/17.
 */
@Entity
@Table(name = "eventmetadata", schema = "bonita")
@SuppressWarnings("serial")
public class EventMetadataEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "trialmetadata_id", nullable = false)
    private TrialMetadataEntity trialMetadata;

    @Column(name = "value")
    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datevalue")
    private Date dateValue;

    @ManyToOne
    @JoinColumn(name = "eventinstance_id", nullable = false)
    private EventInstanceEntity eventInstance;
}
