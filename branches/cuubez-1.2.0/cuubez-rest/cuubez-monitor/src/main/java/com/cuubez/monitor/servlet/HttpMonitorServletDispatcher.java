package com.cuubez.monitor.servlet;


import com.cuubez.core.initiator.ServletContainer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

public class HttpMonitorServletDispatcher extends ServletContainer {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        super.process(req, resp, HttpMethod.POST);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        super.process(req, resp, HttpMethod.GET);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        super.process(req, resp, HttpMethod.PUT);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        super.process(req, resp, HttpMethod.DELETE);
    }
}
