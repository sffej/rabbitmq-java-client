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

package com.rabbitmq.examples.perf;

/**
 * Named variable value
 */
public interface VariableValue {
    /**
     * Set this variable value in run
     * @param params of full run
     * @throws Exception on failure to setup
     */
    public void setup(MulticastParams params) throws Exception;
    /**
     * Teardown this variable value
     * @param params of full run
     */
    public void teardown(MulticastParams params);

    /**
     * @return name of variable
     */
    public String getName();
    /**
     * @return value of variable
     */
    public Object getValue();
}
