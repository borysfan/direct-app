/*
 * Copyright (C) 2010 - 2011 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.direct.services.view.dto.contest;

import java.util.List;
import java.util.Map;

import com.topcoder.clients.model.Project;
import com.topcoder.direct.services.view.dto.CommonDTO;
import com.topcoder.direct.services.view.form.ContestIdForm;
import com.topcoder.management.deliverable.Submission;
import com.topcoder.management.project.Prize;
import com.topcoder.management.resource.Resource;

/**
 * <p>
 * A <code>DTO</code> class providing the data for displaying by <code>Studio Contest Submissions</code> view.
 * </p>
 * 
 * <p>
 * Version 1.1 (Direct Submission Viewer Release 2) change notes:
 * <ul>
 * <li>Added {@link #prizeNumber} private field and getter/setter for it.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Version 1.2 (Direct Submission Viewer Release 3) change notes:
 * <ul>
 * <li>Added {@link #billingAccounts} private field and getter/setter for it.</li>
 * <li>Added {@link #prizes} private field and getter/setter for it.</li>
 * <li>Added {@link #hasCheckout} private field and getter/setter for it.</li>
 * <li>Added {@link #milestonePrize} private field and getter/setter for it.</li>
 * <li>Added {@link #additionalPrize} private field and getter/setter for it.</li>
 * <li>Added {@link #milestoneAwardNumber} private field and getter/setter for it.</li>
 * <li>Added {@link #paidSubmissions} private field and getter/setter for it.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Version 1.4 (Direct Submission Viewer Release 4) change notes:
 * <ul>
 * <li>Added {@link #milestoneRoundFeedbackText} private field and getter/setter for it.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Version 1.5 (TC Direct Release Assembly 7) change notes:
 * <ul>
 * <li>Added {@link #hasContestWritePermission} property with respective accessor/mutator methods.</li>
 * </ul>
 * </p>
 *
 * <p>Version 1.6 (TC Direct Contest Dashboard Update Assembly) change notes:
 * - change to extend from BaseContestCommonDTO.
 * - remove ContestStatsDTO and corresponding get/set methods.
 * </p>
 *
 * <p>
 *   Version 1.6 (TC Direct Replatforming Release 3) change notes:
 *   <ul>
 *     <li>Change the type of {@link #contestSubmissions} from <code>SubmissionData</code> to <code>Submission</code>.</li>
 *     <li>Added {@link #submissionFeedback} property with respective accessor/mutator methods.</li>
 *   </ul>
 * </p>
 * 
 * <p>
 *   Version 1.7 (TC Direct Replatforming Release 5) change notes:
 *   <ul>
 *     <li>Remove <code>billingAccounts</code>, <code>prizes</code>, <code>milestonePrize</code>, <code>additionalPrize</code>,
 *     <code>milestoneAwardNumber</code>, <code>paidSubmissions</code> and <code>submitterHandles</code> fields, also the getters/setters
 *     are removed.</li>
 *     <li>Added {@link #submissionResources} property with respective accessor/mutator methods.</li>
 *   </ul>
 * </p>
 *
 * @author isv, flexme, TCSDEVELOPER, TCSASSEMBLER
 * @since Submission Viewer Release 1 assembly
 * @version 1.7
 */
public class StudioContestSubmissionsDTO extends BaseContestCommonDTO implements ContestStatsDTO.Aware, ContestIdForm.Aware {

    /**
     * <p>
     * A <code>long</code> providing the ID of contest.
     * </p>
     */
    private long contestId;

    /**
     * <p>
     * A <code>List</code> listing the submissions for requested contest.
     * </p>
     */
    private List<Submission> contestSubmissions;

    /**
     * <p>
     * A <code>boolean</code> providing the flag indicating whether contest has milestone round set or not.
     * </p>
     */
    private boolean hasMilestoneRound;

    /**
     * <p>
     * An <code>int</code> providing the number of the prize slots.
     * </p>
     */
    private int prizeNumber;

    /**
     * <p>
     * A <code>boolean</code> providing the flag indicating whether the submissions have already been checked out.
     * </p>
     */
    private boolean hasCheckout;

    /**
     * <p>A <code>String</code> providing the text for milestone round overall feedback.</p>
     *
     * @since 1.4
     */
    private String milestoneRoundFeedbackText;
    
    /**
     * <p>
     * A boolean used to represent whether user has write permission to this
     * contest.
     * </p>
     *
     * @since 1.5
     */
    private boolean hasContestWritePermission;

    /**
     * <p>
     * A <code>Map</code> providing the client feedback for submissions.
     * </p>
     * 
     * @since 1.6
     */
    private Map<Long, String> submissionFeedback;

