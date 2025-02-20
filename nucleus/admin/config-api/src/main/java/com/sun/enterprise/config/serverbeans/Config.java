/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 * Copyright (c) 1997, 2020 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.enterprise.config.serverbeans;

import com.sun.common.util.logging.LoggingConfigImpl;
import com.sun.enterprise.config.serverbeans.customvalidators.NotDuplicateTargetName;
import com.sun.enterprise.config.serverbeans.customvalidators.NotTargetKeyword;
import com.sun.enterprise.config.util.ServerHelper;

import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glassfish.api.admin.config.ConfigExtension;
import org.glassfish.api.admin.config.Container;
import org.glassfish.api.admin.config.Named;
import org.glassfish.api.admin.config.PropertiesDesc;
import org.glassfish.api.admin.config.PropertyDesc;
import org.glassfish.api.naming.SimpleJndiName;
import org.glassfish.config.support.datatypes.Port;
import org.glassfish.grizzly.config.dom.NetworkConfig;
import org.glassfish.grizzly.config.dom.NetworkListener;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.quality.ToDo;
import org.glassfish.server.ServerEnvironmentImpl;
import org.jvnet.hk2.config.Attribute;
import org.jvnet.hk2.config.ConfigBean;
import org.jvnet.hk2.config.ConfigBeanProxy;
import org.jvnet.hk2.config.ConfigExtensionMethod;
import org.jvnet.hk2.config.ConfigSupport;
import org.jvnet.hk2.config.ConfigView;
import org.jvnet.hk2.config.Configured;
import org.jvnet.hk2.config.DuckTyped;
import org.jvnet.hk2.config.Element;
import org.jvnet.hk2.config.SingleConfigCode;
import org.jvnet.hk2.config.TransactionFailure;
import org.jvnet.hk2.config.types.Property;
import org.jvnet.hk2.config.types.PropertyBag;

import static org.glassfish.config.support.Constants.NAME_SERVER_REGEX;

/**
 * The configuration defines the configuration of a server instance that can be shared by other server instances. The
 * availability-service and are SE/EE only
 */

/* @XmlType(name = "", propOrder = {
    "httpService",
    "adminService",
    "logService",
    "securityService",
    "monitoringService",
    "diagnosticService",
    "javaConfig",
    "availabilityService",
    "threadPools",
    "groupManagementService",
    "systemProperty",
    "property"
}) */

@Configured
@NotDuplicateTargetName(message = "{config.duplicate.name}", payload = Config.class)
public interface Config extends Named, PropertyBag, SystemPropertyBag, Payload, ConfigLoader, ConfigBeanProxy, RefContainer {
    /**
     * Name of the configured object
     *
     * @return name of the configured object FIXME: should set 'key=true'. See bugs 6039, 6040
     */
    @NotNull
    @NotTargetKeyword(message = "{config.reserved.name}", payload = Config.class)
    @Pattern(regexp = NAME_SERVER_REGEX, message = "{config.invalid.name}", payload = Config.class)
    @Override
    String getName();

    @Override
    void setName(String value) throws PropertyVetoException;

    /**
     * Gets the value of the dynamicReconfigurationEnabled property.
     *
     * When set to "true" then any changes to the system (e.g. applications deployed, resources created) will be
     * automatically applied to the affected servers without a restart being required. When set to "false" such changes will
     * only be picked up by the affected servers when each server restarts.
     *
     * @return possible object is {@link String }
     */
    @Attribute(defaultValue = "true", dataType = Boolean.class)
    String getDynamicReconfigurationEnabled();

    /**
     * Sets the value of the dynamicReconfigurationEnabled property.
     *
     * @param value allowed object is {@link String }
     */
    void setDynamicReconfigurationEnabled(String value) throws PropertyVetoException;

    /**
     * Gets the value of the networkConfig property.
     *
     * @return possible object is {@link NetworkConfig }
     */
    @Element(required = true)
    NetworkConfig getNetworkConfig();

    /**
     * Sets the value of the networkConfig property.
     *
     * @param value allowed object is {@link NetworkConfig }
     */
    void setNetworkConfig(NetworkConfig value) throws PropertyVetoException;

