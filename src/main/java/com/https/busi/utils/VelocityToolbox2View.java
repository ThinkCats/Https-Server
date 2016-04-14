package com.https.busi.utils;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.Toolbox;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.Map;

public class VelocityToolbox2View extends VelocityLayoutView {
    private static ToolManager tm;
    private static VelocityEngine ve;
    private static Toolbox ta, ts, tr;

    //静态方法供发邮件时调用
    public static String merge(String vm, Map<String, Object> model) {
        if (tm != null) {
            ToolContext ctx = tm.createContext();
            if (model != null) ctx.putAll(model);
            StringWriter out = new StringWriter();
            ve.mergeTemplate(vm, "utf-8", ctx, out);
            return out.toString();
        } else {
            return null;
        }
    }

    //生成view时添加toolbox到context
    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewToolContext ctx = new ViewToolContext(getVelocityEngine(), request, response, getServletContext());
        ctx.putAll(model);
        if (tm != null || this.getToolboxConfigLocation() != null) {
            if (tm == null) {
                tm = new ToolManager();
                tm.setVelocityEngine(getVelocityEngine());
                tm.configure(String.valueOf(getServletContext().getClassLoader().getResource(getToolboxConfigLocation())));

                ve = getVelocityEngine();
                ta = tm.getToolboxFactory().createToolbox(Scope.APPLICATION);
                ts = tm.getToolboxFactory().createToolbox(Scope.SESSION);
                tr = tm.getToolboxFactory().createToolbox(Scope.REQUEST);
            }
            ctx.addToolbox(ta);
            ctx.addToolbox(ts);
            ctx.addToolbox(tr);
        }
        return ctx;
    }
}