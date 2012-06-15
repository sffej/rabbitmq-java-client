//  The contents of this file are subject to the Mozilla Public License
//  Version 1.1 (the "License"); you may not use this file except in
//  compliance with the License. You may obtain a copy of the License
//  at http://www.mozilla.org/MPL/
//
//  Software distributed under the License is distributed on an "AS IS"
//  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
//  the License for the specific language governing rights and
//  limitations under the License.
//
//  The Original Code is RabbitMQ.
//
//  The Initial Developer of the Original Code is VMware, Inc.
//  Copyright (c) 2007-2012 VMware, Inc.  All rights reserved.
//

package com.rabbitmq.client.test.ssl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.test.BrokerTestCase;

/**
 * Test for <a href="https://bugzilla.rabbitmq.com/show_bug.cgi?id=19356">bug 19356</a>
 * - SSL Support in RabbitMQ
 *
 */
public class UnverifiedConnection extends BrokerTestCase {

    /** exception caught - unused */
    public Exception caughtException = null;
    /** whether completed - unused */
    public boolean completed = false;
    /** whether created - read by this test only */
    public boolean created = false;

    public void openConnection()
        throws IOException
    {
        try {
            connectionFactory.useSslProtocol();
        } catch (NoSuchAlgorithmException ex) {
            throw new IOException(ex.toString());
        } catch (KeyManagementException ex) {
            throw new IOException(ex.toString());
        }


        if (connection == null) {
            connection = connectionFactory.newConnection();
        }
    }

    protected void releaseResources()
        throws IOException
    {
        if (created) {
            channel.queueDelete("Bug19356Test");
        }
    }

    /**
     * @throws Exception test
     */
    public void testSSL() throws Exception
    {
        channel.queueDeclare("Bug19356Test", false, true, true, null);
        channel.basicPublish("", "Bug19356Test", null, "SSL".getBytes());

        GetResponse chResponse = channel.basicGet("Bug19356Test", false);
        assertNotNull(chResponse);

        byte[] body = chResponse.getBody();
        assertEquals("SSL", new String(body));
    }

}