    /**
     * Gets the value of the httpService property.
     *
     * @return possible object is {@link HttpService }
     */
    @Element(required = true)
    HttpService getHttpService();

    /**
     * Sets the value of the httpService property.
     *
     * @param value allowed object is {@link HttpService }
     */
    void setHttpService(HttpService value) throws PropertyVetoException;

    /**
     * Gets the value of the adminService property.
     *
     * @return possible object is {@link AdminService }
     */
    @Element(required = true)
    AdminService getAdminService();

    /**
     * Sets the value of the adminService property.
     *
     * @param value allowed object is {@link AdminService }
     */
    void setAdminService(AdminService value) throws PropertyVetoException;

    /**
     * Gets the value of the logService property.
     *
     * @return possible object is {@link LogService }
     */
    @Element(required = true)
    LogService getLogService();

    /**
     * Sets the value of the logService property.
     *
     * @param value allowed object is {@link LogService }
     */
    void setLogService(LogService value) throws PropertyVetoException;

    /**
     * Gets the value of the securityService property.
     *
     * @return possible object is {@link SecurityService }
     */
    @Element(required = true)
    SecurityService getSecurityService();

    /**
     * Sets the value of the securityService property.
     *
     * @param value allowed object is {@link SecurityService }
     */
    void setSecurityService(SecurityService value) throws PropertyVetoException;

    /**
     * Gets the value of the monitoringService property.
     *
     * @return possible object is {@link MonitoringService }
     */
    @Element()
    @NotNull
    MonitoringService getMonitoringService();

    /**
     * Sets the value of the monitoringService property.
     *
     * @param value allowed object is {@link MonitoringService }
     */
    void setMonitoringService(MonitoringService value) throws PropertyVetoException;

    /**
     * Gets the value of the diagnosticService property.
     *
     * @return possible object is {@link DiagnosticService }
     */
    @Element
    DiagnosticService getDiagnosticService();

    /**
     * Sets the value of the diagnosticService property.
     *
     * @param value allowed object is {@link DiagnosticService }
     */
    void setDiagnosticService(DiagnosticService value) throws PropertyVetoException;

    /**
     * Gets the value of the javaConfig property.
     *
     * @return possible object is {@link JavaConfig }
     */
    @Element(required = true)
    JavaConfig getJavaConfig();

    /**
     * Sets the value of the javaConfig property.
     *
     * @param value allowed object is {@link JavaConfig }
     */
    void setJavaConfig(JavaConfig value) throws PropertyVetoException;

    /**
     * Gets the value of the availabilityService property.
     *
     * @return possible object is {@link AvailabilityService }
     */
    @Element
    @NotNull
    AvailabilityService getAvailabilityService();

    /**
     * Sets the value of the availabilityService property.
     *
     * @param value allowed object is {@link AvailabilityService }
     */
    void setAvailabilityService(AvailabilityService value) throws PropertyVetoException;

    /**
     * Gets the value of the threadPools property.
     *
     * @return possible object is {@link ThreadPools }
     */
    @Element(required = true)
    ThreadPools getThreadPools();

    /**
     * Sets the value of the threadPools property.
     *
     * @param value allowed object is {@link ThreadPools }
     */
    void setThreadPools(ThreadPools value) throws PropertyVetoException;

    /**
     * Gets the value of the groupManagementService property.
     *
     * @return possible object is {@link GroupManagementService }
     */
    @Element
    @NotNull
    GroupManagementService getGroupManagementService();

    /**
     * Sets the value of the groupManagementService property.
     *
     * @param value allowed object is {@link GroupManagementService }
     */
    void setGroupManagementService(GroupManagementService value) throws PropertyVetoException;

