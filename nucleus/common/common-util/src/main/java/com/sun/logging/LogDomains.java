/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 * Copyright (c) 2006, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.logging;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static java.util.logging.Level.CONFIG;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.WARNING;

/**
 * Class LogDomains
 */
public class LogDomains {

    /**
     * DOMAIN_ROOT the prefix for the logger name. This is public only
     * so it can be accessed w/in the ias package space.
     */
    public static final String DOMAIN_ROOT = "jakarta.";

    /**
     * Upgrade logger name.
     */
    public static final String UPGRADE_LOGGER = "upgradeLogger";

    /**
     * PACKAGE_ROOT the prefix for the packages where logger resource
     * bundles reside. This is public only so it can be accessed w/in
     * the ias package space.
     */
    public static final String PACKAGE_ROOT = "com.sun.logging.";

    /**
     * RESOURCE_BUNDLE the name of the logging resource bundles.
     */
    public static final String RESOURCE_BUNDLE = "LogStrings";

    /**
     * Field
     */
    public static final String STD_LOGGER = DOMAIN_ROOT + "enterprise.system.std";

    /**
     * Field
     */
    public static final String TOOLS_LOGGER = DOMAIN_ROOT + "enterprise.system.tools";

    /**
     * Field
     */
    public static final String EJB_LOGGER = DOMAIN_ROOT + "enterprise.system.container.ejb";

    /**
     * JavaMail Logger
     */
    public static final String JAVAMAIL_LOGGER = DOMAIN_ROOT + "enterprise.resource.javamail";

    /**
     * IIOP Logger
     public static final String IIOP_LOGGER = DOMAIN_ROOT + "enterprise.resource.iiop";
     */


    /**
     * JMS Logger
     */
    public static final String JMS_LOGGER = DOMAIN_ROOT + "enterprise.resource.jms";

    /**
     * Field
     */
    public static final String WEB_LOGGER = DOMAIN_ROOT + "enterprise.system.container.web";

    /**
     * Field
     */
    public static final String CMP_LOGGER = DOMAIN_ROOT + "enterprise.system.container.cmp";

    /**
     * Field
     */
    public static final String JDO_LOGGER = DOMAIN_ROOT + "enterprise.resource.jdo";

    /**
     * Field
     */
    public static final String ACC_LOGGER = DOMAIN_ROOT + "enterprise.system.container.appclient";

    /**
     * Field
     */
    public static final String MDB_LOGGER = DOMAIN_ROOT + "enterprise.system.container.ejb.mdb";

    /**
     * Field
     */
    public static final String SECURITY_LOGGER = DOMAIN_ROOT + "enterprise.system.core.security";

    /**
     * Field
     */
    public static final String SECURITY_SSL_LOGGER = DOMAIN_ROOT + "enterprise.system.ssl.security";

    /**
     * Field
     */
    public static final String TRANSACTION_LOGGER = DOMAIN_ROOT + "enterprise.system.core.transaction";

    /**
     * Field
     */
    public static final String CORBA_LOGGER = DOMAIN_ROOT + "enterprise.resource.corba";

    /**
     * Field
     */
    //START OF IASRI 4660742
    /**
     * Field
     */
    public static final String UTIL_LOGGER = DOMAIN_ROOT + "enterprise.system.util";
    /**
     * Field
     */
    public static final String NAMING_LOGGER = DOMAIN_ROOT + "enterprise.system.core.naming";

    /**
     * Field
     */
    public static final String JNDI_LOGGER = DOMAIN_ROOT + "enterprise.system.core.naming";
    /**
     * Field
     */
    public static final String ACTIVATION_LOGGER = DOMAIN_ROOT + "enterprise.system.activation";
    /**
     * Field
     */
    public static final String JTA_LOGGER = DOMAIN_ROOT + "enterprise.resource.jta";

    /**
     * Resource Logger
     */

    public static final String RSR_LOGGER = DOMAIN_ROOT + "enterprise.resource.resourceadapter";
    //END OF IASRI 4660742

    /**
     * Deployment Logger
     */
    public static final String DPL_LOGGER = DOMAIN_ROOT + "enterprise.system.tools.deployment";

    /**
     * Deployment audit logger
     */
    public static final String DPLAUDIT_LOGGER = DOMAIN_ROOT + "enterprise.system.tools.deployment.audit";

    /**
     * Field
     */
    public static final String DIAGNOSTICS_LOGGER = DOMAIN_ROOT + "enterprise.system.tools.diagnostics";

    /**
     * JAXRPC Logger
     */
    public static final String JAXRPC_LOGGER = DOMAIN_ROOT + "enterprise.system.webservices.rpc";

    /**
     * JAXR Logger
     */
    public static final String JAXR_LOGGER = DOMAIN_ROOT + "enterprise.system.webservices.registry";

