/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.clustering.infinispan;

import java.net.UnknownHostException;
import java.util.Properties;

import org.infinispan.commons.CacheConfigurationException;
import org.infinispan.configuration.cache.CacheMode;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.persistence.ConfigurationPersistenceException;
import org.jboss.as.domain.management.SecurityRealm;
import org.jboss.as.network.OutboundSocketBinding;
import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Cause;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.msc.inject.InjectionException;
import org.jboss.msc.service.StartException;

/**
 * InfinispanMessages
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@MessageBundle(projectCode = "DGISPN", length = 4)
public interface InfinispanMessages {

    /**
     * A logger with the category of the default clustering package.
     */
    InfinispanMessages MESSAGES = Messages.getBundle(InfinispanMessages.class);

    /**
     * Creates an exception indicating a failure to resolve the outbound socket binding represented by the
     * {@code binding} parameter.
     *
     * @param cause the cause of the error.
     * @param binding the outbound socket binding.
     *
     * @return a {@link ConfigurationPersistenceException} for the error.
     */
    @Message(id = 100, value = "Could not resolve destination address for outbound socket binding named '%s'")
    InjectionException failedToInjectSocketBinding(@Cause UnknownHostException cause, OutboundSocketBinding binding);

    @Message(id = 101, value = "Failed to add %s %s cache to non-clustered %s cache container.")
    StartException transportRequired(CacheMode mode, String cache, String cacheContainer);

    /**
     * Creates an exception indicating an invalid cache store.
     *
     * @param cause          the cause of the error.
     * @param cacheStoreName the name of the cache store.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 102, value = "%s is not a valid cache store")
    OperationFailedException invalidCacheStore(@Cause Throwable cause, String cacheStoreName);

    /**
     * Creates an exception indicating an invalid cache store.
     *
     * @param cacheName     the name of the cache store.
     * @param cacheContainerName the container name.
     *
     * @return an {@link IllegalArgumentException} for the error.
     */
    @Message(id = 103, value = "%s is not a valid default cache. The %s cache container does not contain a cache with that name")
    IllegalArgumentException invalidDefaultCache(String cacheName, String cacheContainerName);

    /**
     * Creates an exception indicating the an executor property is invalid.
     *
     * @param id         the id of the property.
     * @param properties the properties that were searched.
     *
     * @return an {@link IllegalStateException} for the error.
     */
    @Message(id = 104, value = "No %s property was specified within the executor properties: %s")
    IllegalStateException invalidExecutorProperty(String id, Properties properties);

    /**
     * Creates an exception indicating the an transport property is invalid.
     *
     * @param id         the id of the property.
     * @param properties the properties that were searched.
     *
     * @return an {@link IllegalStateException} for the error.
     */
    @Message(id = 105, value = "No %s property was specified within the transport properties: %s")
    IllegalStateException invalidTransportProperty(String id, Properties properties);

    /**
     * Creates an exception indicating that the cache is aborting after the specified number of retries.
     *
     * @param cause           the cause of the error.
     * @param numberOfRetries the number of retries.
     *
     * @return a {@link RuntimeException}
     */
    @Message(id = 106, value = "Aborting cache operation after %d retries.")
    RuntimeException abortingCacheOperation(@Cause Throwable cause, int numberOfRetries);

    /**
     * Creates an exception indicating the an operation parameter is invalid.
     *
     * @param id         the id of the parameter.
     * @param allowableValues the allowable values for the parameter
     *
     * @return the String.
     */
    @Message(id = 107, value = "Invalid value for parameter %s. Allowable values: %s")
    String invalidParameterValue(String id, String allowableValues);

    /**
     * Creates an exception indicating the a cache store cannot be added as one already exists.
     *
     * @param existingStoreName the store which already exists.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 108, value = "Cache store cannot be created: cache store %s is already defined")
    OperationFailedException cacheStoreAlreadyDefined(String existingStoreName);

    /**
     * Creates an exception indicating the a cache store cannot be added as one already exists.
     *
     * @param propertyKey the name of the property.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 109, value = "Value for property with key %s is not defined")
    OperationFailedException propertyValueNotDefined(String propertyKey);

    /**
     * A message indicating that the resource could not be located.
     *
     * @param resourceName the name of the resource.
     *
     * @return the String message.
     */
    @Message(id = 110, value = "Failed to locate %s")
    String notFound(String resourceName);

    /**
     * A message indicating that the resource could not be parsed.
     *
     * @param resourceName the name of the resource.
     *
     * @return IllegalStateException instance.
     */
    @Message(id = 111, value = "Failed to parse %s")
    IllegalStateException failedToParse(@Cause Throwable cause, String resourceName);

    /**
     * Creates an exception indicating a singleton resource already exists.
     *
     * @param resourceName the name of the resource.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 112, value = "Add operation failed: singleton %s already exists.")
    OperationFailedException singletonResourceAlreadyExists(String resourceName);

    /**
     * Creates an exception indicating unable to remove an alias from an empty list of aliases.
     *
     * @param aliasName the name of the alias.
     *
     * @return an {@link OperationFailedException} for the error.
     */
    @Message(id = 113, value = "cannot remove alias % from empty list.")
    OperationFailedException cannotRemoveAliasFromEmptyList(String aliasName);

    /**
     * Creates an exception indicating that an attribute has been deprecated.
     *
     * @param attributeName the name of the deprecated attribute
     * @return an {@link OperationFailedException} for the error
     */
    @Message(id = 114, value = "Attribute '%s' has been deprecated.")
    OperationFailedException attributeDeprecated(String attributeName);

    @Message(id = 115, value = "Attribute 'segments' is an expression and therefore cannot be translated to legacy attribute 'virtual-nodes'. This resource will need to be ignored on that host.")
    String virtualNodesDoesNotSupportExpressions();

    /**
     * Creates an exception indicating the a cache loader cannot be added as one already exists.
     *
     * @param existingStoreName the loader which already exists.
     * @return an {@link OperationFailedException} for the error
     */
    @Message(id = 116, value = "Cache loader cannot be created: cache loader %s is already defined")
    OperationFailedException cacheLoaderAlreadyDefined(String existingStoreName);

    /**
     * Creates an exception indicating an invalid cache loader.
     *
     * @param cause the cause of the error.
     * @param cacheLoaderName the name of the cache loader.
     *
     * @return an {@link IllegalArgumentException} for the error.
     */
    @Message(id = 117, value = "%s is not a valid cache loader")
    IllegalArgumentException invalidCacheLoader(@Cause Throwable cause, String cacheLoaderName);

    /**
     * Returns a message for failed operations
     *
     * @param message
     * @return
     */
    @Message(id = 118, value = "Failed to invoke operation: %s")
    String failedToInvokeOperation(String message);

    /**
     * Creates an exception indicating an invalid compatibility marshaller.
     *
     * @param cause the cause of the error.
     * @param marshallerClassName the name of the marshaller.
     *
     * @return an {@link IllegalArgumentException} for the error.
     */
    @Message(id = 119, value = "%s is not a valid marshaller")
    IllegalArgumentException invalidCompatibilityMarshaller(@Cause Throwable cause, String marshallerClassName);

   /**
    * Creates an exception indicating incompatible parameter sizes
    *
    * @param firstParameter  the first parameter name
    * @param secondParameter the second parameter name
    * @return an {@link IllegalArgumentException} for the error.
    */
   @Message(id = 120, value = "Parameter %s must be the same size as parameter %s")
   IllegalArgumentException invalidParameterSizes(String firstParameter, String secondParameter);

   /**
    * Creates an exception indicating incompatible parameter type
    *
    * @param name         the parameter name
    * @param requiredType the required type of the parameter
    * @return an {@link IllegalArgumentException} for the error.
    */
   @Message(id = 121, value = "Parameter %s must be of type %s")
   IllegalArgumentException invalidParameterType(String name, String requiredType);

   /**
    * Error message thrown when Subsystem can't instantiate given class.
    */
   @Message(id = 122, value = "Could not instantiate class %s")
   IllegalStateException unableToInstantiateClass(String className);

   @Message(id = 123, value = "%s has been removed since 9.0.0. Please use %s instead")
   CacheConfigurationException removeJDBCStoreSpecified(String oldStore, String newStore);

   @Message(id = 124, value = "Could not inject resolve destination address for outbound socket binding named '%s'")
   InjectionException failedToInjectSecurityRealm(@Cause UnknownHostException cause, SecurityRealm realm);
}
