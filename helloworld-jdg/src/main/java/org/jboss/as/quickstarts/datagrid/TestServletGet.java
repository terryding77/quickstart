/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.datagrid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.Cache;

/**
 * A simple servlet requesting the cache for key "hello".
 *
 * @author Pete Muir
 *
 */
@SuppressWarnings("serial")
@WebServlet("/TestServletGet")
public class TestServletGet extends HttpServlet {

    private static final String PAGE_HEADER = "<html><head /><body>";

    private static final String PAGE_FOOTER = "</body></html>";

    @Inject
    private Logger log;

    @Inject
    DefaultCacheManager m;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("putting hello");
        Cache<String, String> c = m.getCache();
        String x = (String) c.get("hello");

        PrintWriter writer = resp.getWriter();
        writer.println(PAGE_HEADER);
        writer.println("<h1>" + "Get Infinispan: " + x + "</h1>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

}