    /**
     * Gets the value of the systemProperty property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the
     * systemProperty property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     *
     * <pre>
     * getSystemProperty().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link SystemProperty }
     */
    @ToDo(priority = ToDo.Priority.IMPORTANT, details = "Any more legal system properties?")
    @PropertiesDesc(systemProperties = true, props = {
            @PropertyDesc(name = "HTTP_LISTENER_PORT", defaultValue = "8080", dataType = Port.class),
            @PropertyDesc(name = "HTTP_SSL_LISTENER_PORT", defaultValue = "1043", dataType = Port.class),
            @PropertyDesc(name = "HTTP_ADMIN_LISTENER_PORT", defaultValue = "4848", dataType = Port.class),
            @PropertyDesc(name = "IIOP_LISTENER_PORT", defaultValue = "3700", dataType = Port.class),
            @PropertyDesc(name = "IIOP_SSL_LISTENER_PORT", defaultValue = "1060", dataType = Port.class),
            @PropertyDesc(name = "IIOP_SSL_MUTUALAUTH_PORT", defaultValue = "1061", dataType = Port.class),
            @PropertyDesc(name = "JMX_SYSTEM_CONNECTOR_PORT", defaultValue = "8686", dataType = Port.class) })
    @Element
    @Override
    List<SystemProperty> getSystemProperty();

    //DuckTyped for accessing the logging.properties file

    @DuckTyped
    Map<String, String> getLoggingProperties();

    @DuckTyped
    String setLoggingProperty(String property, String value);

    @DuckTyped
    Map<String, String> updateLoggingProperties(Map<String, String> properties);

    @DuckTyped
    NetworkListener getAdminListener();

    @DuckTyped
    <T extends ConfigExtension> T createDefaultChildByType(Class<T> type);

    /**
     * Return an extension configuration given the extension type.
     *
     * @param type type of the requested extension configuration
     * @param <T> interface subclassing the ConfigExtension type
     * @return a configuration proxy of type T or null if there is no such configuration with that type.
     */
    @ConfigExtensionMethod
    <T extends ConfigExtension> T getExtensionByType(Class<T> type);

    /**
     * Add name as an index key for this Config and for the objects that are directly referenced by this Config. This
     * includes all of the Config extensions.
     *
     * @param habitat ServiceLocator that contains this Config
     * @param name name to use to identify the objects
     */
    @DuckTyped
    void addIndex(ServiceLocator habitat, String name);

    /**
     * @param configBeanType The config bean type we want to check whether the configuration exists for it or not.
     * @param <P> Type that extends the ConfigBeanProxy which is the type of class we accept as parameter
     * @return true if configuration for the type exists in the target area of domain.xml and false if not.
     */
    @DuckTyped
    <P extends ConfigExtension> boolean checkIfExtensionExists(Class<P> configBeanType);

    @DuckTyped
    ResourceRef getResourceRef(SimpleJndiName refName);

    @DuckTyped
    boolean isResourceRefExists(SimpleJndiName refName);

    @DuckTyped
    void createResourceRef(String enabled, SimpleJndiName refName) throws TransactionFailure;

    @DuckTyped
    void deleteResourceRef(SimpleJndiName refName) throws TransactionFailure;

    @DuckTyped
    boolean isDas();

    class Duck {

        public static boolean isDas(Config c) {
            try {
                String type = c.getAdminService().getType();

                if (type != null && (type.equals("das") || type.equals("das-and-server"))) {
                    return true;
                }
            } catch (Exception e) {
                // fall through
            }
            return false;
        }

        public static String setLoggingProperty(Config c, String property, String value) {
            ConfigBean cb = (ConfigBean) ((ConfigView) Proxy.getInvocationHandler(c)).getMasterView();
            ServerEnvironmentImpl env = cb.getHabitat().getService(ServerEnvironmentImpl.class);
            LoggingConfigImpl loggingConfig = new LoggingConfigImpl();
            loggingConfig.setupConfigDir(env.getConfigDirPath(), env.getLibPath());

            String prop = null;
            try {
                prop = loggingConfig.setLoggingProperty(property, value);
            } catch (IOException ex) {
            }
            return prop;
        }

        public static Map<String, String> getLoggingProperties(Config c) {
            ConfigBean cb = (ConfigBean) ((ConfigView) Proxy.getInvocationHandler(c)).getMasterView();
            ServerEnvironmentImpl env = cb.getHabitat().getService(ServerEnvironmentImpl.class);
            LoggingConfigImpl loggingConfig = new LoggingConfigImpl();
            loggingConfig.setupConfigDir(env.getConfigDirPath(), env.getLibPath());

            Map<String, String> map = new HashMap<>();
            try {
                map = loggingConfig.getLoggingProperties();
            } catch (IOException ex) {
            }
            return map;
        }

