/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.entity;

import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

/**
 * Represents a strategy to determine length of the enclosed content entity
 * based on properties of the HTTP message.
 *
 * @since 4.0
 */
public interface ContentLengthStrategy {

    /**
     * Identity transfer encoding. Message content is delineated
     * by the end of connection.
     */
    int IDENTITY = -1;

    /**
     * Message body chunk coded
     */
    long CHUNKED = -2;

    /**
     * Message body not explicitly delineated. Legal for HTTP response messages
     * and illegal for HTTP request messages.
     */
    long UNDEFINED = -Long.MAX_VALUE;

    /**
     * Returns length of the given message in bytes. The returned value
     * must be a non-negative number, {@link #IDENTITY} if the end of the
     * message is delineated by the end of connection, {@link #CHUNKED}
     * if the message is chunk coded, or {@link #UNDEFINED} if the message
     * is not explicitly delineated.
     *
     * @param message HTTP message
     * @return content length, {@link #UNDEFINED}, or {@link #CHUNKED}
     *
     * @throws HttpException in case of HTTP protocol violation
     */
    long determineLength(HttpMessage message) throws HttpException;

}
