/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 */

package com.nu1silva.apps.rest.services;

import com.nu1silva.apps.rest.domain.Employees;
import com.nu1silva.apps.rest.domain.Status;
import com.nu1silva.apps.rest.h2connector.H2Connector;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/user")
public class UserManagementService {

    H2Connector h2Connector = new H2Connector();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/search/{userId}")
    public Response getUser(@PathParam("userId") int userId) throws SQLException {
        System.out.println("get employee with Id [" + userId + "]");
        return Response.status(200).entity(h2Connector.getEmployee(userId)).build();
    }

    // {"employeeId":1,"firstName":"Nuwan","lastName":"Silva","salary":100000.0,"status":"ACTIVE"}
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response addUser(Employees employee) throws SQLException {
        Status output;
        System.out.println("adding employee with Id [" + employee.getEmployeeId() + "]");
        if (h2Connector.addEmployee(employee)) {
            output = new Status("user created successfully");
            System.out.println("user creation successful");
        } else {
            output = new Status("user creation failed");
            System.out.println("user creation failed");
        }
        return Response.status(201).entity(output).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateUser(Employees employee) throws SQLException {
        Status output;
        System.out.println("update employee with Id [" + employee.getEmployeeId() + "]");
        if (h2Connector.updateEmployee(employee))
            output = new Status("user updated successfully");
        else
            output = new Status("user update failed");
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{userId}")
    public Response deleteUser(@PathParam("userId") int userId) throws SQLException {
        Status output;
        System.out.println("delete employee with Id [" + userId + "]");
        if (h2Connector.deleteEmployee(userId))
            output = new Status("user deleted successfully");
        else
            output = new Status("user deletion failed");
        return Response.status(200).entity(output).build();
    }
}