        public static Map<String, String> updateLoggingProperties(Config c, Map<String, String> properties) {
            ConfigBean cb = (ConfigBean) ((ConfigView) Proxy.getInvocationHandler(c)).getMasterView();
            ServerEnvironmentImpl env = cb.getHabitat().getService(ServerEnvironmentImpl.class);
            LoggingConfigImpl loggingConfig = new LoggingConfigImpl();
            loggingConfig.setupConfigDir(env.getConfigDirPath(), env.getLibPath());

            Map<String, String> map = new HashMap<>();
            try {
                map = loggingConfig.updateLoggingProperties(properties);
            } catch (IOException ex) {
            }
            return map;
        }

        public static NetworkListener getAdminListener(Config c) {
            return ServerHelper.getAdminListener(c);
        }

        public static void addIndex(Config c, ServiceLocator habitat, String name) {
            ServiceLocatorUtilities.addOneDescriptor(habitat, BuilderHelper.createConstantDescriptor(c, name, Config.class));

            // directly referenced objects
            ConfigBeanProxy dirref[] = { c.getAdminService(), c.getAvailabilityService(), c.getDiagnosticService(), c.getHttpService(),
                    c.getJavaConfig(), c.getLogService(), c.getNetworkConfig(), c.getSecurityService(), c.getThreadPools(),
                    c.getMonitoringService(), };
            for (ConfigBeanProxy cbp : dirref) {
                if (cbp != null) {
                    ServiceLocatorUtilities.addOneDescriptor(habitat,
                            BuilderHelper.createConstantDescriptor(cbp, name, ConfigSupport.getImpl(cbp).getProxyType()));
                }
            }

            // containers
            for (Container extension : c.getContainers()) {
                ServiceLocatorUtilities.addOneDescriptor(habitat,
                        BuilderHelper.createConstantDescriptor(extension, name, ConfigSupport.getImpl(extension).getProxyType()));
            }
        }

        public static <P extends ConfigExtension> boolean checkIfExtensionExists(Config c, Class<P> configBeanType) {
            for (ConfigExtension extension : c.getExtensions()) {
                try {
                    configBeanType.cast(extension);
                    return true;
                } catch (Exception e) {
                    // ignore, not the right type.
                }
            }
            return false;
        }

        public static void createResourceRef(Config config, final String enabled, final SimpleJndiName refName) throws TransactionFailure {
            ConfigSupport.apply(new SingleConfigCode<Config>() {

                @Override
                public Object run(Config param) throws PropertyVetoException, TransactionFailure {

                    ResourceRef newResourceRef = param.createChild(ResourceRef.class);
                    newResourceRef.setEnabled(enabled);
                    newResourceRef.setRef(refName.toString());
                    param.getResourceRef().add(newResourceRef);
                    return newResourceRef;
                }
            }, config);
        }

        public static ResourceRef getResourceRef(Config config, SimpleJndiName refName) {
            for (ResourceRef ref : config.getResourceRef()) {
                if (ref.getRef().equals(refName.toString())) {
                    return ref;
                }
            }
            return null;
        }

        public static boolean isResourceRefExists(Config config, SimpleJndiName refName) {
            return getResourceRef(config, refName) != null;
        }

        public static void deleteResourceRef(Config config, SimpleJndiName refName) throws TransactionFailure {
            final ResourceRef ref = getResourceRef(config, refName);
            if (ref != null) {
                ConfigSupport.apply(new SingleConfigCode<Config>() {

                    @Override
                    public Object run(Config param) {
                        return param.getResourceRef().remove(ref);
                    }
                }, config);
            }
        }

    }

    /**
     * Properties as per {@link PropertyBag}
     */
    @ToDo(priority = ToDo.Priority.IMPORTANT, details = "Provide PropertyDesc for legal props")
    @PropertiesDesc(props = {})
    @Element
    @Override
    List<Property> getProperty();

    /**
     * Get the configuration for other types of containers.
     *
     * @return list of containers configuration
     */
    @Element("*")
    List<Container> getContainers();

    @Element("*")
    List<ConfigExtension> getExtensions();
}
