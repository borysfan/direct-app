/*
 * Copyright (C) 2010 - 2011 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.direct.services.configs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;

/**
 * <p>
 * Utils class for handling various configuration files.
 * </p>
 * <p>
 * Version 1.1 - TC Direct - Software Contest Creation Update Assembly 1.0 change notes:
 * - Add the logic to load copilot fees from the configuration file copilotFeeds.xml
 * </p>
 *
 * <p>
 * Version 1.1 - TC Direct Replatforming Release 1 Change note
 * <ul>
 * <li>Remove studioContestTypes filed and getter method for it.</li>
 * <li>Update {@link #init()} method to don't initialize studioContestTypes from files.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Version 1.2 - TC Cockpit Bug Tracking R1 Contest Tracking Assembly 1.0 change notes:
 * - Add the logic to load issue tracking configs from configuration file IssueTrackingConfig.xml
 * </p>
 * 
 * @author BeBetter, Veve
 * @version 1.2
 */
public final class ConfigUtils {
    /**
     * <p>
     * Overview object to hold studio overview information.
     * </P>
     */
    private static Overview overview;

    /**
     * <p>
     * studio subtype overview information.
     * </p>
     */
    private static List<StudioSubtypeOverview> studioOverviews;

    /**
     * <p>
     * Contest fees.
     * </p>
     */
    private static ContestFees contestFees;

    /**
     * <p>
     * Studio subtype contest fees.
     * </p>
     */
    private static List<StudioSubtypeContestFee> studioSubtypeContestFees;

    /**
     * <p>
     * File types.
     * </p>
     */
    private static FileTypes fileTypes;

    /**
     * <p>
     * Software contest fees.
     * </p>
     */
    private static Map<String, ContestFee> softwareContestFees;

    /**
     * <p>
     * Copilot fees.
     * </p>
     *
     * @since 1.1
     */
    private static Map<String, CopilotFee> copilotFees;

    /**
     * <p>
     * Jira Issue Tracking configuration.
     * </p>
     *
     * @since 1.2
     */
    private static IssueTrackingConfig issueTrackingConfig;


    static {
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     * Private ctor. Does nothing.
     * </p>
     */
    private ConfigUtils() {
        // do nothing
    }

    /**
     * <p>
     * Initialize the configuration objects.
     * </p>
     * <p> version 1.1 changes - add load of copilot fees</p>
     * <p> version 1.2 changes - add load of issue tracking configuration</p>
     */
    private static void init() throws Exception {
        JAXBContext overviewJaxbContext = JAXBContext.newInstance(Overview.class);
        overview = (Overview) overviewJaxbContext.createUnmarshaller().unmarshal(
                ConfigUtils.class.getResourceAsStream("/overview.xml"));

        for (ContestOverview contestOverview : overview.getContestOverviews()) {
            if ("STUDIO".equals(contestOverview.getId())) {
                studioOverviews = contestOverview.getStudioSubtypeOverviews();
            }
        }

        if (studioOverviews == null || studioOverviews.size() == 0) {
            throw new RuntimeException("no studio overview is defined in overview.xml");
        }

        JAXBContext contestFeesJaxbContext = JAXBContext.newInstance(ContestFees.class);
        contestFees = (ContestFees) contestFeesJaxbContext.createUnmarshaller().unmarshal(
                ConfigUtils.class.getResourceAsStream("/contestFees.xml"));

        softwareContestFees = new HashMap<String, ContestFee>();
        for (ContestFee contestFee : contestFees.getContestFees()) {
            if (contestFee.isStudioFee()) {
                studioSubtypeContestFees = contestFee.getStudioSubtypeContestFees();
            } else {
                softwareContestFees.put(contestFee.getContestTypeId() + "", contestFee);
            }
        }

        if (studioSubtypeContestFees == null || studioSubtypeContestFees.size() == 0) {
            throw new RuntimeException("no studio subtype contest fee is defined in contestFees.xml");
        }

        JAXBContext fileTypesJaxbContext = JAXBContext.newInstance(FileTypes.class);
        fileTypes = (FileTypes) fileTypesJaxbContext.createUnmarshaller().unmarshal(
                ConfigUtils.class.getResourceAsStream("/fileTypes.xml"));

        // load the copilot fees from the configuration copilotFees.xml
        JAXBContext copilotFeesJaxbContext = JAXBContext.newInstance(CopilotFees.class);
        CopilotFees parsedFees = (CopilotFees) copilotFeesJaxbContext.createUnmarshaller().unmarshal(
                ConfigUtils.class.getResourceAsStream("/copilotFees.xml"));
        copilotFees = new HashMap<String, CopilotFee>();
        // put copilot fee into the map
        for(CopilotFee copilotFee : parsedFees.getCopilotFees()) {
            copilotFees.put(String.valueOf(copilotFee.getContestTypeId()), copilotFee);
        }

        // load Jira issue tracking configuration
        JAXBContext issueTrackingJaxbContext = JAXBContext.newInstance(IssueTrackingConfig.class);
        issueTrackingConfig = (IssueTrackingConfig) issueTrackingJaxbContext.createUnmarshaller().unmarshal(
                ConfigUtils.class.getResourceAsStream("/IssueTrackingConfig.xml"));
    }

    /**
     * <p>
     * Gets overview information.
     * </p>
     *
     * @return overview
     */
    public static Overview getOverview() {
        return overview;
    }

    /**
     * <p>
     * Get studio overviews.
     * </p>
     *
     * @return studio overviews
     */
    public static List<StudioSubtypeOverview> getStudioOverviews() {
        return studioOverviews;
    }

    /**
     * <p>
     * Get studio overview.
     * </p>
     *
     * @return studio subtype overview
     */
    public static StudioSubtypeOverview getStudioOverview(long contestTypeId) {
        for (StudioSubtypeOverview overview : getStudioOverviews()) {
            if (overview.getId() == contestTypeId) {
                return overview;
            }
        }

        return null;
    }

    /**
     * <p>
     * Gets contest fees.
     * </p>
     *
     * @return contest fees
     */
    public static ContestFees getContestFees() {
        return contestFees;
    }

    /**
     * <p>
     * Gets studio subtype contest fees.
     * </p>
     *
     * @return studio subtype contest fees
     */
    public static List<StudioSubtypeContestFee> getStudioContestFees() {
        return studioSubtypeContestFees;
    }

    /**
     * <p>
     * Gets file types.
     * </p>
     *
     * @return file types
     */
    public static FileTypes getFileTypes() {
        return fileTypes;
    }

    /**
     * <p>
     * Gets software contest fees.
     * </p>
     *
     * @return software contest fees
     */
    public static Map<String, ContestFee> getSoftwareContestFees() {
        return softwareContestFees;
    }

    /**
     * <p>
     * Gets copilot fees.
     * </p>
     *
     * @return the copilot fees.
     * @since 1.1
     */
    public static Map<String, CopilotFee> getCopilotFees() {
        return copilotFees;
    }

    /**
     * <p>
     * Gets the configuration for jira issue tracking.
     * </p>
     *
     * @retrn the configuration of jira issue tracking.
     * @since 1.2
     */
    public static IssueTrackingConfig getIssueTrackingConfig() {
        return issueTrackingConfig;
    }
}