    /**
     * <p>
     * A <code>boolean</code> represents whether the corresponding phase is open. If the phase is scheduler, user can't do
     * any operation.
     * </p>
     * 
     * @since 1.6
     */
    private boolean phaseOpen;

    /**
     * Represents the <code>Resource</code> associated with the submissions. The key is the submission id, the value is the 
     * <code>Resource</code> associated with the submission.
     * 
     * @since 1.7
     */
    private Map<Long, Resource> submissionResources;


    /**
     * Represents the billing account associated with the current contest.
     */
    private Project billingAccount;


        /**
     * <p>
     * A <code>List</code> providing the prizes data.
     * </p>
     */
    private List<Prize> prizes;

    /**
     * <p>
     * A <code>double</code> providing the milestone prize.
     * </p>
     */
    private double milestonePrize;

    /**
     * <p>
     * A <code>double</code> providing the additional prize.
     * </p>
     */
    private double additionalPrize;

    /**
     * <p>
     * An <code>int</code> providing the number of milestone submissions which should award.
     * </p>
     */
    private int milestoneAwardNumber;
    
    /**
     * <p>
     * Constructs new <code>StudioContestSubmissionsDTO</code> instance. This implementation does nothing.
     * </p>
     */
    public StudioContestSubmissionsDTO() {
    }

    /**
     * <p>
     * Gets the ID of contest.
     * </p>
     * 
     * @return a <code>long</code> providing the ID of contest.
     */
    public long getContestId() {
        return this.contestId;
    }

    /**
     * <p>
     * Sets the ID of contest.
     * </p>
     * 
     * @param contestId
     *            a <code>long</code> providing the ID of contest.
     */
    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    /**
     * <p>
     * Gets the list of submissions for contest.
     * </p>
     * 
     * @return a <code>List</code> listing the submissions for requested contest.
     */
    public List<Submission> getContestSubmissions() {
        return contestSubmissions;
    }

    /**
     * <p>
     * Sets the list of submissions for contest.
     * </p>
     * 
     * @param contestSubmissions
     *            a <code>List</code> listing the submissions for requested contest.
     */
    public void setContestSubmissions(List<Submission> contestSubmissions) {
        this.contestSubmissions = contestSubmissions;
    }

    /**
     * <p>
     * Gets the flag indicating whether contest has milestone round set or not.
     * </p>
     * 
     * @return a <code>boolean</code> providing the flag indicating whether contest has milestone round set or not.
     */
    public boolean getHasMilestoneRound() {
        return this.hasMilestoneRound;
    }

    /**
     * <p>
     * Sets the flag indicating whether contest has milestone round set or not.
     * </p>
     * 
     * @param hasMilestoneRound
     *            a <code>boolean</code> providing the flag indicating whether contest has milestone round set or not.
     */
    public void setHasMilestoneRound(boolean hasMilestoneRound) {
        this.hasMilestoneRound = hasMilestoneRound;
    }

    /**
     * <p>
     * Gets the number of the prize slots.
     * </p>
     * 
     * @return An <code>int</code> providing the number of the prize slots
     * @since 1.1
     */
    public int getPrizeNumber() {
        return prizeNumber;
    }

