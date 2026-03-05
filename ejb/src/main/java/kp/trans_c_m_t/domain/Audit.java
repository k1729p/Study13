package kp.trans_c_m_t.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The entity class for the <b>audit</b>.
 */
@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int created;

    private int approved;

    /**
     * Gets the number of parcels with the status 'created'.
     *
     * @return the number of parcels with the status 'created'
     */
    public int getCreated() {
        return created;
    }

    /**
     * Sets the number of parcels with the status 'created'.
     *
     * @param created the number of parcels with the status 'created' to set
     */
    public void setCreated(int created) {
        this.created = created;
    }

    /**
     * Gets the number of parcels with the status 'approved'.
     *
     * @return the number of parcels with the status 'approved'
     */
    public int getApproved() {
        return approved;
    }

    /**
     * Sets the number of parcels with the status 'approved'.
     *
     * @param approved the number of parcels with the status 'approved' to set
     */
    public void setApproved(int approved) {
        this.approved = approved;
    }
}