    /**
     * SAAJ Logger
     */
    public static final String SAAJ_LOGGER = DOMAIN_ROOT + "enterprise.system.webservices.saaj";

    /**
     * Self Management Logger
     */
    public static final String SELF_MANAGEMENT_LOGGER = DOMAIN_ROOT + "enterprise.system.core.selfmanagement";

    /**
     * SQL Tracing Logger
     */
    public static final String SQL_TRACE_LOGGER = DOMAIN_ROOT + "enterprise.resource.sqltrace";

    /**
     * Admin Logger
     */
    public static final String ADMIN_LOGGER = DOMAIN_ROOT + "enterprise.system.tools.admin";
    /**
     * Server Logger
     */
    public static final String SERVER_LOGGER = DOMAIN_ROOT + "enterprise.system";
    /**
     * core Logger
     */
    public static final String CORE_LOGGER = DOMAIN_ROOT + "enterprise.system.core";
    /**
     * classloader Logger
     */
    public static final String LOADER_LOGGER = DOMAIN_ROOT + "enterprise.system.core.classloading";

    /**
     * Config Logger
     */
    public static final String CONFIG_LOGGER = DOMAIN_ROOT + "enterprise.system.core.config";

    /**
     * Process Launcher Logger
     */
    public static final String PROCESS_LAUNCHER_LOGGER = DOMAIN_ROOT + "enterprise.tools.launcher";

    /**
     * GMS Logger
     */
    public static final String GMS_LOGGER = DOMAIN_ROOT + "org.glassfish.gms";

    /**
     * AMX Logger
     */
    public static final String AMX_LOGGER = DOMAIN_ROOT + "enterprise.system.amx";

    /**
     * JMX Logger
     */
    public static final String JMX_LOGGER = DOMAIN_ROOT + "enterprise.system.jmx";

    /**
     * core/kernel Logger
     */
    public static final String SERVICES_LOGGER = DOMAIN_ROOT + "enterprise.system.core.services";

    /**
     * webservices logger
     */
    public static final String WEBSERVICES_LOGGER = DOMAIN_ROOT + "enterprise.webservices";

    /**
     * monitoring logger
     */
    public static final String MONITORING_LOGGER = DOMAIN_ROOT + "enterprise.system.tools.monitor";

    /**
     * persistence logger
     */
    public static final String PERSISTENCE_LOGGER = DOMAIN_ROOT + "org.glassfish.persistence";

    /**
     * virtualization logger
     */
    public static final String VIRTUALIZATION_LOGGER = DOMAIN_ROOT + "org.glassfish.virtualization";

    /**
     * PaaS logger
     */
    public static final String PAAS_LOGGER = DOMAIN_ROOT + "org.glassfish.paas";

    private static final LogManager MANAGER = LogManager.getLogManager();
    private static final Logger LOG = Logger.getLogger(LogDomains.class.getName());

    /**
     * Returns initialized logger using resource bundle found by the class's classloader.
     * <p>
     * Logger name will be constructed as <code>${loggerNamePrefix}.$(clazz.getPackageName())</code>
     * <p>
     * Locating the resource bundle:
     * <ol>
     * <li>If the loggerNamePrefix starts with <code>jakarta.</code>, this prefix is replaced
     * by <code>com.sun.logging.</code>
     * <li>If the resource bundle is not found, method tries to go through class's package hierarchy
     * to find the closest LogStrings.properties file.
     * </ol>
     *
     * @param clazz - owner of the logger instance.
     * @param loggerNamePrefix
     * @return {@link Logger}, never null
     */
    public static Logger getLogger(final Class<?> clazz, final String loggerNamePrefix) {
        return getLogger(clazz, loggerNamePrefix, true);
    }


    /**
     * Returns initialized logger.
     * <p>
     * If the resourceBundleLookup is true, uses the clazz's classloader to find and load the
     * <code>LogStrings.properties</code> file.
     * <p>
     * Logger name will be constructed as <code>${loggerNamePrefix}.$(clazz.getPackageName())</code>
     * <p>
     * Locating the resource bundle (if requested):
     * <ol>
     * <li>If the loggerNamePrefix starts with <code>jakarta.</code>, this prefix is replaced
     * by <code>com.sun.logging.</code>
     * <li>If the resource bundle is not found, method tries to go through class's package hierarchy
     * to find the closest LogStrings.properties file.
     * </ol>
     *
     * @param clazz - owner of the logger instance.
     * @param loggerNamePrefix
     * @param findResourceBundle - try to find the resource bundle for the logger now.
     * @return {@link Logger}, never null
     */
    public static Logger getLogger(final Class<?> clazz, final String loggerNamePrefix, final boolean findResourceBundle) {
        final ClassLoader resourceBundleLoader = findResourceBundle ? clazz.getClassLoader() : null;
        return getLogger(clazz, loggerNamePrefix, resourceBundleLoader);
    }