    /**
     * <p>
     * Sets the number of the prize slots.
     * </p>
     * 
     * @param prizeNumber
     *            An <code>int</code> providing the number of the prize slots
     * @since 1.1
     */
    public void setPrizeNumber(int prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    /**
     * <p>
     * Gets the total number of submissions for requested contest.
     * </p>
     * 
     * @return an <code>int</code> providing the total number of submissions for requested contest.
     */
    public int getSubmissionsCount() {
        List<Submission> submissions = getContestSubmissions();
        if (submissions != null) {
            return submissions.size();
        } else {
            return 0;
        }
    }

    /**
     * Gets the flag indicating whether the submissions have already been checked out.
     * 
     * @return A <code>boolean</code> providing the flag indicating whether the submissions have already been checked
     *         out.
     * @since 1.2
     */
    public boolean getHasCheckout() {
        return hasCheckout;
    }

    /**
     * Sets the flag indicating whether the submissions have already been checked out.
     * 
     * @param hasCheckout
     *            A <code>boolean</code> providing the flag indicating whether the submissions have already been checked
     *            out.
     * @since 1.2
     */
    public void setHasCheckout(boolean hasCheckout) {
        this.hasCheckout = hasCheckout;
    }

    /**
     * <p>Gets the text for milestone round overall feedback.</p>
     *
     * @return a <code>String</code> providing the text for milestone round overall feedback.
     * @since 1.4
     */
    public String getMilestoneRoundFeedbackText() {
        return this.milestoneRoundFeedbackText;
    }

    /**
     * <p>Sets the text for milestone round overall feedback.</p>
     *
     * @param milestoneRoundFeedbackText a <code>String</code> providing the text for milestone round overall feedback.
     * @since 1.4
     */
    public void setMilestoneRoundFeedbackText(String milestoneRoundFeedbackText) {
        this.milestoneRoundFeedbackText = milestoneRoundFeedbackText;
    }

    /**
     * Get hasContestWritePermission field.
     * 
     * @return the hasContestWritePermission
     * @since 1.5
     */
    public boolean isHasContestWritePermission() {
        return hasContestWritePermission;
    }

    /**
     * Set hasContestWritePermission field.
     * 
     * @param hasContestWritePermission
     *            the hasContestWritePermission to set
     * @since 1.5
     */
    public void setHasContestWritePermission(boolean hasContestWritePermission) {
        this.hasContestWritePermission = hasContestWritePermission;
    }

    /**
     * Gets the client feedback for the submissions.
     * 
     * @return a <code>Map</code> providing the client feedback for submissions.
     * @since 1.6
     */
    public Map<Long, String> getSubmissionFeedback() {
        return submissionFeedback;
    }

    /**
     * Sets the client feedback for the submissions.
     * 
     * @param submissionFeedback a <code>Map</code> providing the client feedback for submissions.
     * @since 1.6
     */
    public void setSubmissionFeedback(Map<Long, String> submissionFeedback) {
        this.submissionFeedback = submissionFeedback;
    }

    /**
     * Gets whether the phase is open.
     *
     * @return true if the phase is open, false otherwise.
     * @since 1.6
     */
    public boolean isPhaseOpen() {
        return phaseOpen;
    }

    /**
     * Sets whether the phase is open.
     * 
     * @param phaseOpen true if the phase is open, false otherwise.
     * @since 1.6
     */
    public void setPhaseOpen(boolean phaseOpen) {
        this.phaseOpen = phaseOpen;
    }

    /**
     * Gets the <code>Resource</code> associated with the submissions.
     * 
     * @return the <code>Resource</code> associated with the submissions.
     * @since 1.7
     */
    public Map<Long, Resource> getSubmissionResources() {
        return submissionResources;
    }

    /**
     * Sets the <code>Resource</code> associated with the submissions.
     * 
     * @param submissionResources the <code>Resource</code> associated with the submissions.
     * @since 1.7
     */
    public void setSubmissionResources(Map<Long, Resource> submissionResources) {
        this.submissionResources = submissionResources;
    }

        /**
     * <p>
     * Gets the billing account.
     * </p>
     *
     * @return the billing account for requested contest.
     */
    public Project getBillingAccount() {
        return billingAccount;
    }

    /**
     * <p>
     * Sets the billing account for contest.
     * </p>
     *
     * @param billingAccount
     *            the billing account for requested contest.
     */
    public void setBillingAccount(Project billingAccount) {
        this.billingAccount = billingAccount;
    }

    /**
     * Gets the prizes data.
     *
     * @return A <code>List</code> providing the prizes data.
     * @since 1.2
     */
    public List<Prize> getPrizes() {
        return prizes;
    }

    /**
     * Sets the prizes data.
     *
     * @param prizes
     *            A <code>List</code> providing the prizes data.
     * @since 1.2
     */
    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    /**
     * Gets the milestone prize.
     *
     * @return A <code>double</code> providing the milestone prize.
     * @since 1.2
     */
    public double getMilestonePrize() {
        return milestonePrize;
    }

    /**
     * Sets the milestone prize.
     *
     * @param milestonePrize
     *            A <code>double</code> providing the milestone prize.
     * @since 1.2
     */
    public void setMilestonePrize(double milestonePrize) {
        this.milestonePrize = milestonePrize;
    }

    /**
     * Gets the additional prize.
     *
     * @return A <code>double</code> providing the additional prize.
     * @since 1.2
     */
    public double getAdditionalPrize() {
        return additionalPrize;
    }

    /**
     * Sets the additional prize.
     *
     * @param additionalPrize
     *            A <code>double</code> providing the additional prize.
     * @since 1.2
     */
    public void setAdditionalPrize(double additionalPrize) {
        this.additionalPrize = additionalPrize;
    }

    /**
     * Gets the number of milestone submissions which should award.
     *
     * @return An <code>int</code> providing the number of milestone submissions which should award.
     * @since 1.2
     */
    public int getMilestoneAwardNumber() {
        return milestoneAwardNumber;
    }

    /**
     * Sets the number of milestone submissions which should award.
     *
     * @param milestoneAwardNumber
     *            An <code>int</code> providing the number of milestone submissions which should award.
     * @since 1.2
     */
    public void setMilestoneAwardNumber(int milestoneAwardNumber) {
        this.milestoneAwardNumber = milestoneAwardNumber;
    }

}