    /**
     * Returns initialized logger.
     * <p>
     * Locating the resource bundle:
     * <ol>
     * <li>Uses provided classloader, which allows to use resource bundle from another jar file
     * <li>If the loggerNamePrefix starts with <code>jakarta.</code>, this prefix is replaced
     * by <code>com.sun.logging.</code>
     * <li>If the resource bundle is not found, method tries to go through class's package hierarchy
     * to find the closest LogStrings.properties file.
     * </ol>
     *
     * @param clazz - owner of the logger instance.
     * @param loggerNamePrefix
     * @param rbLoader - try to find the resource bundle for the logger now.
     * @return {@link Logger}, never null
     */
    public static Logger getLogger(final Class<?> clazz, final String loggerNamePrefix, final ClassLoader rbLoader) {
        final String loggerName = loggerNamePrefix + "." + clazz.getPackageName();
        final Logger cachedLogger = MANAGER.getLogger(loggerName);
        if (cachedLogger != null) {
            LOG.log(FINEST, "Cached logger: {0}", cachedLogger);
            return cachedLogger;
        }

        final ResourceBundle resourceBundle;
        if (rbLoader == null) {
            resourceBundle = null;
        } else {
            final String bundleName = getResourceBundleNameForDomainRoot(loggerNamePrefix);
            resourceBundle = getResourceBundle(bundleName, clazz, rbLoader);
        }

        final Logger newLogger = new LogDomainsLogger(loggerName, resourceBundle);

        // We must not return an orphan logger (the one we just created) if
        // a race condition has already created one
        boolean added = MANAGER.addLogger(newLogger);
        if (added) {
            LOG.log(CONFIG, "Registered new logger: {0}", newLogger);
            return newLogger;
        }

        // Another thread was faster
        return MANAGER.getLogger(newLogger.getName());
    }


    private static String getResourceBundleNameForDomainRoot(final String loggerNamePrefix) {
        if (loggerNamePrefix.startsWith(DOMAIN_ROOT)) {
            return loggerNamePrefix.replaceFirst(DOMAIN_ROOT, PACKAGE_ROOT);
        }
        return loggerNamePrefix;
    }


    private static ResourceBundle getResourceBundle(final String name, final Class<?> clazz,
        final ClassLoader rbLoader) {
        final ResourceBundle classBundle = findResourceBundle(name, clazz, rbLoader);
        if (classBundle != null) {
            return classBundle;
        }
        LOG.log(WARNING, "Cannot find the resource bundle for the name {0} for {1} using classloader {2}",
            new Object[] {name, clazz, rbLoader});
        return null;
    }


    private static ResourceBundle findResourceBundle(final String name, final Class<?> clazz,
        final ClassLoader rbLoader) {
        final ResourceBundle packageRootBundle = tryTofindResourceBundle(name, rbLoader);
        if (packageRootBundle != null) {
            return packageRootBundle;
        }
        // not found. Ok, let's try to go through the class's package tree
        final StringBuilder rbPackage = new StringBuilder(clazz.getPackage().getName());
        while (true) {
            final ResourceBundle subPkgBundle = tryTofindResourceBundle(rbPackage.toString(), rbLoader);
            if (subPkgBundle != null) {
                return subPkgBundle;
            }
            final int lastDotIndex = rbPackage.lastIndexOf(".");
            if (lastDotIndex == -1) {
                break;
            }
            rbPackage.delete(lastDotIndex, rbPackage.length());
        }

        return null;
    }


    private static ResourceBundle tryTofindResourceBundle(final String name, final ClassLoader rbLoader) {
        try {
            return ResourceBundle.getBundle(name + "." + LogDomains.RESOURCE_BUNDLE, Locale.getDefault(), rbLoader);
        } catch (MissingResourceException e) {
            return null;
        }
    }


    private static class LogDomainsLogger extends Logger {

        // FIXME: setResourceBundle doesn't work for some reason, breaks
        // test.jms.injection.ClientTestNG.testTransactionScopedJMSContextInjection
        // As GF swallows exceptions in critical code, it is hard to find out why.
        private final ResourceBundle resourceBundle;

        public LogDomainsLogger(String loggerName, ResourceBundle resourceBundle) {
            super(loggerName, null);
            this.resourceBundle = resourceBundle;
        }


        @Override
        public void log(LogRecord record) {
            record.setResourceBundle(resourceBundle);
            super.log(record);
        }


        @Override
        public ResourceBundle getResourceBundle() {
            return this.resourceBundle;
        }


        @Override
        public String getResourceBundleName() {
            return resourceBundle == null ? null : resourceBundle.getBaseBundleName();
        }


        @Override
        public String toString() {
            return super.toString() + "[name=" + getName() + ", bundleName=" + getResourceBundleName() + "]";
        }
    }
}
